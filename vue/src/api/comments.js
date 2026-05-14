//评论请求APi
import request from "../utils/request";

// 获取评论列表
export function getCommentList(targetId, targetType) {
    return request({
        url: `/comments/list/${targetId}/${targetType}`,
        method: "get",
    });
}

// 发表评论
export function addComment(data) {
    return request({
        url: "/comments/add",
        method: "post",
        data,
    });
}

// 删除评论
export function deleteComment(id) {
    return request({
        url: `/comments/${id}`,
        method: "delete",
    });
}

// 获取某条根评论的回复
export function getCommentReplies(rootId) {
    return request({
        url: `/comments/replies/${rootId}`,
        method: "get",
    });
}

// 点赞/取消点赞
export function toggleLike(commentId, userId, action) {
    return request({
        url: `/comments/like/${commentId}`,
        method: "post",
        params: { userId, action },
    });
}
