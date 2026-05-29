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
                :header-cell-style="{
                    color: '#333',
                    backgroundColor: '#ffb6c1',
                }"
            >
                <el-table-column type="selection" width="55" />
                <el-table-column
                    fixed
                    prop="shopId"
                    label="店铺ID"
                    width="100"
                />
                <el-table-column label="封面">
                    <template #default="scope">
                        <el-image
                            class="table-img-preview"
                            :src="scope.row.coverPath"
                            :preview-src-list="[scope.row.coverPath]"
                            preview-teleported
                            fit="cover"
                        />
                    </template>
                </el-table-column>
                <el-table-column prop="shopName" label="店铺名称" width="150" />
                <el-table-column prop="location" label="所在地区" width="150" />
                <el-table-column
                    prop="slogn"
                    label="slogn"
                    show-overflow-tooltip
                    width="150"
                />
                <el-table-column prop="tel" label="电话" width="150" />
                <el-table-column
                    prop="openTime"
                    label="营业时间"
                    show-overflow-tooltip
                    width="150"
                />
                <el-table-column
                    prop="price"
                    label="人均价格(RMB)"
                    width="130"
                />
                <el-table-column
                    prop="intro"
                    label="店铺介绍"
                    width="150"
                    show-overflow-tooltip
                />
                <el-table-column
                    prop="offical"
                    label="官网"
                    show-overflow-tooltip
                    width="150"
                />
                <el-table-column
                    prop="twitter"
                    label="twitter"
                    show-overflow-tooltip
                    width="150"
                />
                <el-table-column
                    prop="facebook"
                    label="facebook"
                    show-overflow-tooltip
                    width="150"
                />
                <el-table-column fixed="right" label="操作" min-width="150">
                    <template #default="scope">
                        <el-button size="small" @click="handleEidor(scope.row)">
                            修改
                        </el-button>
                        <el-button
                            size="small"
                            type="danger"
                            @click="del(scope.row)"
                        >
                            删除
                        </el-button>
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

        <el-dialog
            v-model="data.formVisible"
            title="店铺信息"
            width="600"
            destroy-on-close
        >
            <el-form
                ref="formRef"
                :model="data.form"
                :rules="data.rules"
                label-width="auto"
                style="padding: 20px 30px; max-width: auto"
            >
                <el-form-item prop="shopName" label="名称">
                    <el-input
                        v-model="data.form.shopName"
                        placeholder="店铺名称"
                    />
                </el-form-item>

                <el-form-item label="店铺图片" prop="coverPath">
                    <el-upload
                        class="goods-uploader"
                        :show-file-list="false"
                        :before-upload="beforeUpload"
                        action="#"
                    >
                        <img
                            v-if="data.form.coverPath"
                            :src="data.form.coverPath"
                            class="goods-img-preview"
                        />
                        <el-icon v-else class="uploader-icon"><Plus /></el-icon>
                    </el-upload>
                    <div class="upload-tip">
                        点击上传商店图片，支持jpg、png格式，图片会自动进入固定比例裁剪
                    </div>
                </el-form-item>

                <el-form-item prop="location" label="所在地区"
                    ><el-input
                        v-model="data.form.location"
                        placeholder="所在地区"
                    />
                </el-form-item>
                <el-form-item prop="address" label="地址">
                    <el-input
                        v-model="data.form.address"
                        type="textarea"
                        placeholder="请填写商店具体地址"
                    />
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
                    <el-input
                        v-model="data.form.intro"
                        type="textarea"
                    ></el-input>
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
                    <el-button @click="data.formVisible = false"
                        >取消</el-button
                    >
                    <el-button type="primary" @click="save"> 保存 </el-button>
                </div>
            </template>
        </el-dialog>

        <el-dialog
            v-model="data.cropVisible"
            title="裁剪图片"
            width="780px"
            destroy-on-close
            :close-on-click-modal="false"
            @close="closeCropDialog"
        >
            <div class="crop-dialog-body">
                <div class="crop-left">
                    <div class="crop-area" ref="cropAreaRef">
                        <img
                            ref="cropImageRef"
                            v-if="data.crop.imageSrc"
                            :src="data.crop.imageSrc"
                            class="crop-image"
                            @load="onCropImageLoad"
                        />
                        <div
                            class="crop-rect"
                            v-if="data.crop.imageSrc"
                            :style="cropRectStyle()"
                            @mousedown="startCropDrag"
                        ></div>
                    </div>
                </div>
                <div class="crop-right">
                    <el-form label-width="90px">
                        <el-form-item label="裁剪比例">
                            <div>1:1</div>
                        </el-form-item>
                        <el-form-item label="缩放">
                            <el-slider
                                v-model="data.crop.zoom"
                                :min="30"
                                :max="100"
                                @change="onZoomChange"
                                show-tooltip
                            />
                        </el-form-item>
                        <el-form-item label="预览">
                            <div
                                class="crop-preview"
                                :style="previewStyle()"
                            ></div>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="closeCropDialog">取消</el-button>
                    <el-button
                        type="primary"
                        :loading="uploadLoading"
                        @click="applyCropAndUpload"
                        >确认裁剪上传</el-button
                    >
                </div>
            </template>
        </el-dialog>

        <br /><br /><br /><br />
    </el-main>
