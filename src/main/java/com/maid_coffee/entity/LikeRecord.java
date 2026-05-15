package com.maid_coffee.entity;

import java.util.Date;

import lombok.Data;

@Data
public class LikeRecord {
    Long id;
    Long commentId;
    Long userId;
    Date createdDate;
}
