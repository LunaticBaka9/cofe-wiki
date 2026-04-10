<template>
    <el-main>
        <div class="card" style="margin-bottom: 5px">
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.shopId"
                placeholder="请输入所在店铺 ID 查询"
                :prefix-icon="Search"
                clearable
                @keyup.enter.native="load"
                @clear="load"
            ></el-input>
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.shopName"
                placeholder="请输入店铺名称查询"
                :prefix-icon="Search"
                clearable
                @keyup.enter.native="load"
                @clear="load"
            ></el-input>
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.location"
                placeholder="请输入所在地区查询"
                :prefix-icon="Search"
                clearable
                @keyup.enter.native="load"
                @clear="load"
            ></el-input>
            <el-button type="primary" @click="load">查 询</el-button>
            <el-button @click="reset">重 置</el-button>
        </div>

        <div class="card" style="margin-bottom: 5px">
            <el-button type="primary" @click="handleAdd">新 增</el-button>
            <el-button type="danger" @click="deleteBatch">批量删除</el-button>
            <el-button type="success">批量导入</el-button>
            <el-button type="info">批量导出</el-button>
        </div>

        <div class="card" style="margin-bottom: 5px">
            <el-table
                border
                :data="data.tableData"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                :header-cell-style="{ color: '#333', backgroundColor: '#ffb6c1' }"
            >
                <el-table-column type="selection" width="55" />
                <el-table-column fixed prop="shopId" label="店铺ID" width="100" />
                <el-table-column label="封面">
                    <template #default="scope">
                        <el-image
                            :src="scope.row.coverPath"
                            :preview-src-list="[scope.row.coverPath]"
                            preview-teleported
                            fit="cover"
                        />
                    </template>
                </el-table-column>
                <el-table-column prop="shopName" label="店铺名称" width="150" />
                <el-table-column prop="location" label="所在地区" width="150" />
                <el-table-column prop="slogn" label="slogn" show-overflow-tooltip width="150" />
                <el-table-column prop="tel" label="电话" width="150" />
                <el-table-column prop="openTime" label="营业时间" show-overflow-tooltip width="150" />
                <el-table-column prop="price" label="人均价格(RMB)" width="130" />
                <el-table-column prop="intro" label="店铺介绍" width="150" show-overflow-tooltip />
                <el-table-column prop="offical" label="官网" show-overflow-tooltip width="150" />
                <el-table-column prop="twitter" label="twitter" show-overflow-tooltip width="150" />
                <el-table-column prop="facebook" label="facebook" show-overflow-tooltip width="150" />
                <el-table-column fixed="right" label="操作" min-width="150">
                    <template #default="scope">
                        <el-button size="small" @click="handleEidor(scope.row)"> 修改 </el-button>
                        <el-button size="small" type="danger" @click="del(scope.row)"> 删除 </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="card">
            <el-pagination
                v-model:current-page="data.pageNum"
                v-model:page-size="data.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[10, 20, 30]"
                :total="data.total"
                @current-change="load"
                @size-change="load"
            />
        </div>

        <el-dialog v-model="data.formVisible" title="店铺信息" width="600" destroy-on-close>
            <el-form
                ref="formRef"
                :model="data.form"
                :rules="data.rules"
                label-width="auto"
                style="padding: 20px 30px; max-width: auto"
            >
                <el-form-item prop="shopName" label="名称">
                    <el-input v-model="data.form.shopName" placeholder="店铺名称" />
                </el-form-item>
                <el-form-item label="封面">
                    <el-upload
                        :action="uploadUrl"
                        name="file"
                        :data="uploadData"
                        :file-list="fileList"
                        :before-upload="beforeUpload"
                        :on-success="handleUploadSuccess"
                        :show-file-list="false"
                        accept="image/*"
                    >
                        <template #trigger>
                            <el-button type="primary">选择文件</el-button>
                        </template>
                        <template #tip>
                            <div class="el-upload__tip text-red">上传图片尺寸应为 240 x 240 像素</div>
                        </template>
                    </el-upload>
                    <div v-if="data.form.coverPath" style="margin-top: 8px; margin-left: 20px">
                        <el-image :src="data.form.coverPath" style="width: 80px; height: 80px; object-fit: cover" />
                    </div>
                </el-form-item>
                <el-form-item prop="location" label="所在地区"
                    ><el-input v-model="data.form.location" placeholder="所在地区" />
                </el-form-item>
                <el-form-item prop="address" label="地址">
                    <el-input v-model="data.form.address" type="textarea" placeholder="请填写商店具体地址" />
                </el-form-item>
                <el-form-item prop="slogn" label="slogn">
                    <el-input v-model="data.form.slogn" type="textarea" />
                </el-form-item>
                <el-form-item prop="openTime" label="营业时间">
                    <el-input v-model="data.form.openTime" type="textarea" />
                </el-form-item>
                <el-form-item prop="tel" label="电话">
                    <el-input v-model="data.form.tel" />
                </el-form-item>
                <el-form-item prop="price" label="人均价格(RMB)">
                    <el-input v-model="data.form.price" />
                </el-form-item>
                <el-form-item prop="intro" label="商店简介">
                    <el-input v-model="data.form.intro" type="textarea"></el-input>
                </el-form-item>
                <el-form-item prop="offical" label="官网">
                    <el-input v-model="data.form.offical"></el-input>
                </el-form-item>
                <el-form-item prop="twitter" label="twitter">
                    <el-input v-model="data.form.twitter"></el-input>
                </el-form-item>
                <el-form-item prop="facebook" label="facebook">
                    <el-input v-model="data.form.facebook"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="data.formVisible = false">取消</el-button>
                    <el-button type="primary" @click="save"> 保存 </el-button>
                </div>
            </template>
        </el-dialog>
        <br /><br /><br /><br />
    </el-main>
