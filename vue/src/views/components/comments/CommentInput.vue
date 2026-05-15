<template>
    <div class="comment-input-wrapper">
        <div class="input-area">
            <textarea
                ref="textareaRef"
                v-model="text"
                :placeholder="placeholder"
                class="comment-textarea"
                @focus="handleFocus"
                @blur="handleBlur"
            ></textarea>

            <div class="toolbar">
                <div class="toolbar-left">
                    <span
                        class="emoji-btn"
                        @click="toggleEmojiPicker"
                        title="表情"
                    >
                        <svg
                            width="18"
                            height="18"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                        >
                            <circle cx="12" cy="12" r="10" />
                            <path d="M8 14s1.5 2 4 2 4-2 4-2" />
                            <line x1="9" y1="9" x2="9.01" y2="9" />
                            <line x1="15" y1="9" x2="15.01" y2="9" />
                        </svg>
                    </span>
                </div>
                <div class="toolbar-right">
                    <span class="cancel-btn" @click="handleCancel">取消</span>
                    <button
                        class="submit-btn"
                        :disabled="!text.trim()"
                        :class="{ loading: submitting }"
                        @click="handleSubmit"
                    >
                        {{ submitting ? "发布中..." : "发布" }}
                    </button>
                </div>
            </div>
        </div>

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

const emit = defineEmits(["submit", "close"]);

const text = ref("");
const showEmoji = ref(false);
const submitting = ref(false);
const textareaRef = ref(null);
const cursorPosition = ref(0);

const handleFocus = () => {
    const textarea = textareaRef.value;
    if (textarea) {
        cursorPosition.value = textarea.selectionStart || text.value.length;
    }
};

const handleBlur = () => {
    const textarea = textareaRef.value;
    if (textarea) {
        cursorPosition.value = textarea.selectionStart || text.value.length;
    }
};

const toggleEmojiPicker = () => {
    showEmoji.value = !showEmoji.value;
    if (showEmoji.value) {
        const textarea = textareaRef.value;
        if (textarea) {
            cursorPosition.value = textarea.selectionStart || text.value.length;
        }
    }
};

const insertEmoji = (emoji) => {
    const start = cursorPosition.value;
    const end = cursorPosition.value;
    const newText =
        text.value.substring(0, start) + emoji + text.value.substring(end);
    text.value = newText;
    cursorPosition.value = start + emoji.length;

    nextTick(() => {
        const textarea = textareaRef.value;
        if (textarea) {
            textarea.focus();
            textarea.setSelectionRange(
                cursorPosition.value,
                cursorPosition.value,
            );
        }
    });
};

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

const handleCancel = () => {
    text.value = "";
    showEmoji.value = false;
    emit("close");
};

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

.input-area {
    background: #fff;
}

.comment-textarea {
    width: 100%;
    min-height: 60px;
    border: 1px solid #e4e7ed;
    border-radius: 6px;
    padding: 10px 12px;
    font-size: 13px;
    line-height: 1.6;
    color: #222;
    resize: none;
    outline: none;
    transition: border-color 0.2s;
    font-family: inherit;
    box-sizing: border-box;
}

.comment-textarea:focus {
    border-color: #00a1d6;
}

.comment-textarea::placeholder {
    color: #b8c0c8;
}

.toolbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 8px;
}

.toolbar-left {
    display: flex;
    align-items: center;
}

.toolbar-right {
    display: flex;
    align-items: center;
    gap: 8px;
}

.emoji-btn {
    cursor: pointer;
    color: #99a2aa;
    display: flex;
    align-items: center;
    padding: 4px;
    border-radius: 4px;
    transition:
        color 0.2s,
        background 0.2s;
}

.emoji-btn:hover {
    color: #00a1d6;
    background: #e8f4fd;
}

.cancel-btn {
    cursor: pointer;
    color: #99a2aa;
    font-size: 13px;
    padding: 4px 8px;
    border-radius: 4px;
    transition: color 0.2s;
}

.cancel-btn:hover {
    color: #666;
}

.submit-btn {
    background: #ffb6c1;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 6px 16px;
    font-size: 13px;
    cursor: pointer;
    transition: background 0.2s;
    font-family: inherit;
}

.submit-btn:hover:not(:disabled) {
    background: #00b5e5;
}

.submit-btn:disabled {
    background: #c0c4cc;
    cursor: not-allowed;
}

.submit-btn.loading {
    opacity: 0.7;
    cursor: wait;
}

.emoji-picker {
    position: absolute;
    bottom: calc(100% + 8px);
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
