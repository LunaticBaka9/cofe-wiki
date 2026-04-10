const routeIndex = [
    {
        path: "/",
        name: "indexWeb",
        component: () => import("../views/index/index.vue"),
    },
    {
        path: "/shop",
        name: "shop",
        component: () => import("../views/index/shops/shop.vue"),
    },
    {
        path: "/search",
        name: "search",
        component: () => import("../views/index/shops/search.vue"),
    },
    {
        path: "/shopinfo",
        name: "shopinfo",
        component: () => import("../views/index/shops/shopinfo.vue"),
    },
];

export default routeIndex;
