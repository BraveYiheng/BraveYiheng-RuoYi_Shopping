// pages/ordercenter/ordercenter.js
import { baseUrl } from "../../utils/config";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    tabs: [
      { name: "全部", status: "" },
      { name: "待付款", status: 0 },
      { name: "待发货", status: 2 },
      { name: "待收货", status: 3 },
      { name: "退款", status: 5 },
    ],
    activeTab: 0,
    orderList: [],
    userId: null,
    products: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("ordercenter onLoad options:", options);
    console.log("Current pages:", getCurrentPages());
    const userId = wx.getStorageSync("userId");
    let activeTab = 0;
    const tabFromStorage = wx.getStorageSync("orderTabIndex");
    if (tabFromStorage !== "" && tabFromStorage !== undefined) {
      activeTab = Number(tabFromStorage);
      wx.removeStorageSync("orderTabIndex");
    } else if (options && options.tab !== undefined) {
      activeTab = Number(options.tab);
    }
    this.setData({ userId, activeTab });
    this.fetchOrders(activeTab);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // 不再fetchOrders，避免tab被重置
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

  goToProductInfo(e) {
    const productId = e.currentTarget.dataset.id;
    console.log(productId);
    wx.navigateTo({
      url: `/pages/productinfo/productinfo?productId=${productId}`,
    });
  },

  onTabChange(e) {
    const newTab = Number(e.currentTarget.dataset.index);
    this.setData({ activeTab: newTab });
    this.fetchOrders(newTab);
  },

  fetchOrders(tabIndex) {
    const { userId, tabs } = this.data;
    const activeTab = tabIndex !== undefined ? tabIndex : this.data.activeTab;
    const status = tabs[activeTab].status;
    wx.request({
      url: "http://localhost:8081/shopping/ordersDetail/list",
      method: "GET",
      data: {
        orderUserId: userId,
        orderStatus: status,
        pageNum: 1,
        pageSize: 100, // 或更大，确保能拿到全部
      },
      success: (res) => {
        console.log(res.data.rows);

        const rawList = res.data.rows; // 一维数组
        const orderMap = {};
        rawList.forEach((item) => {
          const orderId = item.orderId;
          if (!orderMap[orderId]) {
            orderMap[orderId] = {
              orderId: item.orderId,
              orderUserName: item.orderUserName,
              order_status: Number(item.orderStatus) || 0,
              order_money: item.orderMoney || "",
              statusText: item.statusText || "",
              products: [],
            };
          }
          orderMap[orderId].products.push({
            product_id: item.orderProductId,
            product_name: item.orderProductName,
            product_img: item.orderProductImage,
            product_describe: item.orderProductDesc || "",
            product_price: item.orderProductPrice || "",
            count: item.orderProductCount,
          });
        });
        const statusMap = {
          0: "待付款",
          2: "待发货",
          3: "已发货",
          4: "已完成",
          5: "已退款",
          6: "已取消",
        };
        const orderList = Object.values(orderMap).reverse();
        // 计算每个订单的总金额，并映射状态文本
        orderList.forEach((order) => {
          let total = 0;
          order.products.forEach((product) => {
            total += Number(product.product_price) * Number(product.count);
          });
          order.order_money = total.toFixed(2); // 保留两位小数
          order.statusText = statusMap[order.order_status] || "";
        });
        this.setData({ orderList });
      },
    });
  },

  cancelOrder(e) {
    const orderId = e.currentTarget.dataset.orderid;
    wx.request({
      url: "http://localhost:8081/shopping/orders",
      method: "PUT",
      data: {
        orderId: orderId,
        orderStatus: 6,
      },
      success: (res) => {
        wx.showToast({ title: "订单已取消", icon: "success" });
        this.fetchOrders();
      },
      fail: () => {
        wx.showToast({ title: "取消失败", icon: "none" });
      },
    });
  },

  payOrder(e) {
    const orderId = e.currentTarget.dataset.orderid;
    // 找到当前订单
    const order = this.data.orderList.find((item) => item.orderId == orderId);
    if (!order) {
      wx.showToast({ title: "订单不存在", icon: "none" });
      return;
    }

    // 组装商品信息，兼容多商品
    const products = order.products.map((product) => ({
      cartProductId: product.product_id,
      cartProductName: product.product_name,
      cartProductPrice: product.product_price,
      productCount: product.count,
      cartProductUrl: baseUrl + product.product_img,
    }));
    // 用 storage 传递商品信息
    console.log(products)
    wx.setStorageSync("orderPayItems", products);
    wx.navigateTo({
      url: `/pages/order/confirm?fromOrderCenter=true&orderId=${orderId}&items=${JSON.stringify(products)}`
    });
  },

  refundOrder(e) {
    const orderId = e.currentTarget.dataset.orderid;
    wx.request({
      url: "http://localhost:8081/shopping/orders",
      method: "PUT",
      data: {
        orderId: orderId,
        orderStatus: 5,
      },
      success: (res) => {
        console.log(res.data);
        wx.showToast({ title: "已申请退款", icon: "success" });
        this.fetchOrders();
      },
      fail: () => {
        wx.showToast({ title: "申请失败", icon: "none" });
      },
    });
  },

  confirmReceive(e) {
    const orderId = e.currentTarget.dataset.orderid;
    wx.request({
      url: "http://localhost:8081/shopping/orders",
      method: "PUT",
      data: {
        orderId: orderId,
        orderStatus: 4,
      },
      success: (res) => {
        wx.showToast({ title: "收货成功", icon: "success" });
        this.fetchOrders();
      },
      fail: () => {
        wx.showToast({ title: "操作失败", icon: "none" });
      },
    });
  },
});
