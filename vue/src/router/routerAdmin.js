const routeAdmin = [
    {
        path: "/login",
        name: "login",
        component: () => import("../views/admin/login.vue"),
    },
    {
        path: "/register",
        name: "register",
        component: () => import("../views/admin/register.vue"),
    },
];

export default routeAdmin;
