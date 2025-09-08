import request from '@/utils/request'

// 查询用户信息列表
export function listUserInfo(query) {
  return request({
    url: '/shopping/userInfo/list',
    method: 'get',
    params: query
  })
}

// 无参请求查询所有商品类别
export function listAllUserInfo() {
  return request({
    url: '/shopping/userInfo/listAll',
    method: 'get'
  })
}

// 查询用户信息详细
export function getUserInfo(userId) {
  return request({
    url: '/shopping/userInfo/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addUserInfo(data) {
  return request({
    url: '/shopping/userInfo',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUserInfo(data) {
  return request({
    url: '/shopping/userInfo',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUserInfo(userId) {
  return request({
    url: '/shopping/userInfo/' + userId,
    method: 'delete'
  })
}
