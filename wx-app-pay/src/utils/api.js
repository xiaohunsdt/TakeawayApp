import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {code}),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),
}

export default api
