<!-- src/components/comment/CommentInput.vue -->
<template>
    <div class="comment-input-wrapper">
        <div class="input-box">
            <el-input
                ref="textareaRef"
                v-model="text"
                type="textarea"
                :placeholder="placeholder"
                :autosize="{ minRows: 3, maxRows: 6 }"
                resize="none"
                @focus="handleFocus"
                @blur="handleBlur"
            />

            <!-- 工具栏 -->
            <div class="toolbar">
                <!-- 表情按钮 -->
                <div class="emoji-btn" @click="toggleEmojiPicker">
                    <svg-icon icon-class="emoji" />
                </div>

                <!-- 发布按钮 -->
                <el-button
                    type="primary"
                    size="small"
                    :disabled="!text.trim()"
                    :loading="submitting"
                    @click="handleSubmit"
                >
                    发布
                </el-button>
            </div>

            <!-- 表情选择面板 -->
            <div v-if="showEmoji" class="emoji-picker">
                <span
                    v-for="(emoji, index) in emojiList"
                    :key="index"
                    class="emoji-item"
                    @click="insertEmoji(emoji)"
                >
                    {{ emoji }}
                </span>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, nextTick } from "vue";
import { emojiList } from "@/utils/emoji";

const props = defineProps({
    placeholder: {
        type: String,
        default: "请输入评论...",
    },
    parentId: {
        type: Number,
        default: 0,
    },
    rootId: {
        type: Number,
        default: 0,
    },
    parentUserId: {
        type: Number,
        default: 0,
    },
});

const emit = defineEmits(["submit"]);

const text = ref("");
const showEmoji = ref(false);
const submitting = ref(false);
const textareaRef = ref(null);
const cursorPosition = ref(0);

// 记忆光标位置
const handleFocus = () => {
    const textarea = textareaRef.value?.textarea;
    if (textarea) {
        cursorPosition.value = textarea.selectionStart || text.value.length;
    }
};

const handleBlur = () => {
    const textarea = textareaRef.value?.textarea;
    if (textarea) {
        cursorPosition.value = textarea.selectionStart || text.value.length;
    }
};

// 切换表情面板
const toggleEmojiPicker = () => {
    showEmoji.value = !showEmoji.value;
    if (showEmoji.value) {
        // 打开表情面板时，记录当前光标位置
        const textarea = textareaRef.value?.textarea;
        if (textarea) {
            cursorPosition.value = textarea.selectionStart || text.value.length;
        }
    }
};

// 插入表情
const insertEmoji = (emoji) => {
    const start = cursorPosition.value;
    const end = cursorPosition.value;
    const newText =
        text.value.substring(0, start) + emoji + text.value.substring(end);
    text.value = newText;

    // 计算新的光标位置（表情可能占2个字符长度）
    cursorPosition.value = start + emoji.length;

    // 手动设置光标位置
    nextTick(() => {
        const textarea = textareaRef.value?.textarea;
        if (textarea) {
            textarea.focus();
            textarea.setSelectionRange(
                cursorPosition.value,
                cursorPosition.value,
            );
        }
    });
};

// 提交评论
const handleSubmit = async () => {
    if (!text.value.trim()) return;

    submitting.value = true;
    try {
        await emit("submit", {
            content: text.value,
            parentId: props.parentId,
            rootId: props.rootId,
            parentUserId: props.parentUserId,
        });
        text.value = "";
        showEmoji.value = false;
    } finally {
        submitting.value = false;
    }
};

// 暴露重置方法
const reset = () => {
    text.value = "";
    showEmoji.value = false;
};

defineExpose({ reset });
</script>

<style scoped>
.comment-input-wrapper {
    position: relative;
}

.input-box {
    border: 1px solid #e4e7ed;
    border-radius: 4px;
    padding: 8px;
}

.toolbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 8px;
}

.emoji-btn {
    cursor: pointer;
    font-size: 20px;
    transition: transform 0.2s;
}

.emoji-btn:hover {
    transform: scale(1.2);
}

.emoji-picker {
    position: absolute;
    bottom: 100%;
    left: 0;
    background: white;
    border: 1px solid #e4e7ed;
    border-radius: 8px;
    padding: 12px;
    display: flex;
    flex-wrap: wrap;
    gap: 4px;
    max-width: 320px;
    max-height: 200px;
    overflow-y: auto;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    z-index: 100;
}

.emoji-item {
    cursor: pointer;
    font-size: 24px;
    padding: 4px;
    border-radius: 4px;
    transition: background 0.2s;
}

.emoji-item:hover {
    background: #f0f2f5;
}
</style>
