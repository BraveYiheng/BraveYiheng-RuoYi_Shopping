// pages/login/login.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userName: "",
    userPwd: "",
    userPhoto:""
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

  onUserNameInput(e) {
    this.setData({ userName: e.detail.value });
  },

  onPasswordInput(e) {
    this.setData({ userPwd: e.detail.value });
  },

  onLogin() {
    const { userName, userPwd } = this.data;
    if (!userName || !userPwd) {
      wx.showToast({ title: "请输入账号和密码", icon: "none" });
      return;
    }
    wx.request({
      url: "http://localhost:8081/shopping/userInfo/login",
      method: "POST",
      data: {
        userName: userName,
        userPwd: userPwd,
      },
      header: {
        "content-type": "application/json",
      },
      success: (res) => {
        if (res.data.code === 200) {

          wx.setStorageSync("token", res.data.token);
          console.log("用户token")
          console.log(res.data);
          wx.setStorageSync('userId', res.data.userId)
          console.log(wx.getStorageSync('userId'))
          console.log('------------')
          wx.setStorageSync("userName", res.data.userName);
          console.log(wx.getStorageSync('userName'))
          console.log('------------')
          wx.setStorageSync("userPhoto", res.data.userPhoto || "");
          wx.showToast({ title: "登录成功", icon: "success" });
          wx.switchTab({ url: "/pages/index/index" });
        } else {
          wx.showToast({ title: res.data.msg || "登录失败", icon: "none" });
        }
      },
      fail: () => {
        wx.showToast({ title: "网络错误", icon: "none" });
      },
    });
  },
});
