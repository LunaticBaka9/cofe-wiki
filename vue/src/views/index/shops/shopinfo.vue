<template>
    <el-main>
        <el-header>
            <div class="card-header">
                <p class="card-title-primary">
                    <span class="text">店面介绍</span>
                </p>
            </div>
        </el-header>
        <div class="card-body">
            <el-row :gutter="20">
                <el-col :span="4">
                    <img :src="data.shopData.coverPath" class="cover" />
                </el-col>
                <el-col :span="14">
                    <p class="mb-1">
                        {{ data.shopData.slogn }}
                    </p>
                    <p class="mb-1">{{ data.shopData.shopType }}</p>
                    <h1>
                        <a class="shop-name" href="#">{{ data.shopData.shopName }}</a>
                    </h1>
                </el-col>
                <el-col :span="6" class="col-3">
                    <p class="text-center">
                        <a class="font-weight-bold" style="font-size: 22px">TEL：{{ data.shopData.tel }}</a>
                    </p>
                    <p class="text-center">お気軽にお問わせくださいませ♪</p>
                    <p class="text-center">
                        <el-button type="warning" style="width: 100%" class="btn">拨打电话</el-button>
                    </p>
                </el-col>
            </el-row>
            <el-divider border-style="dashed" class="divider" />
            <el-row :gutter="20">
                <el-col :span="8">
                    <table>
                        <tbody>
                            <tr>
                                <th><span class="label-pill mb-2">预计消费</span></th>
                                <td>{{ data.shopData.price }} RMB~</td>
                            </tr>
                            <tr>
                                <th><span class="label-pill mb-2">营业时间</span></th>
                                <td>{{ data.shopData.openTime }}</td>
                            </tr>
                            <tr>
                                <th><span class="label-pill mb-2">营业日</span></th>
                                <td>
                                    <table class="date-table">
                                        <tbody>
                                            <tr>
                                                <th>日</th>
                                                <th>一</th>
                                                <th>二</th>
                                                <th>三</th>
                                                <th>四</th>
                                                <th>五</th>
                                                <th>六</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <p v-if="OpenDays.Sun" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Mon" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Tues" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Wed" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Thur" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Fri" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                                <td>
                                                    <p v-if="OpenDays.Sat" class="table-text-center">√</p>
                                                    <p v-else class="table-text-center">X</p>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </el-col>
                <el-col :span="10">
                    <table>
                        <tbody>
                            <tr>
                                <th><span class="label-pill mb-2">地址</span></th>
                                <td>{{ data.shopData.address }}</td>
                            </tr>
                            <tr>
                                <th><span class="label-pill mb-2">最近的地铁</span></th>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </el-col>
                <el-col :span="6">
                    <div class="text-center">
                        <p><el-button class="btn btn-primary">人员招募</el-button></p>
                        <p><el-button class="btn btn-primary">收藏店面</el-button></p>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div class="tab-container">
            <el-tabs v-model="activeName" type="card" stretch="top" style="background-color: white">
                <el-tab-pane label="店铺详情" name="first">
                    <information />
                </el-tab-pane>
                <el-tab-pane label="最新资讯" name="second">Config</el-tab-pane>
                <el-tab-pane label="店面图片" name="third">Role</el-tab-pane>
                <el-tab-pane label="其他" name="fourth">Task</el-tab-pane>
            </el-tabs>
        </div>
        <el-header>
            <div class="card-header">
                <p class="card-title-primary">
                    <span class="text">访客评论</span>
                </p>
            </div>
            <div>
                <h1>超绝功能尚未学会（</h1>
            </div>
        </el-header>
        <br /><br /><br /><br /><br /><br /><br />
    </el-main>
</template>

<script setup name="shopinfo">
import { reactive, ref } from "vue";
import request from "../../../utils/request";
import router from "../../../router";
import Information from "./information.vue";

const activeName = ref("first");

const data = reactive({
    user: JSON.parse(localStorage.getItem("code_user") || "{}"),
    shopId: router.currentRoute.value.query.shopId,
    coverPath: "",
    shopData: [],
});

