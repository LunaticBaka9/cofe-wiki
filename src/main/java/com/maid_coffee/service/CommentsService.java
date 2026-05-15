package com.maid_coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.Comments;
import com.maid_coffee.entity.LikeRecord;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.CommentsMapper;
import com.maid_coffee.mapper.LikeRecordMapper;

import jakarta.annotation.Resource;

@Service
public class CommentsService {

    @Resource
    CommentsMapper commentsMapper;

    @Resource
    LikeRecordMapper likeRecordMapper;

    public List<Comments> selectRootComments(Long targetId, String targetType) {
        return commentsMapper.selectRootComments(targetId, targetType);
    }

    public List<Comments> selectRepliesByRootId(Long rootId) {
        return commentsMapper.selectRepliesByRootId(rootId);
    }

    public Comments selectById(Long id) {
        return commentsMapper.selectById(id);
    }

    public PageInfo<Comments> selectPage(Integer pageNum, Integer pageSize, Comments comments) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comments> list = commentsMapper.selectRootComments(comments.getTargetId(), comments.getTargetType());
        return PageInfo.of(list);
    }

    public void add(Comments comment) {
        if (comment.getRootId() == null) {
            comment.setRootId(0L);
        }
        if (comment.getParentId() == null) {
            comment.setParentId(0L);
        }
        if (comment.getParentUserId() == null) {
            comment.setParentUserId(0L);
        }
        if (comment.getLikeCount() == null) {
            comment.setLikeCount(0);
        }
        if (comment.getReplyCount() == null) {
            comment.setReplyCount(0);
        }
        if (comment.getIsTop() == null) {
            comment.setIsTop(false);
        }
        if (comment.getStatus() == null) {
            comment.setStatus(true);
        }
        commentsMapper.insert(comment);

        if (comment.getParentId() != null && comment.getParentId() > 0) {
            Comments parent = commentsMapper.selectById(comment.getParentId());
            if (parent != null) {
                int count = parent.getReplyCount() != null ? parent.getReplyCount() : 0;
                commentsMapper.updateReplyCount(comment.getParentId(), count + 1);
            }
        }
    }

    public List<Comments> selectRootCommentsWithReplies(Long targetId, String targetType) {
        return selectRootCommentsWithReplies(targetId, targetType, null);
    }

    public List<Comments> selectRootCommentsWithReplies(Long targetId, String targetType, Long currentUserId) {
        List<Comments> rootComments = commentsMapper.selectRootComments(targetId, targetType);
        for (Comments root : rootComments) {
            List<Comments> replies = commentsMapper.selectRepliesByRootId(root.getId());
            root.setReplies(replies);
        }
        if (currentUserId != null) {
            populateIsLiked(rootComments, currentUserId);
        }
        return rootComments;
    }

    private void populateIsLiked(List<Comments> comments, Long userId) {
        List<Long> allIds = new java.util.ArrayList<>();
        for (Comments c : comments) {
            allIds.add(c.getId());
            if (c.getReplies() != null) {
                for (Comments r : c.getReplies()) {
                    allIds.add(r.getId());
                }
            }
        }
        if (allIds.isEmpty()) return;
        List<LikeRecord> records = likeRecordMapper.selectByCommentIdsAndUserId(allIds, userId);
        java.util.Set<Long> likedIds = new java.util.HashSet<>();
        for (LikeRecord r : records) {
            likedIds.add(r.getCommentId());
        }
        for (Comments c : comments) {
            c.setIsLiked(likedIds.contains(c.getId()));
            if (c.getReplies() != null) {
                for (Comments r : c.getReplies()) {
                    r.setIsLiked(likedIds.contains(r.getId()));
                }
            }
        }
    }

    public void delete(Long id) {
        Comments existing = commentsMapper.selectById(id);
        if (existing == null) {
            throw new CustomerException("评论不存在");
        }
        existing.setStatus(false);
        existing.setUpdatedDate(new java.util.Date());
        commentsMapper.updateStatus(existing);
    }

    public void toggleLike(Long commentId, Long userId, String action) {
        Comments comment = commentsMapper.selectById(commentId);
        if (comment == null) {
            throw new CustomerException("评论不存在");
        }
        LikeRecord existing = likeRecordMapper.selectByCommentIdAndUserId(commentId, userId);
        if ("like".equals(action)) {
            if (existing != null) {
                throw new CustomerException("您已经点过赞了");
            }
            LikeRecord record = new LikeRecord();
            record.setCommentId(commentId);
            record.setUserId(userId);
            likeRecordMapper.insert(record);
            int count = comment.getLikeCount() != null ? comment.getLikeCount() : 0;
            commentsMapper.updateLikeCount(commentId, count + 1);
        } else if ("unlike".equals(action)) {
            if (existing == null) {
                throw new CustomerException("您还没有点赞");
            }
            likeRecordMapper.deleteByCommentIdAndUserId(commentId, userId);
            int count = comment.getLikeCount() != null ? comment.getLikeCount() : 0;
            commentsMapper.updateLikeCount(commentId, Math.max(0, count - 1));
        }
    }
}
