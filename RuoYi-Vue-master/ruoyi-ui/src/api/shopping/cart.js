import request from '@/utils/request'

// 查询购物车信息管理列表
export function listCart(query) {
  return request({
    url: '/shopping/cart/list',
    method: 'get',
    params: query
  })
}

// 查询购物车信息管理详细
export function getCart(cartId) {
  return request({
    url: '/shopping/cart/' + cartId,
    method: 'get'
  })
}

// 新增购物车信息管理
export function addCart(data) {
  return request({
    url: '/shopping/cart',
    method: 'post',
    data: data
  })
}

// 修改购物车信息管理
export function updateCart(data) {
  return request({
    url: '/shopping/cart',
    method: 'put',
    data: data
  })
}

// 删除购物车信息管理
export function delCart(cartId) {
  return request({
    url: '/shopping/cart/' + cartId,
    method: 'delete'
  })
}
