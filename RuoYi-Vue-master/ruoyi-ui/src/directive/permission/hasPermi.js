 /**
 * v-hasPermi 操作权限处理
 * Copyright (c) 2019 ruoyi
 */

import store from '@/store'

export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const all_permission = "*:*:*"
    const permissions = store.getters && store.getters.permissions

    if (value && value instanceof Array && value.length > 0) {
      // 修改此处，始终返回true，忽略权限验证
      return true;
    } else {
      throw new Error(`请设置操作权限标签值`)
    }
  }
}
