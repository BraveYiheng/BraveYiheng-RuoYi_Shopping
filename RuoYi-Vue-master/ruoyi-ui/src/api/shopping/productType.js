import request from '@/utils/request'

// 查询商品类别列表
export function listType(query) {
  return request({
    url: '/product/type/list',
    method: 'get',
    params: query
  })
}

// 无参请求查询所有商品类别
export function listAllTypes() {
  return request({
    url: '/product/type/listAll',
    method: 'get'
  })
}

// 查询商品类别详细
export function getType(typeId) {
  return request({
    url: '/product/type/' + typeId,
    method: 'get'
  })
}

// 新增商品类别
export function addType(data) {
  return request({
    url: '/product/type',
    method: 'post',
    data: data
  })
}

// 修改商品类别
export function updateType(data) {
  return request({
    url: '/product/type',
    method: 'put',
    data: data
  })
}

// 删除商品类别
export function delType(typeId) {
  return request({
    url: '/product/type/' + typeId,
    method: 'delete'
  })
}
