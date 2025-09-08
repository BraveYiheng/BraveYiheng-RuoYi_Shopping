Page({
  goToOrderCenter() {
    wx.navigateTo({ url: "/pages/ordercenter/ordercenter" });
  },
  
  goToHome() {
    wx.switchTab({
      url: '/pages/index/index'
    });
  }
});
