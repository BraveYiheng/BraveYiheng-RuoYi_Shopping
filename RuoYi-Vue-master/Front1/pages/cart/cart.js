import { baseUrl } from "../../utils/config"

Page({
  data: {
    cartItems: [],
    allSelected: false,
    totalPrice: 0
  },

  onLoad() {
    this.getCartData();
  },

  onShow() {
    this.getCartData();
  },

  // 获取购物车数据
  getCartData() {
    const userId = wx.getStorageSync('userId');
    wx.request({
      url: `${baseUrl}/shopping/cart/list`,
      method: "GET",
      data: {
        cartUserId: userId
      },
      success: (res) => {
        console.log(res.data)
        console.log("购物车---------------------")
        if (res.data.code === 200) {
          const processedItems = res.data.rows.map(item => ({
            ...item,
            selected: true,
            totalPrice: (item.cartProductPrice * item.productCount).toFixed(2),
            // 修改图片URL，确保使用完整的baseUrl
            cartProductUrl: baseUrl + item.cartProductUrl
          }));
          
          // 检查是否所有商品都被选中
          const allSelected = processedItems.length > 0 && processedItems.every(item => item.selected);
          
          this.setData({ 
            cartItems: processedItems,
            allSelected,
            totalPrice: this.calculateTotal(processedItems)
          });
        }
      }
    });
  },

  // 计算总价
  calculateTotal(items) {
    return items
      .filter(item => item.selected)
      .reduce((total, item) => total + (item.cartProductPrice * item.productCount), 0)
      .toFixed(2);
  },

  // 切换单个商品选择状态
  toggleSelect(e) {
    const index = e.currentTarget.dataset.index;
    const cartItems = this.data.cartItems;
    cartItems[index].selected = !cartItems[index].selected;
    
    const allSelected = cartItems.every(item => item.selected);
    this.setData({
      cartItems,
      allSelected,
      totalPrice: this.calculateTotal(cartItems)
    });
  },

  // 切换全选状态
  toggleAllSelect() {
    const allSelected = !this.data.allSelected;
    const cartItems = this.data.cartItems.map(item => ({
      ...item,
      selected: allSelected
    }));
    
    this.setData({
      cartItems,
      allSelected,
      totalPrice: this.calculateTotal(cartItems)
    });
  },

  // 减少商品数量
  decreaseCount(e) {
    const index = e.currentTarget.dataset.index;
    const cartItems = this.data.cartItems;
    if (cartItems[index].productCount > 1) {
      cartItems[index].productCount--;
      cartItems[index].totalPrice = (cartItems[index].cartProductPrice * cartItems[index].productCount).toFixed(2);
      this.setData({ 
        cartItems,
        totalPrice: this.calculateTotal(cartItems)
      });
      this.updateCartItem(cartItems[index].cartId, cartItems[index].productCount);
    }
  },

  // 增加商品数量
  increaseCount(e) {
    const index = e.currentTarget.dataset.index;
    const cartItems = this.data.cartItems;
    cartItems[index].productCount++;
    cartItems[index].totalPrice = (cartItems[index].cartProductPrice * cartItems[index].productCount).toFixed(2);
    this.setData({ 
      cartItems,
      totalPrice: this.calculateTotal(cartItems)
    });
    this.updateCartItem(cartItems[index].cartId, cartItems[index].productCount);
  },

  // 更新购物车商品数量
  updateCartItem(cartId, count) {
    wx.request({
      url: `${baseUrl}/shopping/cart`,
      method: 'PUT',
      data: { 
        cartId: cartId,
        productCount: count 
      },
      success: (res) => {
        if (res.data.code !== 200) {
          wx.showToast({
            title: '更新失败',
            icon: 'none'
          });
        }
      }
    });
  },

  // 跳转到结算页面
  goToCheckout() {
    const selectedItems = this.data.cartItems.filter(item => item.selected);
    if (selectedItems.length === 0) {
      wx.showToast({
        title: '请选择商品',
        icon: 'none'
      });
      return;
    }

    // 将选中的商品信息传递给结算页面
    wx.navigateTo({
      url: `/pages/order/confirm?items=${JSON.stringify(selectedItems)}&fromCart=true`,
      events: {
        // 监听结算页面返回的删除购物车商品事件
        deleteCartItems: (data) => {
          // 从本地数据中移除已结算的商品
          const remainingItems = this.data.cartItems.filter(item => 
            !data.cartIds.includes(item.cartId)
          );
          this.setData({
            cartItems: remainingItems,
            totalPrice: this.calculateTotal(remainingItems)
          });
        }
      }
    });
  }
});