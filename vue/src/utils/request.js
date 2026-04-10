import axios from "axios";
import { ElMessage } from "element-plus";

const request = axios.create({
    baseURL: "/api",
    timeout: 30000,
});

request.interceptors.request.use(
    (config) => {
        config.headers["Content-Type"] = "application/json;charset=utf-8";
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

request.interceptors.response.use(
    (response) => {
        const res = response.data;
        if (typeof res === "string") {
            res = res ? JSON.parse(res) : res; // eslint-disable-line no-unused-vars
        }
        return res;
    },
    (error) => {
        if (error.response.status === 404) {
            ElMessage.error("未找到请求接口");
        } else if (error.response.status === 500) {
            ElMessage.error("系统异常，请查看后端控制台");
        } else {
            console.error(error.message);
        }
        return Promise.reject(error);
    }
);

export default request;
