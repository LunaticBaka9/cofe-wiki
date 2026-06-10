package com.maid_coffee.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comments {
    Long id;
    Long targetId;
    String targetType;
    Long rootId;
    Long parentId;
    Long parentUserId;
    String avatarUrl;
    Long userId;
    String userName;
    String parentUserName;
    String content;
    Integer likeCount;
    Integer replyCount;
    Boolean isTop;
    Boolean status;
    Date createdDate;
    Date updatedDate;
    Boolean isLiked;
    List<Comments> replies;
}
