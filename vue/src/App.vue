<template>
    <el-config-provider :locale="locale">
        <div class="common-layout" style="overflow: hidden">
            <el-container>
                <el-header v-if="showLayout" height="4rem" class="header">
                    <Header />
                </el-header>
                <el-container>
                    <el-aside v-if="showLayout" width="12rem">
                        <Aside />
                    </el-aside>

                    <router-view></router-view>
                </el-container>
            </el-container>
        </div>
    </el-config-provider>
</template>

<script setup>
import { computed } from "vue";
import { useRoute } from "vue-router";
import locale from "element-plus/es/locale/lang/zh-cn";
import Header from "@/views/components/header.vue";
import Aside from "@/views/components/aside.vue";

const route = useRoute();
const showLayout = computed(() => {
    const path = route.path;
    return path !== "/login" && path !== "/register";
});
</script>
