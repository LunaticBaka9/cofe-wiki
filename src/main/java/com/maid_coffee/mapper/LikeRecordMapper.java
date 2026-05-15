package com.maid_coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.maid_coffee.entity.LikeRecord;

@Mapper
public interface LikeRecordMapper {
    LikeRecord selectByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);

    List<LikeRecord> selectByCommentIdsAndUserId(@Param("commentIds") List<Long> commentIds, @Param("userId") Long userId);

    int insert(LikeRecord likeRecord);

    int deleteByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
