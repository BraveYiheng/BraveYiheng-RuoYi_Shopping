<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="orderUserNickName">
        <el-input
          v-model="queryParams.orderUserNickName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_order_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建订单时间" label-width="100px">
        <el-date-picker
          v-model="daterangeOrderAddTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['shopping:orders:add']"
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
          v-hasPermi="['shopping:orders:edit']"
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
          v-hasPermi="['shopping:orders:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shopping:orders:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="用户名" align="center" prop="orderUserNickName" />
      <el-table-column label="订单总金额" align="center" prop="orderMoney" />
      <el-table-column label="订单状态" align="center" prop="orderStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_order_status" :value="scope.row.orderStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建订单时间" align="center" prop="orderAddTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderAddTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shopping:orders:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shopping:orders:remove']"
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

    <!-- 添加或修改订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="450px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="用户名" label-width="100px">
          <el-select v-model="form.orderUserId" placeholder="请选择用户">
            <el-option
              v-for="item in userNickNames"
              :key="item.userId"
              :label="item.userNickName"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单总金额" prop="orderMoney" label-width="100px">
          <el-input v-model="form.orderMoney" placeholder="请输入订单总金额" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus" label-width="100px">
          <el-select v-model="form.orderStatus" placeholder="请选择订单状态">
            <el-option
              v-for="dict in dict.type.sys_order_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建订单时间" prop="orderAddTime" label-width="100px">
          <el-date-picker clearable
            v-model="form.orderAddTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建订单时间">
          </el-date-picker>
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
import { listOrders, getOrders, delOrders, addOrders, updateOrders } from "@/api/shopping/orders"
import {listAllUserInfo} from "@/api/shopping/userInfo";

export default {
  name: "Orders",
  dicts: ['sys_order_status'],
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
      // 订单信息表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 创建订单时间时间范围
      daterangeOrderAddTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderUserNickName: null,
        orderStatus: null,
        orderAddTime: null
      },
      //用户名称下拉框中的信息
      userNickNames: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        
      }
    }
  },
  created() {
    this.getList()
    this.getAllUserNickNames(); // 新增调用
  },
  watch: {
    $route() {
      // 适用于路由参数变化或路由切换
      this.getList()
      this.getAllUserNickNames()
    }
  },
  methods: {
    /** 查询订单信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeOrderAddTime && '' !== this.daterangeOrderAddTime) {
        this.queryParams.params["beginOrderAddTime"] = this.daterangeOrderAddTime[0]
        this.queryParams.params["endOrderAddTime"] = this.daterangeOrderAddTime[1]
      }
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.rows
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
        console.log(response.rows)
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
        orderId: null,
        orderUserNickName: null,
        orderMoney: null,
        orderStatus: null,
        orderAddTime: null
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
      this.daterangeOrderAddTime = []
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加订单信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const orderId = row.orderId || this.ids
      getOrders(orderId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改订单信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addOrders(this.form).then(response => {
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
      const orderIds = row.orderId || this.ids
      this.$modal.confirm('是否确认删除订单信息编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrders(orderIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shopping/orders/export', {
        ...this.queryParams
      }, `orders_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
