<template>
    <el-main>
        <el-header>
            <div class="header">
                <p class="card-title-primary">
                    <span class="text">咖啡文化</span>
                </p>
            </div>
        </el-header>
        <div class="wiki-content">相关词条：</div>
        <div class="wiki-content">
            <el-row :gutter="20">
                <el-col :span="16">
                    <div class="content-text"></div>
                </el-col>
            </el-row>
            <el-col :span="5"></el-col>
        </div>
    </el-main>
</template>
