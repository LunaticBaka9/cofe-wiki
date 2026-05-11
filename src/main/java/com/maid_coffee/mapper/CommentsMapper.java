package com.maid_coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.maid_coffee.entity.Comments;

@Mapper
public interface CommentsMapper {
    /**
     * 根据目标ID获取一级评论列表
     */
    List<Comments> selectRootComments(Comments comments);

    /**
     * 根据根评论ID获取所有回复列表
     */
    List<Comments> selectRepliesByRootId(Comments comments);

    /**
     * 根据ID获取单条评论
     */
    Comments selectById(@Param("id") Long id);

    /**
     * 插入新评论
     */
    int insert(Comments comment);

    /**
     * 更新评论的回复数
     */
    int updateReplyCount(@Param("commentId") Long commentId, @Param("count") Integer count);

    /**
     * 更新评论的点赞数
     */
    int updateLikeCount(@Param("commentId") Long commentId, @Param("count") Integer count);

    /**
     * 更新评论状态（软删除）
     */
    int updateStatus(Comments comment);
}
