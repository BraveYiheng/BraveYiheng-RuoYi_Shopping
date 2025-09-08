import request from '@/utils/request'

// 查询商品列表列表
export function listList(query) {
  return request({
    url: '/product/list/list',
    method: 'get',
    params: query
  })
}

// 无参请求查询所有商品名称
export function listAllProductNames() {
  return request({
    url: '/product/list/listAll',
    method: 'get'
  })
}

// 查询商品列表详细
export function getList(productId) {
  return request({
    url: '/product/list/' + productId,
    method: 'get'
  })
}

// 新增商品列表
export function addList(data) {
  return request({
    url: '/product/list',
    method: 'post',
    data: data
  })
}

// 修改商品列表
export function updateList(data) {
  return request({
    url: '/product/list',
    method: 'put',
    data: data
  })
}

// 删除商品列表
export function delList(productId) {
  return request({
    url: '/product/list/' + productId,
    method: 'delete'
  })
}
