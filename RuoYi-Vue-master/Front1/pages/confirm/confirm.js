import { baseUrl } from "../../utils/config"
Page({
  data: {
    product: {},
    count: 1,
    totalPrice: 0,
    addressList: [],
    defaultAddress: null
  },
  onLoad(options) {
    const { productId, count } = options;
    this.setData({ count: Number(count) || 1 });
    this.getProductDetail(productId);
    this.getUserAddresses();
  },
  onShow() {
    // 每次显示页面时刷新地址列表
    this.getUserAddresses();
  },
  getProductDetail(id) {
    wx.request({
      url: `${baseUrl}/product/list/${id}`,
      method: 'GET',
      success: (res) => {
        if (res.data.code === 200 && res.data.data) {
          let product = res.data.data;
          if (product.productImg) {
            product.productImg = baseUrl + product.productImg;
          }
          this.setData({
            product,
            totalPrice: (product.productPrice * this.data.count).toFixed(2)
          });
          console.log(this.data.product)
        }
      }
    });
  },
  getUserAddresses() {
    const userId = wx.getStorageSync('userId');
    console.log(userId)
    wx.request({
      url: `${baseUrl}/shopping/address/list`,
      method: 'GET',
      data: { addressUserId: userId},
      success: (res) => {
        console.log(res.data)
        console.log('-----------')
        if (res.data.code === 200 && res.data.rows) {
          let defaultAddr = res.data.rows.find(addr => addr.addressIsfo == 0) || res.data.rows[0];
          
          this.setData({
            addressList: res.data.rows,
            defaultAddress:defaultAddr,
          });
          console.log(this.data.defaultAddress);
        } else {
          this.setData({
            addressList: [],
            defaultAddress: null
          });
        }
      }
    });
  },
  goToAddress() {
    wx.navigateTo({ url: '/pages/address/address' });
  },
  setDefaultAddress(e) {
    const addressId = e.currentTarget.dataset.id;
    // const userId = wx.getStorageSync('userId');
    // console.log(wx.getStorageSync('userId'))
    // console.log('------------')
    // console.log(userId)
    // console.log('------------')
    wx.request({
      url: `${baseUrl}/shopping/address`,
      method: 'GET',
      data: {
        addressId,
        addressUserId: userId1,
        addressIsfo: 0
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({ title: '设置成功', icon: 'success' });
          this.getUserAddresses();
        }
      }
    });
  },
  increaseCount() {
    this.setData({
      count: this.data.count + 1,
      totalPrice: (this.data.product.productPrice * (this.data.count + 1)).toFixed(2)
    });
  },
  decreaseCount() {
    if (this.data.count > 1) {
      this.setData({
        count: this.data.count - 1,
        totalPrice: (this.data.product.productPrice * (this.data.count - 1)).toFixed(2)
      });
    }
  },
  payNow() {
    console.log(this.data.defaultAddressId)
    if (!this.data.defaultAddressId) {
      wx.showToast({ title: '请选择收货地址', icon: 'none' });
      return;
    }
    wx.request({
      url: `${baseUrl}/order/create`,
      method: 'POST',
      data: {
        userId: wx.getStorageSync('userId'),
        productId: this.data.product.productId,
        count: this.data.count,
        addressId: this.data.defaultAddressId
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({ title: '支付成功', icon: 'success' });
          wx.redirectTo({ url: '/pages/order/success' });
        }
      }
    });
  }
})