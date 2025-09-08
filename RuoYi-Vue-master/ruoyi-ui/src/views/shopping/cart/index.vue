<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="cartUserNickName">
        <el-input
          v-model="queryParams.cartUserNickName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名" prop="cartProductName">
        <el-input
          v-model="queryParams.cartProductName"
          placeholder="请输入商品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品数量" prop="productCount">
        <el-input
          v-model="queryParams.productCount"
          placeholder="请输入商品数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="daterangeProductAddTime"
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
          v-hasPermi="['shopping:cart:add']"
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
          v-hasPermi="['shopping:cart:edit']"
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
          v-hasPermi="['shopping:cart:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shopping:cart:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cartList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="购物车记录ID" align="center" prop="cartId" />
      <el-table-column label="用户名" align="center" prop="cartUserNickName" />
      <el-table-column label="商品名" align="center" prop="cartProductName" />
      <el-table-column label="商品数量" align="center" prop="productCount" />
      <el-table-column label="添加时间" align="center" prop="productAddTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productAddTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shopping:cart:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shopping:cart:remove']"
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

    <!-- 添加或修改购物车信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名">
          <el-select v-model="form.cartUserId" placeholder="请选择用户">
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

        <el-form-item label="商品名">
          <el-select v-model="form.cartProductId" placeholder="请选择商品">
            <el-option
              v-for="item in productNames"
              :key="item.productId"
              :label="item.productName"
              :value="item.productId"
            >
              <span>{{item.productName}}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品数量" prop="productCount">
          <el-input v-model="form.productCount" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="添加时间" prop="productAddTime">
          <el-date-picker clearable
                          v-model="form.productAddTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择添加时间">
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
import { listCart, getCart, delCart, addCart, updateCart } from "@/api/shopping/cart"
import {listAllUserInfo} from "@/api/shopping/userInfo";
import {listAllProductNames} from "@/api/shopping/productList";

export default {
  name: "Cart",
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
      // 购物车信息管理表格数据
      cartList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 添加时间时间范围
      daterangeProductAddTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cartUserNickName: null,
        cartProductName: null,
        productCount: null,
        productAddTime: null
      },
      //用户名称
      userNickNames: [],
      //商品名称
      productNames: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
    this.getAllUserNickNames()
    this.getAllProductNames()
  },
  watch: {
    $route() {
      // 适用于路由参数变化或路由切换
      this.getList()
      this.getAllUserNickNames()
      this.getAllProductNames()
    }
  },
  methods: {
    /** 查询购物车信息管理列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeProductAddTime && '' !== this.daterangeProductAddTime) {
        this.queryParams.params["beginProductAddTime"] = this.daterangeProductAddTime[0]
        this.queryParams.params["endProductAddTime"] = this.daterangeProductAddTime[1]
      }
      listCart(this.queryParams).then(response => {
        console.log(response.rows)
        this.cartList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询全部用户名称
     * 用于下拉框
     *  */
    getAllUserNickNames() {
      this.loading = true
      listAllUserInfo().then(response => {
        this.userNickNames = response.rows || []
      }).catch(error => {
        console.error("获取商品名失败", error)
        this.$message.error("获取商品名失败，请刷新页面")
      })
    },
    /** 查询全部商品名称
     * 用于下拉框
     *  */
    getAllProductNames() {
      this.loading = true
      listAllProductNames().then(response => {
        this.productNames = response.rows || []
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
        cartId: null,
        cartUserNickName: null,
        cartProductName: null,
        productCount: null,
        productAddTime: null
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
      this.daterangeProductAddTime = []
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.cartId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加购物车信息管理"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const cartId = row.cartId || this.ids
      getCart(cartId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改购物车信息管理"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cartId != null) {
            updateCart(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCart(this.form).then(response => {
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
      const cartIds = row.cartId || this.ids
      this.$modal.confirm('是否确认删除购物车信息管理编号为"' + cartIds + '"的数据项？').then(function() {
        return delCart(cartIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shopping/cart/export', {
        ...this.queryParams
      }, `cart_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
