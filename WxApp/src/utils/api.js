import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {code}),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),

  // 获取指定flag的产品列表
  getSpecificFlagGoodsList: (flag) => request.get('index/getSpecificFlagGoodsList', {flag}),
  // 获取所有分类
  getAllCategory: () => request.get('category/getAllCategory'),
  // 根据分类获取商品
  getGoodsListByCategoryId: (categoryId) => request.get('goods/getGoodsListByCategoryId', {categoryId}),

  // 获取用户的地址列表
  getAddressById: (addressId) => request.get('address/getAddressById', {addressId}),
  getMyAddressList: () => request.get('address/getMyAddressList'),
  // 获取用户默认的地址
  getDefaultAddress: () => request.get('address/getDefaultAddress'),
  // 新建一个地址
  createNewAddress: (address) => request.post('address/createNewAddress', address),
  // 更新一个地址
  updateAddress: (address) => request.post('address/updateAddress', address),
  deteleAddress: (addressId) => request.post('address/deteleAddress', {addressId}),

  // 创建一个订单
  createOrder: (order, orderItems) => request.post('order/createOrder', {order, orderItems}, {
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export default api