</template>

<script setup name="ShopManager">
import request from "@/utils/request.js";
import { onBeforeMount, nextTick, reactive, ref } from "vue";
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
    cropVisible: false,
    crop: {
        imageSrc: "",
        file: null,
        naturalWidth: 0,
        naturalHeight: 0,
        displayWidth: 0,
        displayHeight: 0,
        x: 0,
        y: 0,
        size: 0,
        zoom: 70,
    },
    rules: {
        shopName: [
            { required: true, message: "请填写商店名称", trigger: "blur" },
        ],
        location: [
            { required: true, message: "请填写所在区域", trigger: "blur" },
        ],
        address: [
            { required: true, message: "请填写商店地址", trigger: "blur" },
        ],
    },
    rows: [],
});

const formRef = ref();
const cropImageRef = ref(null);
const cropAreaRef = ref(null);
const cropDrag = reactive({
    active: false,
    startX: 0,
    startY: 0,
    startLeft: 0,
    startTop: 0,
});

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

const resetCropPosition = () => {
    const displayWidth = data.crop.displayWidth;
    const displayHeight = data.crop.displayHeight;
    const maxSize = Math.min(displayWidth, displayHeight);
    data.crop.size = Math.max(
        120,
        Math.min(maxSize, (data.crop.zoom * maxSize) / 100),
    );
    data.crop.x = (displayWidth - data.crop.size) / 2;
    data.crop.y = (displayHeight - data.crop.size) / 2;
};

const onCropImageLoad = async () => {
    await nextTick();
    const img = cropImageRef.value;
    const area = cropAreaRef.value;
    if (!img || !area) {
        return;
    }
    data.crop.naturalWidth = img.naturalWidth;
    data.crop.naturalHeight = img.naturalHeight;
    data.crop.displayWidth = img.clientWidth;
    data.crop.displayHeight = img.clientHeight;
    data.crop.zoom = 70;
    resetCropPosition();
};

const clampCrop = () => {
    const maxX = Math.max(0, data.crop.displayWidth - data.crop.size);
    const maxY = Math.max(0, data.crop.displayHeight - data.crop.size);
    data.crop.x = Math.min(Math.max(0, data.crop.x), maxX);
    data.crop.y = Math.min(Math.max(0, data.crop.y), maxY);
};

const onZoomChange = () => {
    resetCropPosition();
};

const startCropDrag = (event) => {
    event.preventDefault();
    cropDrag.active = true;
    cropDrag.startX = event.clientX;
    cropDrag.startY = event.clientY;
    cropDrag.startLeft = data.crop.x;
    cropDrag.startTop = data.crop.y;
    window.addEventListener("mousemove", moveCropDrag);
    window.addEventListener("mouseup", endCropDrag);
};

const moveCropDrag = (event) => {
    if (!cropDrag.active) return;
    const deltaX = event.clientX - cropDrag.startX;
    const deltaY = event.clientY - cropDrag.startY;
    data.crop.x = cropDrag.startLeft + deltaX;
    data.crop.y = cropDrag.startTop + deltaY;
    clampCrop();
};

const endCropDrag = () => {
    cropDrag.active = false;
    window.removeEventListener("mousemove", moveCropDrag);
    window.removeEventListener("mouseup", endCropDrag);
};

const closeCropDialog = () => {
    data.cropVisible = false;
    data.crop.imageSrc = "";
    data.crop.file = null;
};

