import { createApp, reactive } from "vue";
import App from "./App.vue";
import ElementPlus from "element-plus"; // 引入 element-plus第三方UI组件库
import "element-plus/dist/index.css"; // 引入 element-plus 样式
import * as ElementPlusIconsVue from "@element-plus/icons-vue"; // 引入 element-plus 图标库
import router from "./router/index";
import "./assets/css/global.css";
import Axios from "axios";

const app = createApp(App);
app.use(router);
app.use(Axios);
app.use(ElementPlus); // 使用ElementPlus第三方组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}
app.mount("#app");
