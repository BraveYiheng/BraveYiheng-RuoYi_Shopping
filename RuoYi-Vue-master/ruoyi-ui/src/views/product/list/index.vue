<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品类别" prop="productTypeId">
        <el-select v-model="queryParams.productTypeId" placeholder="请选择商品类别">
          <el-option
            v-for="item in productTypes"
            :key="item.typeId"
            :label="item.typeName"
            :value="item.typeId"
          >
            <span>{{item.typeName}}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="起始价格" prop="beginProductPrice">
        <el-input
          v-model="queryParams.beginProductPrice"
          placeholder="请输入起始价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结束价格" prop="endProductPrice">
        <el-input
          v-model="queryParams.endProductPrice"
          placeholder="请输入结束价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品状态" prop="productStatus">
        <el-select v-model="queryParams.productStatus" placeholder="请选择商品状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_product_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['product:list:add']"
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
          v-hasPermi="['product:list:edit']"
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
          v-hasPermi="['product:list:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['product:list:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 显示表单数据-->
    <el-table v-loading="loading" :data="listList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="productId" />
      <el-table-column label="商品名称" align="center" prop="productName" />
      <el-table-column label="商品价格" align="center" prop="productPrice" />
      <el-table-column label="商品库存" align="center" prop="productCount" />
      <el-table-column label="商品图片" align="center" prop="productImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.productImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品规格" align="center" prop="productSize" />
      <el-table-column label="生产日期" align="center" prop="productCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品品牌" align="center" prop="productBrand" />
      <el-table-column label="商品类别" align="center" prop="productTypeName" />
      <el-table-column label="商品包装" align="center" prop="productPackage" />
      <el-table-column label="商品标识" align="center" prop="productSign" />
      <el-table-column label="是否加糖" align="center" prop="productSugar">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_product_sugar" :value="scope.row.productSugar"/>
        </template>
      </el-table-column>
      <el-table-column label="商品状态" align="center" prop="productStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_product_status" :value="scope.row.productStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:list:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:list:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      :page-sizes="pageSizes"
      @pagination="getList"
    />

    <!-- 添加或修改商品列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品价格" prop="productPrice">
          <el-input v-model="form.productPrice" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="商品库存" prop="productCount">
          <el-input v-model="form.productCount" placeholder="请输入商品库存" />
        </el-form-item>
        <el-form-item label="商品图片" prop="productImg">
          <image-upload v-model="form.productImg"/>
        </el-form-item>
        <el-form-item label="商品规格" prop="productSize">
          <el-input v-model="form.productSize" placeholder="请输入商品规格" />
        </el-form-item>
        <el-form-item label="生产日期" prop="productCreateTime">
          <el-date-picker clearable
            v-model="form.productCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生产日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="商品品牌" prop="productBrand">
          <el-input v-model="form.productBrand" placeholder="请输入商品品牌" />
        </el-form-item>
        <el-form-item label="商品类别" prop="productTypeId">
          <el-select v-model="form.productTypeId" placeholder="请选择商品类别">
            <el-option
              v-for="item in productTypes"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId"
            >
              <span>{{item.typeName}}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品描述" prop="productDescribe">
          <el-input v-model="form.productDescribe" type="textarea" placeholder="请输入商品描述" />
        </el-form-item>
        <el-form-item label="商品包装" prop="productPackage">
          <el-input v-model="form.productPackage" placeholder="请输入商品包装" />
        </el-form-item>
        <el-form-item label="商品标识" prop="productSign">
          <el-input v-model="form.productSign" placeholder="请输入商品标识" />
        </el-form-item>
        <el-form-item label="是否加糖" prop="productSugar">
          <el-select v-model="form.productSugar" placeholder="请选择是否加糖">
            <el-option
              v-for="dict in dict.type.sys_product_sugar"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品状态" prop="productStatus">
          <el-select v-model="form.productStatus" placeholder="请选择商品状态">
            <el-option
              v-for="dict in dict.type.sys_product_status"
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
import { listList, getList, delList, addList, updateList } from "@/api/shopping/productList"
import {listAllTypes} from "@/api/shopping/productType" // 导入商品类别API

export default {
  name: "List",
  dicts: ['sys_product_status', 'sys_product_sugar'],
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
      // 商品列表表格数据
      listList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //可选分页数目
      pageSizes:[8,10,20,50],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        productName: null,
        productPrice: null,
        beginProductPrice:null,
        endProductPrice:null,
        productTypeId: null,
        productStatus: null
      },

      //商品类别
      productTypes: [], // 存储商品类别数据


      // 表单参数
      form: {
        productId: null,
        productName: null,
        productPrice: null,
        productCount: null,
        productImg: null,
        productSize: null,
        productCreateTime: null,
        productBrand: null,
        productTypeId: null,
        productPackage: null,
        productSign: null,
        productSugar: null,
        productStatus: null,
        productDescribe: null
      },
      // 表单校验
      rules: {
        productName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        productPrice: [
          { required: true, message: "商品价格不能为空", trigger: "blur" }
        ],
        productTypeId: [
          { required: true, message: "商品类别不能为空", trigger: "change" }
        ]

      }
    }
  },

  created() {
    this.getList()
    this.getProductTypes() // 加载商品类别
  },

  watch: {
    $route() {
      // 适用于路由参数变化或路由切换
      this.getList();
      this.getProductTypes();
    }
  },


  methods: {
    /** 查询商品列表列表 */
    getList() {
      this.loading = true
      listList(this.queryParams).then(response => {
        this.listList = response.rows
        this.total = response.total
        this.loading = false
      })
    },

    /* 查询商品类别 */
    getProductTypes() {
      this.loading = true
      listAllTypes().then(response => {
        this.productTypes = response.rows || []
      }).catch(error => {
        console.error("获取商品类别失败", error)
        this.$message.error("获取商品类别失败，请刷新页面")
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
        productId: null,
        productName: null,
        productPrice: null,
        productCount: null,
        productImg: null,
        productSize: null,
        productCreateTime: null,
        productBrand: null,
        productTypeId: null,
        productPackage: null,
        productSign: null,
        productSugar: null,
        productStatus: null,
        productDescribe: null
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
      this.ids = selection.map(item => item.productId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加商品列表"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const productId = row.productId || this.ids
      getList(productId).then(response => {
        console.log(response.data)
        this.form = response.data
        this.open = true
        this.title = "修改商品列表"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productId != null) {
            updateList(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addList(this.form).then(response => {
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
      const productIds = row.productId || this.ids
      this.$modal.confirm('是否确认删除商品列表编号为"' + productIds + '"的数据项？').then(function() {
        return delList(productIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport: function () {
      this.download('product/list/export', {
        ...this.queryParams
      }, `list_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
