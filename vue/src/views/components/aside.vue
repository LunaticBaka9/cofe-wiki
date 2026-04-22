<template>
    <el-col>
        <el-menu :default-active="activeIndex">
            <router-link to="/" class="custom-link">
                <el-menu-item class="card-title-primary">
                    <span class="text">首页</span>
                </el-menu-item>
            </router-link>
            <el-menu-item-group>
                <router-link to="/wiki/cafeShop" class="custom-link">
                    <el-menu-item index="1-1">咖啡厅介绍</el-menu-item>
                </router-link>
                <router-link to="/shop" class="custom-link">
                    <el-menu-item index="1-2">店面列表</el-menu-item>
                </router-link>
                <router-link to="/search" class="custom-link">
                    <el-menu-item index="1-3">店面搜索</el-menu-item>
                </router-link>
            </el-menu-item-group>
            <router-link to="/wiki/coffee" class="custom-link">
                <el-menu-item class="card-title-primary">
                    <span class="text">咖啡</span>
                </el-menu-item>
            </router-link>
            <el-menu-item-group title="">
                <router-link to="/wiki/coffeeKind" class="custom-link">
                    <el-menu-item index="2-1">咖啡种类</el-menu-item>
                </router-link>
                <router-link to="/wiki/coffeeCulture" class="custom-link">
                    <el-menu-item index="2-2">咖啡文化</el-menu-item>
                </router-link>
            </el-menu-item-group>
            <router-link to="" class="custom-link" v-if="data.user.name">
                <el-menu-item class="card-title-primary">
                    <span class="text">管理</span>
                </el-menu-item>
            </router-link>
            <el-menu-item-group v-if="data.user.name">
                <router-link to="/shopManager" class="custom-link">
                    <el-menu-item index="3-1">店面管理</el-menu-item>
                </router-link>
                <router-link to="/adminManager" class="custom-link">
                    <el-menu-item index="3-2">管理员管理</el-menu-item>
                </router-link>
                <router-link to="/userManager" class="custom-link">
                    <el-menu-item index="3-3">用户管理</el-menu-item>
                </router-link>
            </el-menu-item-group>
        </el-menu>
        <br /><br /><br /><br />
    </el-col>
</template>

<script setup>
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
        return "1-1";
    }
    if (path.startsWith("/shop")) {
        return "1-2";
    }
    if (path.startsWith("/search")) {
        return "1-3";
    }

    // 咖啡相关页面
    if (path.startsWith("/wiki/coffee")) {
        return "2-1";
    }
    if (path.startsWith("/wiki/coffeeKind")) {
        return "2-1";
    }
    if (path.startsWith("/wiki/coffeeCulture")) {
        return "2-2";
    }

    // 管理相关页面
    if (path.startsWith("/shopManager")) {
        return "3-1";
    }
    if (path.startsWith("/adminManager")) {
        return "3-2";
    }
    if (path.startsWith("/userManager")) {
        return "3-3";
    }

    // 默认返回首页
    return "1";
});
</script>
<style scoped>
.custom-link {
    text-decoration: none;
}
</style>
