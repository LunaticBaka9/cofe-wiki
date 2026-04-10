<template>
    <div>
        <el-carousel style="height: 700px; margin-top: 10px" :autoplay="false">
            <el-carousel-item
                v-for="(image, index) in data.shopData.shopImg"
                :key="index"
                style="height: auto; width: 100%"
            >
                <img :src="image" />
            </el-carousel-item>
        </el-carousel>
        <div class="table-bordered">
            <table>
                <tbody>
                    <tr>
                        <th width="130">讲究</th>
                        <td>
                            <ul>
                                <li class="md-5">
                                    <span class="label label-primary">其一</span>
                                    {{ data.shopData.obs1 }}
                                </li>
                                <li class="md-5">
                                    <span class="label label-primary">其二</span>
                                    {{ data.shopData.obs2 }}
                                </li>
                                <li class="md-5">
                                    <span class="label label-primary">其三</span>
                                    {{ data.shopData.obs3 }}
                                </li>
                            </ul>
                        </td>
                    </tr>
                    <tr>
                        <th width="130">简介</th>
                        <td style="white-space: pre-wrap">
                            {{ data.shopData.intro }}
                        </td>
                    </tr>
                    <tr>
                        <th width="130">种类</th>
                        <td>{{ data.shopData.shopType }}</td>
                    </tr>
                    <tr>
                        <th width="130">预计消费</th>
                        <td>{{ data.shopData.price }} RMB~</td>
                    </tr>
                    <tr>
                        <th width="130">菜单</th>
                        <td style="white-space: pre-wrap">
                            {{ data.shopData.menu }}
                        </td>
                    </tr>
                    <tr>
                        <th width="130">推荐菜单</th>
                        <td style="white-space: pre-wrap">
                            {{ data.shopData.pmenu }}
                        </td>
                    </tr>
                    <tr>
                        <th width="130">电话号码</th>
                        <td>{{ data.shopData.tel }}</td>
                    </tr>
                    <tr>
                        <th width="130">营业时间</th>
                        <td>{{ data.shopData.openTime }}</td>
                    </tr>
                    <tr>
                        <th width="130">营业日</th>
                        <td width="89%">
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
                    <tr>
                        <th width="130">详细分类</th>
                        <td>
                            {{ data.shopData.tags }}
                        </td>
                    </tr>
                    <tr>
                        <th width="130">地址</th>
                        <td>{{ data.shopData.address }}</td>
                    </tr>
                    <tr>
                        <th width="130">官网</th>
                        <td>
                            <span v-if="!data.shopData.offical">-</span>
                            <span v-else>
                                <a :href="data.shopData.offical">
                                    {{ data.shopData.offical }}
                                </a>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th width="130">twitter</th>
                        <td>
                            <span v-if="!data.shopData.twitter">-</span>
                            <span v-else>
                                <a :href="data.shopData.twitter">
                                    {{ data.shopData.twitter }}
                                </a>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th width="130">facebook</th>
                        <td>
                            <span v-if="!data.shopData.facebook">-</span>
                            <span v-else>
                                <a :href="data.shopData.facebook">
                                    {{ data.shopData.facebook }}
                                </a>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th width="130">开店日</th>
                        <td>2023年9月29日</td>
                    </tr>
                    <tr>
                        <th width="130">采访</th>
                        <td>接受媒体采访！</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "../../../utils/request";
import router from "../../../router";

const data = reactive({
    user: JSON.parse(localStorage.getItem("code_user") || "{}"),
    shopId: router.currentRoute.value.query.shopId,
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
            data.shopData.shopImg = res.data.shopImg.split(",");
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

.table-text-center {
    text-align: center;
    margin: 0;
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
