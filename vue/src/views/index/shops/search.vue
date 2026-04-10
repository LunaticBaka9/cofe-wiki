<template>
    <el-main>
        <el-header>
            <div class="header">
                <p class="card-title-primary">
                    <span class="text">搜索店面</span>
                </p>
            </div>
        </el-header>
        <el-input
            style="padding-left: 20px; width: 40%"
            v-model="data.shopName"
            placeholder="请输入店名查询"
            :prefix-icon="Search"
            clearable
            @keyup.enter.native="load"
            @clear="load"
        ></el-input>
        <el-input
            style="padding-left: 20px; width: 40%"
            v-model="data.location"
            placeholder="请输入地区查询"
            :prefix-icon="Search"
            clearable
            @keyup.enter.native="load"
            @clear="load"
        ></el-input>
        <el-row :gutter="30" class="shop-cards">
            <el-col :span="4" v-for="(item, index) in data.tableData" :key="index">
                <el-card shadow="hover" @click="navTo('/shopinfo?shopId=' + item.shopId)">
                    <img :src="item.coverPath" alt="店铺图片" class="shop-image" />
                    <div class="shop-info">
                        <div>{{ item.location }}</div>
                        <div class="text-info">{{ item.shopName }}</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <br /><br /><br />
    </el-main>
</template>

<script setup>
import { reactive, ref } from "vue";
import request from "../../../utils/request";

const data = reactive({
    shopName: null,
    pageSize: 100,
    tableData: [],
});

const navTo = (url) => {
    location.href = url;
};

const load = () => {
    request
        .get("shop/selectPage", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                shopId: data.shopId,
                shopName: data.shopName,
                location: data.location,
            },
        })
        .then((res) => {
            if (res.code === "200") {
                data.tableData = res.data.list;
                data.total = res.data.total;
            } else {
                ElMessage.error(res.msg);
            }
        });
};
load();
</script>

<style scoped>
.custom-link {
    text-decoration: none;
}

.shop-cards {
    padding: 20px;
    border-radius: 7px;
    overflow: hidden;
}

.main-container {
    display: flex;
}

.el-card {
    --el-card-padding: 5px;
    max-width: 300px;
}

.shop-image {
    display: block;
    width: 100%;
    height: 100%;
    border-radius: 4px;
    max-width: 100%;
}

.shop-info {
    flex: 1 1 auto;
}

.shop-info div {
    font-size: 0.85rem;
    margin-top: 1rem;
    margin-bottom: 20px;
    line-height: 1;
    color: #6c757d;
    font-weight: 400;
}

.text-info {
    display: block;
    height: 2.8em;
    line-height: 1.4;
    overflow: hidden;
    word-break: break-all;
    font-size: 0.85rem;
    color: #17a2b8;
}

.el-col {
    margin-bottom: 20px;
}
</style>
