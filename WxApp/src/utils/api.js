import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {code}),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),
  // 首页数据接口
  getIndexData: () => request.get('app/index', null)
}

export default api
