import request from './request'

const api = {
  createPayInfo: (orderId) => request.post('wx/pay/createPayInfo', {orderId})
}

export default api
