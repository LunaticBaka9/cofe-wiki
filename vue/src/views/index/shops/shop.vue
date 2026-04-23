<template>
    <el-main>
        <el-header>
            <div class="header">
                <p class="card-title-primary">
                    <span class="text">热门店面</span>
                </p>
            </div>
        </el-header>
        <el-row :gutter="30" class="shop-cards">
            <el-col
                :xs="24"
                :sm="12"
                :md="8"
                :lg="6"
                :xl="4"
                v-for="(item, index) in data.tableData"
                :key="index"
            >
                <el-card
                    shadow="hover"
                    class="shop-card"
                    @click="navTo('/shopinfo?shopId=' + item.shopId)"
                >
                    <img
                        :src="item.coverPath"
                        alt="店铺图片"
                        class="shop-image"
                    />
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
    tableData: [],
});

const navTo = (url) => {
    location.href = url;
};

const load = () => {
    request.get("shop/selectAllShops").then((res) => {
        if (res.code === "200") {
            console.log(res.data);
            data.tableData = res.data;
        } else {
            ElMessage.error(res.msg);
        }
    });
};
load();
</script>

<style scoped>
* {
    text-decoration: none;
    font-family: Arial, sans-serif;
}

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

.footer-content {
    text-align: center;
    padding: 10px;
}

/* 标题背景样式 */
.card-title-primary {
    background-color: #ff69b4;
    margin: 0;
    color: #fff;
    font-size: 20px;
    font-weight: 700;
    padding: 10px 15px;
    border-radius: 10px 10px 0 0;
    background-image: linear-gradient(
        135deg,
        #ffb6c1 25%,
        #ff69b4 25%,
        #ff69b4 50%,
        #ffb6c1 50%,
        #ffb6c1 75%,
        #ff69b4 75%,
        #ff69b4
    );
    background-size: 14px 14px;
}

.card-title-primary .text {
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
}

/* 商店卡片 */
.el-card {
    --el-card-padding: 5px;
    width: 100%;
    max-width: 300px;
}

.shop-card {
    cursor: pointer;
    transition:
        transform 0.3s ease,
        box-shadow 0.3s ease;
}

.shop-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
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

@media (max-width: 768px) {
    .shop-cards {
        padding: 10px;
    }

    .shop-cards :deep(.el-col) {
        margin-bottom: 15px;
    }

    .shop-image {
        height: 150px;
    }

    .shop-info div {
        font-size: 0.75rem;
        margin-top: 0.5rem;
        margin-bottom: 10px;
    }

    .text-info {
        font-size: 0.75rem;
        height: 2.5em;
    }
}

@media (max-width: 480px) {
    .shop-cards {
        padding: 5px;
    }

    .shop-image {
        height: 120px;
    }

    .shop-info div {
        font-size: 0.7rem;
    }

    .text-info {
        font-size: 0.7rem;
    }
}
</style>
