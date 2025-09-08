// pages/my/my.js
let isNavigating = false;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    userName: "",
    userPhoto: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    const token = wx.getStorageSync("token");
    if (!token) {
      wx.redirectTo({ url: "/pages/login/login" });
      return;
    }
    const userName = wx.getStorageSync("userName") || "未登录";
    const userPhoto =
      wx.getStorageSync("userPhoto") || "/images/default-avatar.png";
    this.setData({ userName, userPhoto });
  },

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

  onLogout() {
    wx.removeStorageSync("token");
    wx.removeStorageSync("userName");
    wx.removeStorageSync("userPhoto");
    wx.redirectTo({ url: "/pages/login/login" });
  },

  goToSetting() {
    wx.navigateTo({ url: "/pages/setting/setting" });
  },
  goToAddress() {
    wx.navigateTo({ url: "/pages/address/address" });
  },
  goToOrderCenter() {
    wx.navigateTo({ url: "/pages/ordercenter/ordercenter" });
  },
  goToOrderTab(e) {
    if (isNavigating) return;
    isNavigating = true;
    const tab = e.currentTarget.dataset.tab;
    wx.setStorageSync("orderTabIndex", tab);
    wx.navigateTo({
      url: "/pages/ordercenter/ordercenter",
      complete: () => {
        setTimeout(() => {
          isNavigating = false;
        }, 1000);
      },
    });
  },
});
