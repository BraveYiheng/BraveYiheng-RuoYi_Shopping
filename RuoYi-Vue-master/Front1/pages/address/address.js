import { baseUrl } from "../../utils/config"

Page({
  data: {
    addressList: [],
    showDialog: false,
    isEdit: false,
    form: {
      addressId: null,
      name: '',
      phone: '',
      address: ''
    }
  },
  onLoad() {
    this.getUserAddresses();
  },
  onShow() {
    this.getUserAddresses();
  },
  getUserAddresses() {
    const userId = wx.getStorageSync('userId');
    wx.request({
      url: `${baseUrl}/shopping/address/list`,
      method: 'GET',
      data: { addressUserId: userId },
      success: (res) => {
        if (res.data.code === 200 && res.data.rows) {
          console.log('获取到的地址列表：', res.data.rows);
          this.setData({ addressList: res.data.rows });
        }
      }
    });
  },
  showAddDialog() {
    this.setData({
      showDialog: true,
      isEdit: false,
      form: {
        addressId: null,
        name: '',
        phone: '',
        address: ''
      }
    });
  },
  showEditDialog(e) {
    const { id, name, phone, address } = e.currentTarget.dataset;
    this.setData({
      showDialog: true,
      isEdit: true,
      form: {
        addressId: id,
        name:name,
        phone:phone,
        address:address
      }
    });
  },
  hideDialog() {
    this.setData({ showDialog: false });
  },
  inputName(e) {
    this.setData({ 'form.name': e.detail.value });
  },
  inputPhone(e) {
    this.setData({ 'form.phone': e.detail.value });
  },
  inputAddress(e) {
    this.setData({ 'form.address': e.detail.value });
  },
  submitForm() {
    const { form, isEdit } = this.data;
    if (!form.name || !form.phone || !form.address) {
      wx.showToast({ title: '请填写完整信息', icon: 'none' });
      return;
    }
    const userId = wx.getStorageSync('userId');
    console.log(form.name);
    const data = {
      addressUserId: userId,
      addressUserNickName: form.name,
      phone: form.phone,
      address: form.address,
    };

    if (isEdit) {
      data.addressId = form.addressId;
    }
    wx.request({
      url: `${baseUrl}/shopping/address`,
      method: isEdit ? 'PUT' : 'POST',
      data,
      success: (res) => {
        if (res.data.code === 200) {
          wx.showToast({ 
            title: isEdit ? '修改成功' : '添加成功', 
            icon: 'success' 
          });
          this.hideDialog();
          this.getUserAddresses();
        }
      }
    });
  },
  setDefaultAddress(e) {
    const addressId = e.currentTarget.dataset.id;
    const userId = wx.getStorageSync('userId') ;
    
    // 先找到当前默认地址
    const currentDefault = this.data.addressList.find(addr => addr.addressIsfo == 0);
    
    // 如果当前要设置的地址已经是默认地址，则不做任何操作
    if (currentDefault && currentDefault.addressId === addressId) {
      return;
    }

    // 如果存在默认地址，先将其设置为非默认
    if (currentDefault) {
      wx.request({
        url: `${baseUrl}/shopping/address`,
        method: 'PUT',
        data: {
          addressId: currentDefault.addressId,
          addressUserId: userId,
          addressIsfo: 1
        },
        success: (res) => {
          if (res.data.code === 200) {
            // 设置新的默认地址
            this.setNewDefaultAddress(addressId, userId);
          }
        }
      });
    } else {
      // 如果没有默认地址，直接设置新的默认地址
      this.setNewDefaultAddress(addressId, userId);
    }
  },
  setNewDefaultAddress(addressId, userId) {
    wx.request({
      url: `${baseUrl}/shopping/address`,
      method: 'PUT',
      data: {
        addressId,
        addressUserId: userId,
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
  deleteAddress(e) {
    const addressId = e.currentTarget.dataset.id;
    wx.showModal({
      title: '提示',
      content: '确定要删除该地址吗？',
      success: (res) => {
        if (res.confirm) {
          wx.request({
            url: `${baseUrl}/shopping/address/${addressId}`,
            method: 'DELETE',
            success: (res) => {
              if (res.data.code === 200) {
                wx.showToast({ title: '删除成功', icon: 'success' });
                this.getUserAddresses();
              }
            }
          });
        }
      }
    });
  }
})