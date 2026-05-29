// 请求封装

import axios from "axios";
import { ElMessage } from "element-plus";

const request = axios.create({
    baseURL: "/api",
    timeout: 30000,
});

request.interceptors.request.use(
    (config) => {
        if (config.data instanceof FormData) {
            delete config.headers["Content-Type"];
        } else if (
            config.headers["Content-Type"] &&
            config.headers["Content-Type"].includes("multipart/form-data")
        ) {
            // allow browser/axios to set boundary
        } else {
            config.headers["Content-Type"] = "application/json;charset=utf-8";
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    },
);

request.interceptors.response.use(
    (response) => {
        let res = response.data;
        if (typeof res === "string") {
            res = res ? JSON.parse(res) : res;
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
    },
);

export default request;
