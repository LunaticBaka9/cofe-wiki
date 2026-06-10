package com.maid_coffee.controller;

import com.maid_coffee.common.Result;
import com.maid_coffee.entity.Comments;
import com.maid_coffee.service.CommentsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Resource
    CommentsService commentsService;

    @GetMapping("/list/{targetId}/{targetType}")
    public Result list(@PathVariable Long targetId, @PathVariable String targetType,
            @RequestParam(required = false) Long userId) {
        List<Comments> list = commentsService.selectRootCommentsWithReplies(targetId, targetType, userId);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Comments comment) {
        commentsService.add(comment);
        return Result.success();
    }

    @GetMapping("/replies/{rootId}")
    public Result replies(@PathVariable Long rootId) {
        List<Comments> replies = commentsService.selectRepliesByRootId(rootId);
        return Result.success(replies);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        commentsService.delete(id);
        return Result.success();
    }

    @PostMapping("/like/{commentId}")
    public Result toggleLike(@PathVariable Long commentId,
            @RequestParam Long userId,
            @RequestParam String action) {
        commentsService.toggleLike(commentId, userId, action);
        return Result.success();
    }
}
