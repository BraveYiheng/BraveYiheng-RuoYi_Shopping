<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="收货地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="addressUserNickName">
        <el-input
          v-model="queryParams.addressUserNickName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['shopping:address:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shopping:address:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shopping:address:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shopping:address:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="addressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="地址ID" align="center" prop="addressId" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="用户昵称" align="center" prop="addressUserNickName" />
      <el-table-column label="收货人电话" align="center" prop="phone" />
      <el-table-column label="是否为默认地址" align="center" prop="addressIsfo">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_address_default" :value="scope.row.addressIsfo"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shopping:address:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shopping:address:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改地址信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="用户名" prop="addressUserId">
          <el-select v-model="form.addressUserId" placeholder="请选择用户">
            <el-option
              v-for="item in userNickNames"
              :key="item.userId"
              :label="item.userNickName"
              :value="item.userId"
            >
              <span>{{item.userNickName}}</span>
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>

        <el-form-item label="默认地址" prop="addressIsfo">
          <el-select v-model="form.addressIsfo" placeholder="请选择是否为默认地址">
            <el-option
              v-for="dict in dict.type.sys_address_default"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAddress, getAddress, delAddress, addAddress, updateAddress } from "@/api/shopping/address"
import {listAllUserInfo} from "@/api/shopping/userInfo";

export default {
  name: "Address",
  dicts: ['sys_address_default'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 地址信息表格数据
      addressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        address: null,
        addressUserNickName: null
      },

      //用户名称
      userNickNames: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          { pattern: /^1[3-9]\d{9}$/, message: "手机号格式不正确", trigger: "blur" }
        ],
        addressUserId: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        addressIsfo: [
          { required: true, message: "是否默认地址不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
    this.getAllUserNickNames()
  },

  watch: {
    $route() {
      // 适用于路由参数变化或路由切换
      this.getList()
      this.getAllUserNickNames()
    }
  },

  methods: {
    /** 查询地址信息列表 */
    getList() {
      this.loading = true
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows
        this.total = response.total
        this.loading = false
      })
    },

    /* 查询全部用户名称
    * 用于下拉框
    *  */
    getAllUserNickNames() {
      this.loading = true
      listAllUserInfo().then(response => {
        this.userNickNames = response.rows || []
      }).catch(error => {
        console.error("获取用户名失败", error)
        this.$message.error("获取用户名失败，请刷新页面")
      })
    },

    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        addressId: null,
        address: null,
        addressUserNickName: null,
        addressIsfo: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      // 清空查询参数对象
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        address: null,
        addressUserNickName: null
      };
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.addressId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加地址信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const addressId = row.addressId || this.ids
      getAddress(addressId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改地址信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.addressId != null) {
            updateAddress(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAddress(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const addressIds = row.addressId || this.ids
      this.$modal.confirm('是否确认删除地址信息编号为"' + addressIds + '"的数据项？').then(function() {
        return delAddress(addressIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shopping/address/export', {
        ...this.queryParams
      }, `address_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
