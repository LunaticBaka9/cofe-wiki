<template>
    <el-main>
        <h1>Bro以为自己是Big Boss，能掌管这里的所有权限</h1>
        <img :src="img" />
    </el-main>
</template>

<script setup>
const img = "/api/EvilLoud.png";
</script>

<style scoped>
img {
    width: 200px;
}
</style>
