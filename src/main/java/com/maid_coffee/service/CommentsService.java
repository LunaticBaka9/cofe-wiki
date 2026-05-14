package com.maid_coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.Comments;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.CommentsMapper;

import jakarta.annotation.Resource;

@Service
public class CommentsService {

    @Resource
    CommentsMapper commentsMapper;

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
                commentsMapper.updateReplyCount(comment.getRootId(), parent.getReplyCount() + 1);
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
        int count = comment.getLikeCount() != null ? comment.getLikeCount() : 0;
        if ("like".equals(action)) {
            count++;
        } else if ("unlike".equals(action)) {
            count = Math.max(0, count - 1);
        }
        commentsMapper.updateLikeCount(commentId, count);
    }
}
