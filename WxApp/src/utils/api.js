import request from './request'

const api = {
  // 微信登陆
  authLoginByWeixin: (code, userInfo) => request.post('auth/loginByWeixin', {code, userInfo}),
  // 首页数据接口
  getIndexData: () => request.get('app/index', null)
}

export default api
