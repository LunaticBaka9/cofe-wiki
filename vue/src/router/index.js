import { createRouter, createWebHistory } from "vue-router";
import routeIndex from "./routerIndex"; // 普通文件路由
import routeAdmin from "./routerAdmin";
import routeManager from "./routerManager";
import routerWiki from "./routerWiki";

const router = createRouter({
    history: createWebHistory(),
    routes: [...routeIndex, ...routeAdmin, ...routeManager, ...routerWiki],
});

const whiteList = ["/login", "/register"];

router.beforeEach((to, from, next) => {
    const userStr = localStorage.getItem("code_user");
    if (userStr) {
        next();
    } else {
        if (whiteList.indexOf(to.path) !== -1) {
            next();
        } else {
            next("/login");
        }
    }
});

export default router;
