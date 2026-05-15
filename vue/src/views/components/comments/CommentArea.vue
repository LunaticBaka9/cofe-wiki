<template>
    <div class="comment-area">
        <div v-if="comments.length === 0" class="empty-tip">
            暂无评论，快来发表第一条评论吧。
        </div>

        <div v-for="comment in comments" :key="comment.id" class="comment-item">
            <div class="comment-main">
                <el-avatar :size="40" :src="getAvatarUrl(comment.avatarUrl)">
                    {{ comment.userName?.charAt(0) }}
                </el-avatar>

                <div class="comment-body">
                    <div class="user-info">
                        <span class="user-name">{{ comment.userName }}</span>
                        <span class="time">{{
                            formatTime(comment.createdDate)
                        }}</span>
                    </div>

                    <div
                        class="content"
                        v-html="renderContent(comment.content)"
                    ></div>

                    <div class="actions">
                        <span
                            class="action-btn"
                            :class="{ liked: comment.isLiked }"
                            @click="handleLike(comment)"
                        >
                            <svg
                                width="14"
                                height="14"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                            >
                                <path
                                    d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"
                                />
                            </svg>
                            <span>{{ comment.likeCount || "点赞" }}</span>
                        </span>
                        <span class="action-btn" @click="toggleReply(comment)">
                            <svg
                                width="14"
                                height="14"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                            >
                                <path
                                    d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"
                                />
                            </svg>
                            <span>回复</span>
                        </span>
                        <span
                            v-if="comment.userId === currentUserId"
                            class="action-btn danger"
                            @click="handleDelete(comment.id)"
                        >
                            <svg
                                width="14"
                                height="14"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                            >
                                <polyline points="3 6 5 6 21 6" />
                                <path
                                    d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"
                                />
                            </svg>
                            <span>删除</span>
                        </span>
                    </div>

                    <div
                        v-if="showReplyId === comment.id"
                        class="reply-input-wrap"
                    >
                        <CommentInput
                            :placeholder="'回复 @' + comment.userName"
                            :parent-id="comment.id"
                            :parent-user-id="comment.userId"
                            :root-id="comment.rootId || comment.id"
                            @submit="handleReplySubmit"
                            @close="showReplyId = null"
                        />
                    </div>

                    <div v-if="comment.replies?.length" class="replies-wrap">
                        <div
                            v-for="reply in comment.replies"
                            :key="reply.id"
                            class="reply-item"
                        >
                            <el-avatar
                                :size="24"
                                :src="getAvatarUrl(reply.avatarUrl)"
                            >
                                {{ reply.userName?.charAt(0) }}
                            </el-avatar>

                            <div class="reply-body">
                                <div class="reply-user-info">
                                    <span class="user-name">{{
                                        reply.userName
                                    }}</span>
                                    <span
                                        v-if="reply.parentUserName"
                                        class="reply-to"
                                    >
                                        <span class="reply-arrow">回复</span>
                                        <span class="at-user"
                                            >@{{ reply.parentUserName }}</span
                                        >
                                    </span>
                                    <span class="time">{{
                                        formatTime(reply.createdDate)
                                    }}</span>
                                </div>

                                <div
                                    class="content"
                                    v-html="renderContent(reply.content)"
                                ></div>

                                <div class="actions">
                                    <span
                                        class="action-btn"
                                        :class="{ liked: reply.isLiked }"
                                        @click="handleLike(reply)"
                                    >
                                        <svg
                                            width="12"
                                            height="12"
                                            viewBox="0 0 24 24"
                                            fill="none"
                                            stroke="currentColor"
                                            stroke-width="2"
                                        >
                                            <path
                                                d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"
                                            />
                                        </svg>
                                        <span>{{
                                            reply.likeCount || "赞"
                                        }}</span>
                                    </span>
                                    <span
                                        class="action-btn"
                                        @click="toggleReply(reply)"
                                    >
                                        <svg
                                            width="12"
                                            height="12"
                                            viewBox="0 0 24 24"
                                            fill="none"
                                            stroke="currentColor"
                                            stroke-width="2"
                                        >
                                            <path
                                                d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"
                                            />
                                        </svg>
                                        <span>回复</span>
                                    </span>
                                    <span
                                        v-if="reply.userId === currentUserId"
                                        class="action-btn danger"
                                        @click="handleDelete(reply.id)"
                                    >
                                        <svg
                                            width="12"
                                            height="12"
                                            viewBox="0 0 24 24"
                                            fill="none"
                                            stroke="currentColor"
                                            stroke-width="2"
                                        >
                                            <polyline points="3 6 5 6 21 6" />
                                            <path
                                                d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"
                                            />
                                        </svg>
                                        <span>删除</span>
                                    </span>
                                </div>

                                <div
                                    v-if="showReplyId === reply.id"
                                    class="reply-input-wrap"
                                >
                                    <CommentInput
                                        :placeholder="'回复 @' + reply.userName"
                                        :parent-id="reply.id"
                                        :parent-user-id="reply.userId"
                                        :root-id="comment.rootId || comment.id"
                                        @submit="handleReplySubmit"
                                        @close="showReplyId = null"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-if="hasMore" class="load-more" @click="loadMore">
            加载更多评论
        </div>
    </div>
    <br /><br /><br /><br /><br /><br />
