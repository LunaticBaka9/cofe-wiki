const routerWiki = [
    {
        path: "/wiki/cafeShop",
        name: "cafeShop",
        component: () => import("../views/index/wiki/cafeShop.vue"),
    },
    {
        path: "/wiki/maidCafe",
        name: "maidCafe",
        component: () => import("../views/index/wiki/maidCofe.vue"),
    },
    {
        path: "/wiki/coffee",
        name: "coffee",
        component: () => import("../views/index/wiki/coffee.vue"),
    },
    {
        path: "/wiki/coffeeCulture",
        name: "coffeeCulture",
        component: () => import("../views/index/wiki/coffeeCulture.vue"),
    },
    {
        path: "/wiki/coffeeKind",
        name: "coffeeKind",
        component: () => import("../views/index/wiki/coffeeKind.vue"),
    },
];

export default routerWiki;
