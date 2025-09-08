const { baseUrl } = require('../../utils/config');

Page({
  data: {
    categories: [],
    currentCategory: -1, // 默认选中全部类别
    currentTab: 0,
    goodsList: [],
    loading: false
  },

  onLoad() {
    this.getCategories();
  },

  // 获取分类列表
  getCategories() {
    wx.request({
      url: baseUrl + '/product/type/list',
      method: 'GET',
      success: (res) => {
        if (res.data.code === 200) {
          this.setData({
            categories: res.data.rows
          });
          console.log(res.data);
          // 获取全部商品
          this.getGoodsList();
        }
      },
      fail: (err) => {
        wx.showToast({
          title: '获取分类失败',
          icon: 'none'
        });
      }
    });
  },

  // 获取商品列表
  getGoodsList(typeId) {
    if (this.data.loading) return;
    this.setData({ loading: true });
    
    let url = baseUrl + '/product/list/listAll';
    let params = {};

    // 如果不是全部类别，添加typeId参数
    if (typeId) {
      params.productTypeId = typeId;
    }

    // 根据当前选中的tab添加排序参数
    if (this.data.currentTab === 1) {
      // 价格降序
      params.productSort=1
    } else if (this.data.currentTab === 2) {
      // 价格升序
      params.productSort=0
    } else if (this.data.currentTab === 0) {
      // 热门排序（按创建时间降序）
      params.orderByColumn = 'productCreateTime';
      params.isAsc = 'desc';
    }

    wx.request({
      url: url,
      method: 'GET',
      data: params,
      success: (res) => {
        if (res.data.code === 200) {
          this.setData({
            goodsList: res.data.rows
          });
          console.log(this.data.goodsList);
        }
      },
      fail: (err) => {
        wx.showToast({
          title: '获取商品列表失败',
          icon: 'none'
        });
      },
      complete: () => {
        this.setData({ loading: false });
      }
    });
  },

  // 分类点击事件
  onCategoryTap(e) {
    const index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      currentCategory: index
    });
    console.log(index)
    // 如果是全部类别（index === -1），不传递typeId
    if (index === -1) {
      this.getGoodsList();
    } else {
      // 确保categories数组存在且有数据
      if (this.data.categories && this.data.categories[index]) {
        const typeId = this.data.categories[index].typeId;
        console.log(typeId)
        this.getGoodsList(typeId);
      } else {
        console.error('分类数据不存在');
        wx.showToast({
          title: '分类数据错误',
          icon: 'none'
        });
      }
    }
  },

  // Tab切换事件
  onTabTap(e) {
    const index = parseInt(e.currentTarget.dataset.index);
    this.setData({
      currentTab: index
    });
    // 重新获取商品列表（带排序）
    if (this.data.currentCategory === -1) {
      this.getGoodsList();
    } else {
      // 确保categories数组存在且有数据
      if (this.data.categories && this.data.categories[this.data.currentCategory]) {
        const typeId = this.data.categories[this.data.currentCategory].typeId;
        this.getGoodsList(typeId);
      } else {
        console.error('分类数据不存在');
        wx.showToast({
          title: '分类数据错误',
          icon: 'none'
        });
      }
    }
  },

  // 下拉刷新
  onPullDownRefresh() {
    if (this.data.currentCategory === -1) {
      this.getGoodsList();
    } else {
      // 确保categories数组存在且有数据
      if (this.data.categories && this.data.categories[this.data.currentCategory]) {
        const typeId = this.data.categories[this.data.currentCategory].typeId;
        this.getGoodsList(typeId);
      } else {
        console.error('分类数据不存在');
        wx.showToast({
          title: '分类数据错误',
          icon: 'none'
        });
      }
    }
    wx.stopPullDownRefresh();
  },
  onInput(e) {
    this.setData({
      searchName: e.detail.value,
    });
  },
  onSearch() {
    const { searchName } = this.data;
    wx.navigateTo({
      url: `/pages/search/search?searchName=${encodeURIComponent(searchName)}`,
    });
  },

  // 商品点击事件
  onGoodsTap(e) {
    const productId = e.currentTarget.dataset.id;
    // console.log(productId);
    wx.navigateTo({
      url: `/pages/productinfo/productinfo?productId=${productId}`,
    });
  }
});