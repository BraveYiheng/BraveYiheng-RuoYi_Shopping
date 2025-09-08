import request from '@/utils/request'

// 查询地址信息列表
export function listAddress(query) {
  return request({
    url: '/shopping/address/list',
    method: 'get',
    params: query
  })
}

// 查询地址信息详细
export function getAddress(addressId) {
  return request({
    url: '/shopping/address/' + addressId,
    method: 'get'
  })
}

// 新增地址信息
export function addAddress(data) {
  return request({
    url: '/shopping/address',
    method: 'post',
    data: data
  })
}

// 修改地址信息
export function updateAddress(data) {
  return request({
    url: '/shopping/address',
    method: 'put',
    data: data
  })
}

// 删除地址信息
export function delAddress(addressId) {
  return request({
    url: '/shopping/address/' + addressId,
    method: 'delete'
  })
}
