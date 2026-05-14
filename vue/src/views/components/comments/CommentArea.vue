<!-- src/components/comment/CommentArea.vue -->
<template>
    <div class="comment-area">
        <div v-if="comments.length === 0" class="empty-tip">
            暂无评论，快来发表第一条评论吧。
        </div>
        <!-- 一级评论列表 -->
        <div v-for="comment in comments" :key="comment.id" class="comment-item">
            <div class="comment-main">
                <!-- 用户头像 -->
                <el-avatar :size="40" :src="getAvatarUrl(comment.avatarUrl)">
                    {{ comment.userName?.charAt(0) }}
                </el-avatar>

                <div class="comment-body">
                    <!-- 用户信息 -->
                    <div class="user-info">
                        <span class="user-name">{{ comment.userName }}</span>
                        <span class="time">{{
                            formatTime(comment.createdAt)
                        }}</span>
                    </div>

                    <!-- 评论内容 -->
                    <div
                        class="content"
                        v-html="renderContent(comment.content)"
                    ></div>

                    <!-- 操作栏 -->
                    <div class="actions">
                        <span class="action-btn" @click="handleLike(comment)">
                            {{ comment.isLiked ? "❤️" : "🤍" }}
                            {{ comment.likeCount || "" }}
                        </span>
                        <span class="action-btn" @click="toggleReply(comment)">
                            💬 回复
                        </span>
                        <span
                            v-if="comment.userId === currentUserId"
                            class="action-btn"
                            @click="handleDelete(comment.id)"
                        >
                            🗑️ 删除
                        </span>
                    </div>

                    <!-- 回复输入框 -->
                    <div v-if="showReplyId === comment.id" class="reply-input">
                        <CommentInput
                            :placeholder="`回复 @${comment.userName}`"
                            :parent-id="comment.id"
                            :parent-user-id="comment.userId"
                            :root-id="comment.rootId || comment.id"
                            @submit="handleReplySubmit"
                        />
                    </div>

                    <!-- 楼中楼回复列表 -->
                    <div v-if="comment.replies?.length" class="replies">
                        <div
                            v-for="reply in comment.replies"
                            :key="reply.id"
                            class="reply-item"
                        >
                            <el-avatar
                                :size="28"
                                :src="getAvatarUrl(reply.avatarUrl)"
                            >
                                {{ reply.userName?.charAt(0) }}
                            </el-avatar>

                            <div class="reply-body">
                                <div class="user-info">
                                    <span class="user-name">{{
                                        reply.userName
                                    }}</span>
                                    <span
                                        v-if="reply.parentUserName"
                                        class="reply-to"
                                    >
                                        回复 @{{ reply.parentUserName }}
                                    </span>
                                    <span class="time">{{
                                        formatTime(reply.createdAt)
                                    }}</span>
                                </div>

                                <div
                                    class="content"
                                    v-html="renderContent(reply.content)"
                                ></div>

                                <div class="actions">
                                    <span
                                        class="action-btn"
                                        @click="handleLike(reply)"
                                    >
                                        {{ reply.isLiked ? "❤️" : "🤍" }}
                                        {{ reply.likeCount || "" }}
                                    </span>
                                    <span
                                        class="action-btn"
                                        @click="toggleReply(reply)"
                                    >
                                        💬 回复
                                    </span>
                                    <span
                                        v-if="reply.userId === currentUserId"
                                        class="action-btn"
                                        @click="handleDelete(reply.id)"
                                    >
                                        🗑️ 删除
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 加载更多 -->
        <div v-if="hasMore" class="load-more" @click="loadMore">
            加载更多评论
        </div>
    </div>
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

// 切换回复输入框
const toggleReply = (comment) => {
    showReplyId.value = showReplyId.value === comment.id ? null : comment.id;
};

// 处理点赞
const handleLike = (comment) => {
    emit("like", comment);
};

// 处理删除
const handleDelete = (id) => {
    emit("delete", id);
};

// 处理回复提交
const handleReplySubmit = (data) => {
    emit("reply", data);
    showReplyId.value = null;
};

// 加载更多
const loadMore = () => {
    emit("load-more");
};

// 格式化时间
const formatTime = (time) => {
    if (!time) return "";
    const date = new Date(time);
    const now = new Date();
    const diff = now - date;

    // 1分钟内
    if (diff < 60000) return "刚刚";
    // 1小时内
    if (diff < 3600000) return `${Math.floor(diff / 60000)}分钟前`;
    // 今天内
    if (date.toDateString() === now.toDateString()) {
        return `今天 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
    }
    // 昨天
    const yesterday = new Date(now);
    yesterday.setDate(yesterday.getDate() - 1);
    if (date.toDateString() === yesterday.toDateString()) {
        return `昨天 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
    }
    // 更早
    return `${date.getMonth() + 1}月${date.getDate()}日 ${date.getHours().toString().padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}`;
};

// 渲染内容（将表情文本转为实际表情）
const renderContent = (content) => {
    if (!content) return "";
    // 这里可以扩展为支持富文本、@用户等
    return content;
};
</script>

<style scoped>
.comment-area {
    padding: 16px 0;
}

.comment-item {
    margin-bottom: 20px;
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
}

.user-name {
    font-weight: 600;
    color: #1e80ff;
    font-size: 14px;
}

.reply-to {
    color: #1e80ff;
    font-size: 13px;
}

.time {
    color: #999;
    font-size: 12px;
}

.content {
    font-size: 14px;
    line-height: 1.6;
    color: #333;
    margin-bottom: 8px;
    word-break: break-word;
}

.actions {
    display: flex;
    gap: 16px;
    margin-bottom: 8px;
}

.action-btn {
    cursor: pointer;
    color: #666;
    font-size: 13px;
    transition: color 0.2s;
}

.action-btn:hover {
    color: #1e80ff;
}

.reply-input {
    margin: 8px 0;
}

.replies {
    background: #f6f7f8;
    border-radius: 4px;
    padding: 12px;
    margin-top: 8px;
}

.reply-item {
    display: flex;
    gap: 8px;
    margin-bottom: 12px;
}

.reply-item:last-child {
    margin-bottom: 0;
}

.reply-body {
    flex: 1;
    min-width: 0;
}

.load-more {
    text-align: center;
    padding: 12px;
    color: #1e80ff;
    cursor: pointer;
    font-size: 14px;
}

.load-more:hover {
    background: #f6f7f8;
}
</style>
