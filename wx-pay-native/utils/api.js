import request from './request'

const api = {
  authLoginByWeixin: (code) => request.post('wx/auth', {code}),
  createPayInfo: (orderId) => request.post('wx/pay/createPayInfo', {orderId}),
  confirmOrder: (orderId) => request.post('wx/pay/confirmOrder', {orderId})
}

export default api