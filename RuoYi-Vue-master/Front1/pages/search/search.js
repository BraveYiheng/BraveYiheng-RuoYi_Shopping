// pages/search/search.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    searchName: "",
    productList: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (options && options.searchName) {
      this.setData({ searchName: decodeURIComponent(options.searchName) });
      this.onSearch();
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},

  onInput(e) {
    this.setData({
      searchName: e.detail.value,
    });
  },

  onSearch() {
    const { searchName } = this.data;
    wx.request({
      url: "http://localhost:8081/product/list/list",
      method: "GET",
      data: {
        productName: searchName,
      },
      success: (res) => {
        console.log(res)
        if (res.statusCode === 200 && res.data.code === 200) {
          this.setData({
            productList: res.data.rows,
          });
        } else {
          wx.showToast({ title: "查询失败", icon: "none" });
        }
      },
      fail() {
        wx.showToast({ title: "网络异常", icon: "none" });
      },
    });
  },

  goToProductInfo(e) {
    const productId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/productinfo/productinfo?productId=${productId}`,
    });
  },
});
