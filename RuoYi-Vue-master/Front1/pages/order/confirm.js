import { baseUrl } from "../../utils/config";

Page({
  data: {
    product: {
      count: 1,
    },
    cartItems: [],
    totalPrice: 0,
    addressList: [],
    defaultAddress: null,
    orderId: null,
    isFromCart: false,
    isFromOrderCenter: false,
  },
  onLoad(options) {
    if (options.fromCart) {
      console.log(options.items);
      const items = JSON.parse(options.items);
      this.setData({
        cartItems: items,
        isFromCart: true,
        isFromOrderCenter: false,
        totalPrice: items
          .reduce(
            (total, item) => total + item.cartProductPrice * item.productCount,
            0
          )
          .toFixed(2),
      });
    } else if (options.fromOrderCenter) {
      console.log('============')
      console.log(options.items);
      console.log('============')
      const items = JSON.parse(options.items);
      this.setData({
        cartItems: items,
        isFromCart: false,
        isFromOrderCenter: true,
        totalPrice: items
          .reduce(
            (total, item) => total + item.cartProductPrice * item.productCount,
            0
          )
          .toFixed(2),
      });
    } else {
      const { productId, count } = options;
      console.log(options);
      this.setData({
        "product.count": Number(count) || 1,
        isFromCart: false,
        isFromOrderCenter: false,
      });
      this.getProductDetail(productId);
    }
    this.getUserAddresses();
  },
  onShow() {
    this.getUserAddresses();
  },
  getProductDetail(id) {
    wx.request({
      url: `${baseUrl}/product/list/${id}`,
      method: "GET",
      success: (res) => {
        if (res.data.code === 200 && res.data.data) {
          let product = res.data.data;
          if (product.productImg) {
            product.productImg = baseUrl + product.productImg;
          }
          product.count = this.data.product.count;
          this.setData({
            product,
            totalPrice: (product.productPrice * product.count).toFixed(2),
          });
        }
      },
    });
  },
  getUserAddresses() {
    const userId = wx.getStorageSync("userId");
    wx.request({
      url: `${baseUrl}/shopping/address/list`,
      method: "GET",
      data: { addressUserId: userId },
      success: (res) => {
        if (res.data.code === 200 && res.data.rows) {
          let defaultAddr =
            res.data.rows.find((addr) => addr.addressIsfo == 0) ||
            res.data.rows[0];
          this.setData({
            addressList: res.data.rows,
            defaultAddress: defaultAddr,
          });
          console.log(this.data.defaultAddress);
        } else {
          this.setData({
            addressList: [],
            defaultAddress: null,
          });
        }
      },
    });
  },
  goToAddress() {
    wx.navigateTo({ url: "/pages/address/address" });
  },
  setDefaultAddress(e) {
    const addressId = e.currentTarget.dataset.id;
    const userId = wx.getStorageSync("userId");
    wx.request({
      url: `${baseUrl}/shopping/address`,
      method: "PUT",
      data: {
        addressId,
        addressUserId: userId,
        addressIsfo: 0,
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({ title: "设置成功", icon: "success" });
          this.getUserAddresses();
        }
      },
    });
  },
  increaseCount() {
    const { product } = this.data;
    this.setData({
      "product.count": product.count + 1,
      totalPrice: (product.productPrice * (product.count + 1)).toFixed(2),
    });
  },
  decreaseCount() {
    const { product } = this.data;
    if (product.count > 1) {
      this.setData({
        "product.count": product.count - 1,
        totalPrice: (product.productPrice * (product.count - 1)).toFixed(2),
      });
    }
  },
  payNow() {
    if (!this.data.defaultAddress) {
      wx.showToast({
        title: "请选择您的地址",
        icon: "none",
      });
      return;
    }

    const userId = wx.getStorageSync("userId");
    const { isFromCart, isFromOrderCenter,cartItems, product, totalPrice } = this.data;

    // 创建订单
    wx.request({
      url: `${baseUrl}/shopping/orders`,
      method: "POST",
      data: {
        orderUserId: userId,
        orderMoney: Math.round(totalPrice),
        orderStatus: 2,
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.request({
            url: `${baseUrl}/shopping/orders/listUpdateId`,
            method: "GET",
            success: (res) => {
              const orderId = res.data.rows[0].orderId;

              if (isFromCart || isFromOrderCenter) {
                // 购物车结算：批量创建订单详情
                const createDetailPromises = cartItems.map((item) => {
                  return new Promise((resolve, reject) => {
                    wx.request({
                      url: `${baseUrl}/shopping/ordersDetail`,
                      method: "POST",
                      data: {
                        orderId: orderId,
                        orderProductId: item.cartProductId,
                        orderProductName: item.cartProductName,
                        orderProductCount: item.productCount,
                        orderProductImage: item.cartProductUrl,
                        orderMoney: (
                          item.cartProductPrice * item.productCount
                        ).toFixed(2),
                      },
                      success: resolve,
                      fail: reject,
                    });
                  });
                });

                // 等待所有订单详情创建完成
                Promise.all(createDetailPromises)
                  .then(() => {
                    // 获取购物车商品ID数组
                    const cartIds = cartItems.map((item) => item.cartId);
                    if(isFromCart){
                       // 调用删除购物车商品接口
                      wx.request({
                        url: `${baseUrl}/shopping/cart/${cartIds.join(",")}`,
                        method: "DELETE",
                        success: (res) => {
                          if (res.data.code === 200) {
                            // 通知购物车页面更新数据
                            const pages = getCurrentPages();
                            const cartPage = pages.find(
                              (page) => page.route === "pages/cart/cart"
                            );
                            if (cartPage) {
                              cartPage.getCartData();
                            }
  
                            // 直接跳转到支付成功页面
                            wx.redirectTo({
                              url: "/pages/order/success",
                            });
                          } else {
                            wx.showToast({
                              title: "支付成功但删除购物车失败",
                              icon: "none",
                            });
                          }
                        },
                        fail: (err) => {
                          console.error("删除购物车商品失败:", err);
                          wx.showToast({
                            title: "支付成功但删除购物车失败",
                            icon: "none",
                          });
                        },
                      });
                    }else{
                      wx.redirectTo({
                        url: "/pages/order/success",
                      });
                    }
                    
                  })
                  .catch((err) => {
                    console.error("创建订单详情失败:", err);
                    wx.showToast({ title: "支付失败", icon: "none" });
                  });
              } else {
                // 直接购买：创建单个订单详情
                wx.request({
                  url: `${baseUrl}/shopping/ordersDetail`,
                  method: "POST",
                  data: {
                    orderId: orderId,
                    orderProductId: product.productId,
                    orderProductName: product.productName,
                    orderProductCount: product.count,
                    orderProductImage: product.productImg,
                    orderMoney: totalPrice,
                  },
                  success: (res) => {
                    if (res.data.code === 200) {
                      // 直接跳转到支付成功页面
                      wx.redirectTo({
                        url: "/pages/order/success",
                      });
                    }
                  },
                  fail: (err) => console.error("创建订单详情失败:", err),
                });
              }
            },
          });
        }
      },
    });
  },
  addToCart() {
    const userId = wx.getStorageSync("userId");
    const { product } = this.data;

    wx.request({
      url: `${baseUrl}/cart/add`,
      method: "POST",
      data: {
        cartUserId: userId,
        cartUserNickName: wx.getStorageSync("userNickName"),
        cartProductId: product.productId,
        cartProductName: product.productName,
        productCount: product.count,
        productAddTime: new Date(),
      },
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({
            title: "已加入购物车",
            icon: "success",
          });
        }
      },
    });
  },
});
