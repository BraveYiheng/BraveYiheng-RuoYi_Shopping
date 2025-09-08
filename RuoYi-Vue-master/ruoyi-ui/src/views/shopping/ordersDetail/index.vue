<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名" prop="orderProductName" label-width="90px">
        <el-input
          v-model="queryParams.orderProductName"
          placeholder="请输入商品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品数量" prop="orderProductCount">
        <el-input
          v-model="queryParams.orderProductCount"
          placeholder="请输入订单商品的数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属用户" prop="orderUserName" label-width="90px">
        <el-input
          v-model="queryParams.orderUserName"
          placeholder="请输入所属用户名"
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
          :disabled="true"
          v-hasPermi="['shopping:ordersDetail:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="true"
          @click="handleUpdate"
          v-hasPermi="['shopping:ordersDetail:edit']"
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
          v-hasPermi="['shopping:ordersDetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shopping:ordersDetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordersDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单详细ID" align="center" prop="orderDetailId" />
      <el-table-column label="商品名" align="center" prop="orderProductName" />
      <el-table-column label="商品图片" align="center" prop="orderProductImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.orderProductImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品数量" align="center" prop="orderProductCount" />
      <el-table-column label="订单所属用户" align="center" prop="orderUserName" />
      <el-table-column label="对应订单ID" align="center" prop="orderId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            :disabled="true"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shopping:ordersDetail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shopping:ordersDetail:remove']"
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

    <!-- 添加或修改订单详细信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品表外键" prop="orderProductName">
          <el-input v-model="form.orderProductName" placeholder="请输入商品名" />
        </el-form-item>
        <el-form-item label="订单商品的数量" prop="orderProductCount">
          <el-input v-model="form.orderProductCount" placeholder="请输入订单商品的数量" />
        </el-form-item>
        <el-form-item label="订单表外键" prop="orderUserName">
          <el-input v-model="form.orderUserName" placeholder="请输入订单表外键" />
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
import { listOrdersDetail, getOrdersDetail, delOrdersDetail, addOrdersDetail, updateOrdersDetail } from "@/api/shopping/ordersDetail"

export default {
  name: "OrdersDetail",
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
      // 订单详细信息表格数据
      ordersDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderProductName: null,
        orderProductCount: null,
        orderUserName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询订单详细信息列表 */
    getList() {
      this.loading = true
      listOrdersDetail(this.queryParams).then(response => {
        this.ordersDetailList = response.rows
        this.total = response.total
        this.loading = false
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
        orderDetailId: null,
        orderProductName: null,
        orderProductCount: null,
        orderUserName: null
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
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderDetailId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = false
      this.title = "添加订单详细信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const orderDetailId = row.orderDetailId || this.ids
      getOrdersDetail(orderDetailId).then(response => {
        this.form = response.data
        this.open = false
        this.title = "修改订单详细信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderDetailId != null) {
            updateOrdersDetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addOrdersDetail(this.form).then(response => {
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
      const orderDetailIds = row.orderDetailId || this.ids
      this.$modal.confirm('是否确认删除订单详细信息编号为"' + orderDetailIds + '"的数据项？').then(function() {
        return delOrdersDetail(orderDetailIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shopping/ordersDetail/export', {
        ...this.queryParams
      }, `ordersDetail_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
