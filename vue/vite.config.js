import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
    plugins: [vue()],
    optimizeDeps: {
        include: ["element-plus"],
    },
    resolve: {
        alias: {
            "@": "/src",
        },
    },
    server: {
        proxy: {
            "/api": {
                target: "http://localhost:8081/",
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, ""),
            },
        },
    },
});
