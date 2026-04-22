import { createRouter, createWebHistory } from "vue-router";
import routeIndex from "./routerIndex"; // 普通文件路由
import routeAdmin from "./routerAdmin";
import routeManager from "./routerManager";
import routerWiki from "./routerWiki";

const router = createRouter({
    history: createWebHistory(),
    routes: [...routeIndex, ...routeAdmin, ...routeManager, ...routerWiki],
});

export default router;