</template>

<script setup name="ShopManager">
import request from "@/utils/request.js";
import { onBeforeMount, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

onBeforeMount(() => {
    const userStr = localStorage.getItem("code_user");
    if (userStr) {
        const user = JSON.parse(userStr);
        if (!user.userId) {
            location.href = "/login";
        } else if (user.userType !== "admin" && user.userType !== "editor") {
            location.href = "/NoPermission";
        }
    } else {
        location.href = "/login";
    }
    load();
});

const data = reactive({
    shopId: null,
    shopName: null,
    location: null,
    pageNum: 1,
    pageSize: 10,
    total: 6,
    tableData: [],
    formVisible: false,
    form: {},
    rules: {
        shopName: [{ required: true, message: "请填写商店名称", trigger: "blur" }],
        location: [{ required: true, message: "请填写所在区域", trigger: "blur" }],
        address: [{ required: true, message: "请填写商店地址", trigger: "blur" }],
    },
    rows: [],
});

const formRef = ref();

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
                console.log(data.tableData);
            } else {
                ElMessage.error(res.msg);
            }
        });
};

const reset = () => {
    data.shopId = null;
    data.shopName = null;
    data.location = null;
    load();
};

const handleAdd = () => {
    data.formVisible = true;
    data.form = {};
};

const handleEidor = (row) => {
    data.form = JSON.parse(JSON.stringify(row));
    data.formVisible = true;
};

const handleSelectionChange = (rows) => {
    data.rows = rows;
};

const add = () => {
    //应用表单进行验证
    formRef.value.validate((valid) => {
        if (valid) {
            //验证通过的情况下调用接口
            request.post("/shop/add", data.form).then((res) => {
                if (res.code === "200") {
                    data.formVisible = false;
                    ElMessage.success("新增成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        } else {
            ElMessage.error(res.msg);
        }
    });
};

const update = (row) => {
    //应用表单进行验证
    formRef.value.validate((valid) => {
        if (valid) {
            //验证通过的情况下调用接口
            request.put("/shop/update", data.form).then((res) => {
                if (res.code === "200") {
                    data.formVisible = false;
                    ElMessage.success("修改成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        } else {
            ElMessage.error(res.msg);
        }
    });
};

const del = (row) => {
    //应用表单进行验证
    ElMessageBox.confirm("确认删除此列数据", "删除确认", { type: "warning" })
        .then((res) => {
            request.put("/shop/delete", row).then((res) => {
                if (res.code === "200") {
                    ElMessage.success("删除成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        })
        .catch((err) => {});
};

const deleteBatch = () => {
    if (data.rows.length == 0) {
        ElMessage.warning("请选择数据");
        return;
    }
    ElMessageBox.confirm("确认删除此列数据", "删除确认", { type: "warning" })
        .then((res) => {
            request.put("/shop/deleteBatch", data.rows).then((res) => {
                if (res.code === "200") {
                    ElMessage.success("批量删除成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        })
        .catch((err) => {});
};

const uploadUrl = "/api/upload/shop-cover";
const fileList = ref([]);

const uploadData = () => {
    return { shopId: data.form.shopId };
};

const beforeUpload = (file) => {
    return new Promise((resolve, reject) => {
        if (!file.type.startsWith("image/")) {
            ElMessage.error("请选择图片文件");
            return reject(false);
        }
        if (!data.form.shopId) {
            ElMessage.warning("请先保存或填写 shopId 后再上传封面");
            return reject(false);
        }
        const url = URL.createObjectURL(file);
        const img = new Image();
        img.onload = () => {
            URL.revokeObjectURL(url);
            if (img.naturalWidth === 240 && img.naturalHeight === 240) {
                resolve(true);
            } else {
                ElMessage.error("图片尺寸需为 240 x 240 像素");
                reject(false);
            }
        };
        img.onerror = () => {
            URL.revokeObjectURL(url);
            ElMessage.error("无法读取图片");
            reject(false);
        };
        img.src = url;
    });
};

const handleUploadSuccess = (res, file) => {
    // 后端返回完整 URL 字符串
    const url = typeof res === "string" ? res : res.url || res.data || "";
    if (url) {
        data.form.coverPath = url;
        ElMessage.success("上传成功");
        load();
    } else {
        ElMessage.error("上传失败，服务器未返回 URL");
    }
};

const save = () => {
    data.form.shopId ? update() : add();
};
</script>

<style scoped>
.card {
    padding: 10px;
    border-radius: 5px;
}
</style>
