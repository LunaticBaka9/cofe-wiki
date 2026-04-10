<template>
    <el-main>
        <div class="card" style="margin-bottom: 5px">
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.username"
                placeholder="请输入账号查询"
                :prefix-icon="Search"
                clearable
                @keyup.enter.native="load"
                @clear="load"
            ></el-input>
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.name"
                placeholder="请输入名称查询"
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
                stripe
                border
                :data="data.tableData"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                :header-cell-style="{ color: '#333', backgroundColor: '#ffb6c1' }"
            >
                <el-table-column type="selection" width="55" />
                <el-table-column prop="username" label="账号" />
                <el-table-column prop="password" label="密码" />
                <el-table-column prop="name" label="名称" />
                <el-table-column prop="email" label="邮箱" />
                <el-table-column prop="phone" label="电话" />
                <el-table-column prop="createTime" label="创建时间" />
                <el-table-column prop="userType" label="用户类型" />
                <el-table-column label="操作">
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

        <el-dialog v-model="data.formVisible" title="用户信息" width="500" destroy-on-close>
            <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding: 20px 30px">
                <el-form-item prop="username" label="账号">
                    <el-input v-model="data.form.username" autocomplete="off" />
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input v-model="data.form.password" autocomplete="off" />
                </el-form-item>
                <el-form-item prop="name" label="名称">
                    <el-input v-model="data.form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item prop="email" label="邮箱">
                    <el-input v-model="data.form.email" autocomplete="off" />
                </el-form-item>
                <el-form-item prop="phone" label="电话">
                    <el-input v-model="data.form.phone" autocomplete="off" />
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

<script setup name="userManager">
import request from "@/utils/request.js";
import { onBeforeMount, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

onBeforeMount(() => {
    const userStr = localStorage.getItem("code_user");
    if (userStr) {
        const user = JSON.parse(userStr);
        if (!user.userId) {
            location.href = "/login";
        } else if (user.userType !== "admin") {
            location.href = "/NoPermission";
        }
    } else {
        location.href = "/login";
    }
});

const data = reactive({
    username: null,
    name: null,
    pageNum: 1,
    pageSize: 10,
    total: 6,
    tableData: [],
    formVisible: false,
    form: {},
    rules: {
        username: [
            { required: true, message: "请填写账号", trigger: "blur" },
            { min: 3, message: "至少输入3位", trigger: "blur" },
        ],
        password: [{ required: true, message: "请填写密码", trigger: "blur" }],
        name: [{ required: true, message: "请填写名称", trigger: "blur" }],
        email: [{ required: true, message: "请填写邮箱", trigger: "blur" }],
    },
    rows: [],
});

const formRef = ref();

const load = () => {
    request
        .get("admin/selectPage", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                username: data.username,
                name: data.name,
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

const reset = () => {
    data.username = null;
    data.name = null;
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
            request.post("/admin/add", data.form).then((res) => {
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
            request.put("/admin/update", data.form).then((res) => {
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
            request.put("/admin/delete", row).then((res) => {
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
            request.put("/admin/deleteBatch", data.rows).then((res) => {
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

const save = () => {
    data.form.userId ? update() : add();
};
</script>

<style scoped>
.card {
    padding: 10px;
    border-radius: 5px;
}
</style>
