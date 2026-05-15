import { createRouter, createWebHistory } from "vue-router";
import routeIndex from "./routerIndex"; // 普通文件路由
import routeAdmin from "./routerAdmin";
import routeManager from "./routerManager";
import routerWiki from "./routerWiki";

const router = createRouter({
    history: createWebHistory(),
    routes: [...routeIndex, ...routeAdmin, ...routeManager, ...routerWiki],
});

router.beforeEach((to, from, next) => {
    const user = JSON.parse(window.localStorage.getItem("code_user") || "{}");
    const isLoggedIn = Boolean(user && user.userId);

    if (to.meta.requiresAuth && !isLoggedIn) {
        return next("/login");
    }

    if (to.meta.roles && to.meta.roles.length > 0) {
        if (!isLoggedIn) {
            return next("/login");
        }
        if (!to.meta.roles.includes(user.userType)) {
            return next("/noPermission");
        }
    }

    next();
});

export default router;