const OpenDays = reactive({
    Sun: 1,
    Mon: 1,
    Tues: 1,
    Wed: 1,
    Thur: 1,
    Fri: 1,
    Sat: 1,
});

const load = () => {
    request.get("shop/selectShopById/" + data.shopId).then((res) => {
        if (res.code === "200") {
            data.shopData = res.data;
            data.coverPath = res.data.coverPath;
            OpenDays.Sun = res.data.sun;
            OpenDays.Mon = res.data.mon;
            OpenDays.Tues = res.data.wed;
            OpenDays.Thur = res.data.thur;
            OpenDays.Fri = res.data.fri;
            OpenDays.Sat = res.data.sat;
        } else {
            ElMessage.error(res.msg);
        }
    });
};
load();
</script>

<style scoped>
.el-main {
    padding: 0;
}

a:hover {
    text-decoration: underline;
}

.cover {
    display: block;
    width: 100%;
    height: 100%;
    aspect-ratio: 1/1;
    background-repeat: no-repeat;
    background-position: center center;
    background-size: cover;
    max-width: 100%;
}

.text-center {
    text-align: center;
}

.table-text-center {
    text-align: center;
    margin: 0;
}

.shop-name {
    font-size: 24px;
}

.divider {
    margin: 14px 0;
    border-bottom: 4px dotted #ffccff;
    width: 100%;
}

.card-header {
    margin-top: 20px;
}

.card-body {
    margin: 0 20px 0 20px;
    background-color: white;
    border: 1px solid rgba(0, 0, 0, 0.125);
    border-radius: 0 0 14px 14px;
}

.label-pill {
    width: 100px;
    background-color: #00bfae;
    border: 2px solid #00bfae;
    text-align: center;
    display: inline-block;
    margin-right: 4px;
    color: #fff;
    font-weight: 700;
    border-radius: 30px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    opacity: 0.8;
    height: 30px;
    font-size: 16px;
}

.btn {
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-weight: 400;
    line-height: 1.5;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    touch-action: manipulation;
    border: 1px solid transparent;
    border-radius: 2px;
    height: 42px;
}

.btn-primary {
    color: #fff;
    background-color: #ff64b1;
    border-color: #ff5aac;
    transition-property: all;
    width: 100%;
}

.tab-container {
    margin: 20px 20px 0 20px;
    padding: 0px;
    border: 1px solid rgba(0, 0, 0, 0.125);
    background-color: #fff;
}

.table-bordered {
    margin-top: 20px;
    border: 1px solid rgba(0, 0, 0, 0.125);
}

.table-bordered th {
    background: #ffe9ef;
    padding: 10px;
    font-weight: 700;
    color: #d6336c;
    text-align: center;
    white-space: nowrap !important;
    vertical-align: middle !important;
}

.table-bordered td {
    word-break: break-all;
    vertical-align: middle !important;
    padding: 0.5rem;
}

.table-bordered ul {
    list-style: none;
    padding: 0px;
}

.label {
    padding: 0.2em 0.6em 0.3em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: 0.25em;
    display: inline-block;
    white-space: normal;
    font-weight: 400;
}

.label-primary {
    background-color: #ff64b1;
}

.date-table {
    background: #fff;
    border: 1px solid #dee2e6;
    width: 100%;
    border-collapse: collapse;
}

.date-table th {
    padding: 2px 4px;
    background: #ffe9ef;
    font-weight: 700;
    color: #d6336c;
    text-align: center;
    white-space: nowrap !important;
}

.mb-1 {
    margin-bottom: 0.25rem !important;
}

.mb-2 {
    margin-bottom: 0.5rem !important;
}

.md-5 {
    margin-bottom: 5px !important;
}

a {
    color: #ff64b1;
    text-decoration: none;
}

p {
    margin-top: 0;
    margin-bottom: 1rem;
}

.card-body {
    margin-top: 0;
    padding: 20px;
}
</style>
