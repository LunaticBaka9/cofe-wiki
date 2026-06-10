package com.maid_coffee.mapper;

import com.maid_coffee.entity.LikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeRecordMapper {
    LikeRecord selectByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);

    List<LikeRecord> selectByCommentIdsAndUserId(@Param("commentIds") List<Long> commentIds, @Param("userId") Long userId);

    int insert(LikeRecord likeRecord);

    int deleteByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
