<template>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        text-color="#ff007f"
        :collapse-transition="false"
        :ellipsis="false"
    >
        <el-menu-item index="0">
            <router-link to="/">
                <img style="width: 100px" src="" alt="咖啡厅Wiki" />
            </router-link>
        </el-menu-item>
        <el-menu-item index="1">
            <router-link to="/"> 首页 </router-link>
        </el-menu-item>
        <el-sub-menu index="2">
            <template #title> <span>咖啡厅</span></template>
            <router-link to="/wiki/cafeShop" class="custom-link">
                <el-menu-item index="2-1">咖啡厅介绍</el-menu-item>
            </router-link>
            <router-link to="/shop" class="custom-link">
                <el-menu-item index="2-2">店面列表</el-menu-item>
            </router-link>
            <router-link to="/search" class="custom-link">
                <el-menu-item index="2-3">店面搜索</el-menu-item>
            </router-link>
        </el-sub-menu>
        <el-sub-menu index="3">
            <template #title> <span>咖啡</span></template>
            <router-link to="/wiki/coffee" class="custom-link">
                <el-menu-item index="3-1">咖啡简介</el-menu-item>
            </router-link>
            <router-link to="/wiki/coffeeKind" class="custom-link">
                <el-menu-item index="3-2">咖啡种类</el-menu-item>
            </router-link>
        </el-sub-menu>
        <el-sub-menu index="4">
            <template #title>
                <span>
                    <el-icon><UserFilled /></el-icon>
                    {{ data.user?.name }}
                </span>
            </template>
            <span v-if="!data.user.name">
                <router-link to="/login">
                    <el-menu-item index="4-1"> 登录 </el-menu-item>
                </router-link>
            </span>
            <span v-else>
                <el-menu-item index="4-2" @click="logout">退出登录</el-menu-item>
            </span>
        </el-sub-menu>
    </el-menu>
</template>

<script setup name="header">
import { reactive, computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const data = reactive({
    user: JSON.parse(localStorage.getItem("code_user") || "{}"),
});

// 根据当前路由计算激活的菜单项
const activeIndex = computed(() => {
    const path = route.path;

    // 首页
    if (path === "/" || path === "/index") {
        return "1";
    }

    // 咖啡厅相关页面
    if (path.startsWith("/wiki/cafeShop")) {
        return "2-1";
    }
    if (path.startsWith("/shop")) {
        return "2-2";
    }
    if (path.startsWith("/search")) {
        return "2-3";
    }

    // 咖啡相关页面
    if (path.startsWith("/wiki/coffee")) {
        return "3-1";
    }
    if (path.startsWith("/wiki/coffeeKind")) {
        return "3-2";
    }

    // 登录/退出相关
    if (path.startsWith("/login")) {
        return "4-1";
    }

    // 默认返回首页
    return "1";
});

const logout = () => {
    localStorage.removeItem("code_user");
    location.href = "/login";
};
</script>

<style scoped>
* {
    text-decoration: none;
    font-family: Arial, sans-serif;
    font-size: 14px;
}

.el-menu--horizontal > .el-menu-item:nth-child(1) {
    margin-right: auto;
}

.header-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    gap: 10px;
}

.el-menu-item a {
    color: #ff007f;
    display: inline-block;
    padding: 12px;
    padding-right: 12px;
    padding-left: 12px;
}
</style>
