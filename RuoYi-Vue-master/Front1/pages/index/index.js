Page({
  data: {
    carouselList: [],
    productList: [],
    searchName: "",
  },
  onLoad: function () {
    this.getcarouselList();
    this.getproductList();
    this.islogin();
  },
  islogin() {
    const token = wx.getStorageSync("token");
    if (!token) {
      wx.redirectTo({ url: "/pages/login/login" });
      return;
    }
  },
  getcarouselList() {
    wx.request({
      url: "http://localhost:8081/shopping/carousel/list",
      success: (res) => {
        // 使用箭头函数保持this指向当前页面实例
        // console.log(res.data.rows);
        this.setData({
          carouselList: res.data.rows,
        });
        // console.log(this.data.carouselList);
      },
    });
  },
  getproductList() {
    wx.request({
      url: "http://localhost:8081/product/list/listAll",
      success: (res) => {
        // 使用箭头函数保持this指向当前页面实例
        // console.log(res.data.rows);
        this.setData({
          productList: res.data.rows,
        });
        // console.log(this.data.productList);
      },
    });
  },
  goToProductInfo(e) {
    const productId = e.currentTarget.dataset.id;
    console.log(productId);
    wx.navigateTo({
      url: `/pages/productinfo/productinfo?productId=${productId}`,
    });
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
});
