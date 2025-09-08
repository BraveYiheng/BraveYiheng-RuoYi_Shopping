const {baseUrl } = require('../../utils/config');

// pages/productinfo/productinfo.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    product: {},
    loading: true,
    error: null,
    count:1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const productId = options.productId;
    this.getProductDetail(productId);
  },
  getProductDetail(productId) {
    wx.request({
      url: `http://localhost:8081/product/list/${productId}`,
      method: "GET",
      success: (res) => {
        if (res.data.code === 200) {
          this.setData({
            product: res.data.data,
            loading: false,
          });
        } else {
          this.setData({
            error: res.data.msg || "获取商品信息失败",
            loading: false,
          });
        }
      },
      fail: () => {
        this.setData({
          error: "网络错误",
          loading: false,
        });
      },
    });
  },
   // 加入购物车
   addToCart() {
    const userId = wx.getStorageSync('userId');
    if (!userId) {
      wx.showToast({ 
        title: '请先登录', 
        icon: 'none' 
      });
      return;
    }
    const { product} = this.data;
    
    wx.request({
      url: `${baseUrl}/shopping/cart`,
      method: 'POST',
      data: {
        cartUserId: userId,
        cartProductId: product.productId,
        productCount: 1
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({ 
            title: '已加入购物车', 
            icon: 'success' 
          });
        } else {
          wx.showToast({ 
            title: res.data.msg || '添加失败', 
            icon: 'none' 
          });
        }
      },
      fail: () => {
        wx.showToast({ 
          title: '网络错误', 
          icon: 'none' 
        });
      }
    });
  },
  buyNow() {
    const userId = wx.getStorageSync('userId');
    if (!userId) {
      wx.showToast({ 
        title: '请先登录', 
        icon: 'none' 
      });
      return;
    }
    
    wx.navigateTo({
      url: `/pages/order/confirm?productId=${this.data.product.productId}&count=${this.data.count}`
    });
  }
});