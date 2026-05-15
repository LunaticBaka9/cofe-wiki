const routeManager = [
    {
        path: "/userManager",
        name: "userManager",
        component: () => import("@/views/Manager/UserManager.vue"),
        meta: {
            requiresAuth: true,
            roles: ["admin"],
        },
    },
    {
        path: "/shopManager",
        name: "shopManager",
        component: () => import("@/views/Manager/ShopManager.vue"),
        meta: {
            requiresAuth: true,
            roles: ["admin", "editor"],
        },
    },
    {
        path: "/roleManager",
        name: "roleManager",
        component: () => import("@/views/Manager/RoleManager.vue"),
        meta: {
            requiresAuth: true,
            roles: ["admin"],
        },
    },
    {
        path: "/noPermission",
        name: "noPermission",
        component: () => import("@/views/Manager/NoPermission.vue"),
    },
];

export default routeManager;
