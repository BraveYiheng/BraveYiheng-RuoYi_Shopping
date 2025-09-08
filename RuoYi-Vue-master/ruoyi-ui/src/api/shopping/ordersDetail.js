import request from '@/utils/request'

// 查询订单详细信息列表
export function listOrdersDetail(query) {
  return request({
    url: '/shopping/ordersDetail/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细信息详细
export function getOrdersDetail(orderDetailId) {
  return request({
    url: '/shopping/ordersDetail/' + orderDetailId,
    method: 'get'
  })
}

// 新增订单详细信息
export function addOrdersDetail(data) {
  return request({
    url: '/shopping/ordersDetail',
    method: 'post',
    data: data
  })
}

// 修改订单详细信息
export function updateOrdersDetail(data) {
  return request({
    url: '/shopping/ordersDetail',
    method: 'put',
    data: data
  })
}

// 删除订单详细信息
export function delOrdersDetail(orderDetailId) {
  return request({
    url: '/shopping/ordersDetail/' + orderDetailId,
    method: 'delete'
  })
}
