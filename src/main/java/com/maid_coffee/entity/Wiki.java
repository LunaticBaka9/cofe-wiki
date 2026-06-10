package com.maid_coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wiki {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String tags;
    private Date updateDate;
}