const cropRectStyle = () => {
    return {
        position: "absolute",
        left: `${data.crop.x}px`,
        top: `${data.crop.y}px`,
        width: `${data.crop.size}px`,
        height: `${data.crop.size}px`,
        border: "2px dashed rgba(64, 158, 255, 0.85)",
        background: "rgba(64, 158, 255, 0.15)",
        cursor: "move",
        boxSizing: "border-box",
    };
};

const previewStyle = () => {
    if (
        !data.crop.imageSrc ||
        !data.crop.displayWidth ||
        !data.crop.displayHeight
    ) {
        return {
            backgroundColor: "#f5f7fa",
        };
    }
    return {
        backgroundImage: `url(${data.crop.imageSrc})`,
        backgroundSize: `${data.crop.displayWidth}px ${data.crop.displayHeight}px`,
        backgroundPosition: `-${data.crop.x}px -${data.crop.y}px`,
        backgroundRepeat: "no-repeat",
    };
};

const applyCropAndUpload = async () => {
    if (!cropImageRef.value) {
        ElMessage.error("图片未加载, 无法裁剪");
        return;
    }
    const img = cropImageRef.value;
    const scaleX = data.crop.naturalWidth / data.crop.displayWidth;
    const scaleY = data.crop.naturalHeight / data.crop.displayHeight;
    const naturalX = data.crop.x * scaleX;
    const naturalY = data.crop.y * scaleY;
    const naturalSize = data.crop.size * Math.min(scaleX, scaleY);

    const canvas = document.createElement("canvas");
    canvas.width = naturalSize;
    canvas.height = naturalSize;
    const ctx = canvas.getContext("2d");
    ctx.drawImage(
        img,
        naturalX,
        naturalY,
        naturalSize,
        naturalSize,
        0,
        0,
        naturalSize,
        naturalSize,
    );

    const blob = await new Promise((resolve) =>
        canvas.toBlob(resolve, "image/png"),
    );
    if (!blob) {
        ElMessage.error("裁剪失败，请重试");
        return;
    }

    const formData = new FormData();
    formData.append("photo", blob, data.crop.file?.name ?? "cropped.png");
    if (data.form.shopId) {
        formData.append("shopId", data.form.shopId);
    }

    uploadLoading.value = true;
    try {
        const res = await request.post("/file/uploadShopCover", formData);
        if (res.code === "200") {
            data.form.coverPath = res.data || res.data?.url;
            ElMessage.success("图片上传成功");
            closeCropDialog();
        } else {
            ElMessage.error(res.msg || "上传失败");
        }
    } catch (error) {
        console.error("上传图片失败:", error);
        ElMessage.error("图片上传失败");
    } finally {
        uploadLoading.value = false;
    }
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

const loadPermission = () => {
    // request.get();
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

const uploadUrl = "/file/uploadShopCover";
const fileList = ref([]);
const uploadLoading = ref(false);

const beforeUpload = async (file) => {
    const isImage = file.type.startsWith("image/");
    const isLt5M = file.size / 1024 / 1024 < 5;

    if (!isImage) {
        ElMessage.error("只能上传图片文件!");
        return false;
    }
    if (!isLt5M) {
        ElMessage.error("图片大小不能超过5MB!");
        return false;
    }

    data.crop.file = file;
    data.cropVisible = true;
    const reader = new FileReader();
    reader.onload = (e) => {
        data.crop.imageSrc = e.target.result;
    };
    reader.readAsDataURL(file);

    return false;
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
.crop-dialog-body {
    display: flex;
    gap: 20px;
}
.crop-left {
    flex: 1;
    min-width: 360px;
}
.crop-area {
    position: relative;
    width: 100%;
    min-height: 420px;
    border: 1px solid #dcdfe6;
    background: #f5f7fa;
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    overflow: hidden;
}
.crop-area img.crop-image {
    max-width: 100%;
    max-height: 100%;
    width: auto;
    height: auto;
    display: block;
    user-select: none;
    pointer-events: none;
}
.crop-rect {
    z-index: 2;
}
.crop-right {
    width: 240px;
    flex-shrink: 0;
}
.crop-preview {
    width: 200px;
    height: 200px;
    border: 1px solid #dcdfe6;
    border-radius: 4px;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
.goods-img-preview {
    width: 120px;
    height: 120px;
    object-fit: cover;
    border-radius: 4px;
    display: inline-block;
}
.table-img-preview {
    width: 80px;
    height: 80px;
    display: inline-block;
}
.table-img-preview .el-image__inner {
    width: 100% !important;
    height: 100% !important;
    object-fit: cover !important;
}
.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}
</style>
