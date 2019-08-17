import request from './request'

const api = {
  // 首页数据接口
  getIndexData: (r) => request.get('app/index', null)
}

export default api
