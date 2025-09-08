const {baseUrl } = require('../../utils/config');
Page({
  data: {
    product: {},
    count:1
  },
  onLoad(options) {
    const id = options.id;
    this.getProductDetail(id);
  },
  getProductDetail(id) {
    wx.request({
      url: `${baseUrl}/product/list/${id}`,
      method: 'GET',
      success: (res) => {
        console.log(res.data)
        let product = res.data.data || (res.data.rows && res.data.rows[0]) || {};
        // 拼接图片完整路径
        if (product.productImg ) {
          product.productImg = baseUrl + product.productImg;
        }
        this.setData({
          product
        });
      }
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