<template>
    <el-main class="login-card">
        <div style="width: 350px; height: auto; background-color: #ffff; border-radius: 5px; padding: 20px">
            <el-form ref="formRef" :model="data.form" :rules="data.rules" style="padding: 20px 30px 10px 0">
                <div style="margin: 20px 0; text-align: center; font-weight: bold; font-size: 24px">注 册</div>
                <el-form-item prop="username">
                    <el-input
                        v-model="data.form.username"
                        autocomplete="off"
                        size="large"
                        prefix-icon="User"
                        placeholder="请输入账号"
                    />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        v-model="data.form.password"
                        autocomplete="off"
                        show-password="true"
                        size="large"
                        prefix-icon="Lock"
                        placeholder="请输入密码"
                    />
                </el-form-item>
                <el-form-item prop="confirmPassword">
                    <el-input
                        v-model="data.form.confirmPassword"
                        autocomplete="off"
                        show-password="true"
                        size="large"
                        prefix-icon="Lock"
                        placeholder="请确认输入密码"
                    />
                </el-form-item>
                <el-form-item prop="email">
                    <el-input
                        v-model="data.form.email"
                        autocomplete="off"
                        size="large"
                        prefix-icon="Message"
                        placeholder="请确认输入邮箱"
                    />
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input
                        v-model="data.form.phone"
                        autocomplete="off"
                        size="large"
                        prefix-icon="Phone"
                        placeholder="请确认输入电话号码"
                    />
                </el-form-item>
                <div>
                    <el-button size="large" type="primary" style="width: 100%; margin: 20px 0" @click="register">
                        注 册
                    </el-button>
                </div>
                <div style="text-align: right">已经有账号？请<a style="color: #274afa" href="/login">登录</a></div>
            </el-form>
        </div>
    </el-main>
</template>

<script setup name="login" lang="ts">
import { ref, reactive } from "vue";
import { ElMessage } from "element-plus";
import router from "../../router";
import request from "../../utils/request";

const formRef = ref();

const validatePass2 = (rule, value, callback) => {
    if (value === "") {
        callback(new Error("请再次输入密码确认！"));
    } else if (value !== data.form.password) {
        callback(new Error("两次密码不一致！"));
    } else {
        callback();
    }
};

const checkEmail = (rule, value, callback) => {
    const regEmail = /^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})$/;
    if (value === "") {
        callback(new Error("请输入邮箱"));
    } else if (!regEmail.test(value)) {
        callback(new Error("请输入有效的邮箱"));
    } else {
        callback();
    }
};

const data = reactive({
    form: {},
    rules: {
        username: [
            { required: true, message: "请输入账号", trigger: "blur" },
            { min: 3, message: "至少输入3位", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        confirmPassword: [{ validator: validatePass2, trigger: "blur" }],
        email: [{ validator: checkEmail, trigger: "blur" }],
    },
});

const register = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            request.post("/register", data.form).then((res) => {
                if (res.code === "200") {
                    ElMessage.success("注册成功");
                    router.push("/login");
                } else {
                    ElMessage.error(res.msg);
                }
            });
        }
    });
};
</script>

<style scoped>
/* 登录表单 */
.login-card {
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    margin-top: 0;
}
</style>
