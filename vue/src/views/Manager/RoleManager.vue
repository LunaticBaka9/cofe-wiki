<template>
    <el-main>
        <div class="card" style="margin-bottom: 5px">
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.roleName"
                placeholder="请输入角色名称查询"
                :prefix-icon="Search"
                clearable
                @keyup.enter.native="load"
                @clear="load"
            ></el-input>
            <el-input
                style="width: 260px; margin-right: 5px"
                v-model="data.roleCode"
                placeholder="请输入角色代码查询"
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
        </div>

        <div class="card" style="margin-bottom: 5px">
            <el-table
                stripe
                border
                :data="data.tableData"
                style="width: 100%"
                :header-cell-style="{ color: '#333', backgroundColor: '#ffb6c1' }"
            >
                <el-table-column prop="roleId" label="ID" width="80" />
                <el-table-column prop="roleName" label="角色名称" />
                <el-table-column prop="roleCode" label="角色代码" />
                <el-table-column prop="description" label="描述" />
                <el-table-column prop="createTime" label="创建时间" />
                <el-table-column prop="updateTime" label="更新时间" />
                <el-table-column label="操作" min-width="150" fixed="right">
                    <template #default="scope">
                        <el-button size="small" @click="handleEdit(scope.row)"> 修改 </el-button>
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

        <el-dialog v-model="data.formVisible" title="角色信息" width="500" destroy-on-close>
            <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="100px" style="padding: 20px 30px">
                <el-form-item prop="roleName" label="角色名称">
                    <el-input v-model="data.form.roleName" autocomplete="off" placeholder="例如：超级管理员" />
                </el-form-item>
                <el-form-item prop="roleCode" label="角色代码">
                    <el-input v-model="data.form.roleCode" autocomplete="off" placeholder="例如：admin" />
                </el-form-item>
                <el-form-item prop="description" label="描述">
                    <el-input
                        v-model="data.form.description"
                        type="textarea"
                        :rows="3"
                        autocomplete="off"
                        placeholder="请输入角色描述"
                    />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="data.formVisible = false">取消</el-button>
                    <el-button type="primary" @click="save"> 保存 </el-button>
                </div>
            </template>
        </el-dialog>
    </el-main>
</template>

<script setup name="roleManager">
import request from "@/utils/request.js";
import { onBeforeMount, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { Search } from '@element-plus/icons-vue';

onBeforeMount(() => {
    const userStr = localStorage.getItem("code_user");
    if (userStr) {
        const user = JSON.parse(userStr);
        if (!user.userId) {
            location.href = "/login";
        } else if (user.userType !== "admin") {
            location.href = "/noPermission";
        }
    } else {
        location.href = "/login";
    }
});

const data = reactive({
    roleName: null,
    roleCode: null,
    pageNum: 1,
    pageSize: 10,
    total: 0,
    tableData: [],
    formVisible: false,
    form: {},
    rules: {
        roleName: [
            { required: true, message: "请填写角色名称", trigger: "blur" },
        ],
        roleCode: [
            { required: true, message: "请填写角色代码", trigger: "blur" },
            { pattern: /^[a-zA-Z_][a-zA-Z0-9_]*$/, message: "角色代码只能包含字母、数字和下划线", trigger: "blur" }
        ],
    },
});

const formRef = ref();

const load = () => {
    request
        .get("role/selectPage", {
            params: {
                pageNum: data.pageNum,
                pageSize: data.pageSize,
                roleName: data.roleName,
                roleCode: data.roleCode,
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
    data.roleName = null;
    data.roleCode = null;
    load();
};

const handleAdd = () => {
    data.formVisible = true;
    data.form = {};
};

const handleEdit = (row) => {
    data.form = JSON.parse(JSON.stringify(row));
    data.formVisible = true;
};

const add = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            request.post("/role/add", data.form).then((res) => {
                if (res.code === "200") {
                    data.formVisible = false;
                    ElMessage.success("新增成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        } else {
            ElMessage.error("请检查表单填写");
        }
    });
};

const update = () => {
    formRef.value.validate((valid) => {
        if (valid) {
            request.put("/role/update", data.form).then((res) => {
                if (res.code === "200") {
                    data.formVisible = false;
                    ElMessage.success("修改成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        } else {
            ElMessage.error("请检查表单填写");
        }
    });
};

const del = (row) => {
    ElMessageBox.confirm("确认删除此角色？", "删除确认", { type: "warning" })
        .then(() => {
            request.delete("/role/delete", { params: { roleId: row.roleId } }).then((res) => {
                if (res.code === "200") {
                    ElMessage.success("删除成功");
                    load();
                } else {
                    ElMessage.error(res.msg);
                }
            });
        })
        .catch(() => {});
};

const save = () => {
    data.form.roleId ? update() : add();
};
</script>

<style scoped>
.card {
    padding: 10px;
    border-radius: 5px;
    background-color: #fff;
    margin-bottom: 10px;
}
</style>