</template>

<script setup>
import { ref } from "vue";
import CommentInput from "./CommentInput.vue";

const props = defineProps({
    comments: {
        type: Array,
        default: () => [],
    },
    currentUserId: {
        type: Number,
        default: 0,
    },
    hasMore: {
        type: Boolean,
        default: false,
    },
});

const defaultAvatar = "/default-avatar.svg";

const emit = defineEmits(["like", "delete", "reply", "load-more"]);

const getAvatarUrl = (avatarUrl) => avatarUrl || defaultAvatar;

const showReplyId = ref(null);

const toggleReply = (comment) => {
    showReplyId.value = showReplyId.value === comment.id ? null : comment.id;
};

const handleLike = (comment) => {
    emit("like", comment);
};

const handleDelete = (id) => {
    emit("delete", id);
};

const handleReplySubmit = (data) => {
    emit("reply", data);
    showReplyId.value = null;
};

const loadMore = () => {
    emit("load-more");
};

const formatTime = (time) => {
    if (!time) return "";
    const date = new Date(time);
    const now = new Date();
    const diff = now - date;

    if (diff < 60000) return "刚刚";
    if (diff < 3600000) return `${Math.floor(diff / 60000)}分钟前`;
    if (date.toDateString() === now.toDateString()) {
        return `今天 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
    }
    const yesterday = new Date(now);
    yesterday.setDate(yesterday.getDate() - 1);
    if (date.toDateString() === yesterday.toDateString()) {
        return `昨天 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
    }
    return `${date.getMonth() + 1}月${date.getDate()}日 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
};

const renderContent = (content) => {
    if (!content) return "";
    return content;
};
</script>

<style scoped>
.comment-area {
    padding: 8px 0;
}

.empty-tip {
    text-align: center;
    padding: 40px 0;
    color: #999;
    font-size: 14px;
}

.comment-item {
    padding: 16px 0;
    border-bottom: 1px solid #f0f0f0;
}

.comment-item:last-child {
    border-bottom: none;
}

.comment-main {
    display: flex;
    gap: 12px;
}

.comment-body {
    flex: 1;
    min-width: 0;
}

.user-info {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 4px;
    flex-wrap: wrap;
}

.user-name {
    font-weight: 500;
    color: #00a1d6;
    font-size: 16px;
    cursor: pointer;
}

.user-name:hover {
    color: #00b5e5;
}

.time {
    color: #99a2aa;
    font-size: 12px;
}

.content {
    font-size: 16px;
    line-height: 1.6;
    color: #222;
    margin-bottom: 6px;
    word-break: break-word;
    white-space: pre-wrap;
}

.actions {
    display: flex;
    align-items: center;
    gap: 0;
    margin-bottom: 4px;
}

.action-btn {
    display: inline-flex;
    align-items: center;
    gap: 4px;
    cursor: pointer;
    color: #99a2aa;
    font-size: 13px;
    padding: 4px 8px;
    border-radius: 4px;
    transition:
        color 0.2s,
        background 0.2s;
    user-select: none;
}

.action-btn:hover {
    color: #00a1d6;
    background: #e8f4fd;
}

.action-btn.liked {
    color: #00a1d6;
}

.action-btn.danger:hover {
    color: #f04040;
    background: #fef0f0;
}

.action-btn svg {
    flex-shrink: 0;
}

.reply-input-wrap {
    margin: 8px 0 8px 0;
    padding: 12px;
    background: #f6f7f8;
    border-radius: 8px;
}

.replies-wrap {
    margin-top: 8px;
    padding-left: 0;
}

.reply-item {
    display: flex;
    gap: 8px;
    padding: 10px 0;
    border-bottom: 1px solid #f5f5f5;
}

.reply-item:last-child {
    border-bottom: none;
}

.reply-body {
    flex: 1;
    min-width: 0;
}

.reply-user-info {
    display: flex;
    align-items: center;
    gap: 4px;
    margin-bottom: 2px;
    flex-wrap: wrap;
}

.reply-user-info .user-name {
    font-size: 15px;
}

.reply-to {
    color: #99a2aa;
    font-size: 15px;
    display: flex;
    align-items: center;
    gap: 4px;
}

.reply-arrow {
    color: #99a2aa;
}

.at-user {
    color: #00a1d6;
    cursor: pointer;
}

.at-user:hover {
    color: #00b5e5;
}

.reply-body .content {
    font-size: 16px;
    margin-bottom: 4px;
}

.reply-body .actions {
    gap: 0;
}

.reply-body .action-btn {
    font-size: 12px;
    padding: 2px 6px;
}

.load-more {
    text-align: center;
    padding: 12px;
    color: #00a1d6;
    cursor: pointer;
    font-size: 13px;
    border-top: 1px solid #f0f0f0;
    transition: background 0.2s;
}

.load-more:hover {
    background: #f6f7f8;
}
</style>
