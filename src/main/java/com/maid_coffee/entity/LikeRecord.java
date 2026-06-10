package com.maid_coffee.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LikeRecord {
    Long id;
    Long commentId;
    Long userId;
    Date createdDate;
}
