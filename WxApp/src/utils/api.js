import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {code}),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),

  // 获取指定flag的产品列表
  getSpecificFlagGoodsList: (flag) => request.get('getSpecificFlagGoodsList', {flag})
}

export default api
