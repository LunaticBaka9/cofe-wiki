<template>
    <el-main>
        <div class="hero-wrap">
            <img class="hero-img" :src="bander2" alt="咖啡馆横幅" />
            <div class="hero-caption">
                <h1>日本咖啡文化</h1>
                <div class="hero-sub">在日本的每条街巷，总有一间等你停下脚步的咖啡馆。</div>
            </div>
        </div>

        <div class="content-wrap" shadow="never">
            <el-row :gutter="20">
                <el-col :span="18">
                    <el-card class="guide-card">
                        <div class="guide-header">
                            <h2 class="large-title">日本咖啡馆指南</h2>
                            <div class="guide-lead">为中国游客提供清晰、实用的咖啡馆探索入口</div>
                        </div>

                        <el-divider />

                        <div class="guide-body">
                            <p>
                                在日本旅行时，面对街头巷尾风格各异的咖啡馆，你是否也曾犹豫：这家适合我吗？需要预约吗？能用中文沟通吗？
                            </p>

                            <p>
                                我们网站的作用很简单：帮你快速了解日本有哪些类型的咖啡馆，找到符合你兴趣、语言习惯和行程节奏的那一间。
                            </p>

                            <p>
                                无论你偏爱安静老铺、现代精品，还是想体验一次特别的文化互动，我们整理了清晰的分类、背景与实用信息，让你轻松出发，安心入座。
                            </p>

                            <h4 class="section-subtitle">☕ 日本咖啡馆：从喫茶店到女仆咖啡厅</h4>
                            <p>
                                日本的咖啡文化始于20世纪初。受西方影响，最早的“喫茶店”（Kissaten）在大正时代出现，成为文人雅士聚集、市民休憩的重要空间。它们讲究静谧氛围、手冲技艺与慢节奏生活，至今仍是日本咖啡精神的根基。
                            </p>

                            <p>
                                随着时代发展，咖啡馆逐渐分化出多种形态：追求豆子本味与冲煮工艺的精品咖啡馆；融合动漫、动物或IP元素的主题咖啡馆；以及其中最具代表性的分支——女仆咖啡厅。女仆咖啡厅起源于2000年代初的东京秋叶原，是御宅文化与日式待客之道结合的产物。在这里，服务员以“女仆”身份亲切接待顾客，提供餐饮服务的同时加入轻度互动，营造出一种可爱而治愈的体验氛围。它并非表演秀，而是日本多元咖啡文化中一个独特而真实的组成部分。
                            </p>

                            <p class="closing">愿我们为你打开一扇窗，让你更从容地走进属于你的那杯日本咖啡时光。</p>
                        </div>
                    </el-card>
                </el-col>

                <el-col :span="6">
                    <div class="side-area">
                        <el-card class="side-banner-card" shadow="never">
                            <img :src="bander1" alt="店铺推荐" class="side-banner" />
                        </el-card>

                        <el-card class="reminder-card" shadow="never">
                            <h4 class="reminder-title">给中国游客的小贴士</h4>
                            <ul class="tips">
                                <li>语言：大多数热门店铺都提供中文菜单，并能进行基本的中文沟通。</li>
                                <li>礼仪：保持礼貌，尊重他人的工作和服务界限。</li>
                                <li>拍照：通常允许拍摄食物和店内装饰，但合影前请先征得同意。</li>
                                <li>预约：建议提前预约，特别是在周末或节假日，以免长时间等待。</li>
                            </ul>
                        </el-card>

                        <el-card class="support-card" shadow="never">
                            <div class="support-row">
                                <div class="support-text">
                                    TIPS： 需要帮助或查找附近咖啡馆？使用站内搜索或登录查看更多推荐。
                                </div>
                            </div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>
        </div>
        <br /><br /><br /><br />
    </el-main>
</template>

<script setup name="index">
import { ref } from "vue";

const bander1 = ref("/api/img/bander/bander1.jpg");
const bander2 = ref("/api/img/bander/bander2.png");
const bander3 = ref("/api/img/bander/bander3.webp");
</script>

<style scoped>
.hero-wrap {
    position: relative;
    border-radius: 12px;
    overflow: hidden;
    margin-bottom: 18px;
}
.hero-img {
    width: 100%;
    height: 220px;
    object-fit: cover;
    display: block;
}
.hero-caption {
    position: absolute;
    left: 20px;
    bottom: 20px;
    color: #fff;
    text-shadow: 0 4px 14px rgba(0, 0, 0, 0.5);
}
.hero-caption h1 {
    margin: 0;
    font-size: 1.6rem;
}
.hero-sub {
    font-size: 0.98rem;
    opacity: 0.95;
}
.content-wrap {
    margin: 0 auto;
}
.guide-card {
    padding: 0;
    border-radius: 12px;
}
.guide-header {
    padding: 18px 20px;
}
.large-title {
    color: #c2185b;
    margin: 0 0 6px 0;
}
.guide-lead {
    color: #666;
}
.guide-body {
    padding: 0 20px 18px 20px;
    color: #333;
    line-height: 1.8;
}
.section-subtitle,
.tips-title {
    color: #c2185b;
    margin-top: 12px;
}
.tips {
    padding: 0px;
    list-style: none;
}
.tips li {
    margin: 0.5rem;
}
.closing {
    margin-top: 20px;
    margin-bottom: 8px;
    color: #6b6b6b;
    font-style: italic;
    text-align: center;
    padding-top: 12px;
    border-top: 1px dashed rgba(194, 24, 91, 0.08);
    line-height: 1.6;
}

/* ensure card body has enough bottom space so closing isn't cramped to card edge */
.guide-card .el-card__body {
    padding-bottom: 22px;
}

.side-area {
    display: flex;
    flex-direction: column;
    gap: 12px;
}
.side-banner {
    width: 100%;
    height: 140px;
    object-fit: cover;
    border-radius: 8px;
    display: block;
}
.reminder-card {
    background-image: linear-gradient(135deg, #fff7fb 0%, #fff 100%);
}
.reminder-title {
    color: #c2185b;
    margin: 0 0 8px 0;
}
.support-card {
    padding: 10px 12px;
}
.support-row {
    display: flex;
    gap: 10px;
    align-items: center;
}
.support-row img {
    width: 44px;
    height: 44px;
    border-radius: 6px;
}
.support-text {
    color: #666;
    font-size: 0.95rem;
}

@media (max-width: 900px) {
    .hero-img {
        height: 200px;
    }
    .content-wrap {
        padding: 0 12px;
    }
}
</style>
