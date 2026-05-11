package com.maid_coffee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.maid_coffee.common.Result;
import com.maid_coffee.entity.Comments;
import com.maid_coffee.service.CommentsService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Resource
    CommentsService commentsService;

    @GetMapping("/selectRootComments")
    public Result selectRootComments(Comments comments) {
        List<Comments> list = commentsService.selectRootComments(comments);
        return Result.success(list);
    }

    @GetMapping("/selectRepliesByRootId/{rootId}")
    public Result selectRepliesByRootId(@PathVariable Long rootId) {
        List<Comments> list = commentsService.selectRepliesByRootId(rootId);
        return Result.success(list);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Long id) {
        Comments comment = commentsService.selectById(id);
        return Result.success(comment);
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              Comments comments) {
        PageInfo<Comments> pageInfo = commentsService.selectPage(pageNum, pageSize, comments);
        return Result.success(pageInfo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Comments comment) {
        commentsService.add(comment);
        return Result.success();
    }

    @PutMapping("/delete")
    public Result delete(@RequestBody Comments comment) {
        commentsService.delete(comment);
        return Result.success();
    }
}
