import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', { code }),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),
  // 获取签到天数
  getSignInDays: () => request.get('user/getSignInDays'),
  getSignInedCount: () => request.get('user/getSignInedCount'),

  // 搜索商品
  searchGoods: (keyword) => request.post('search/searchGoods', { keyword }),

  // 获取首页横幅图片
  getBannersList: () => request.get('index/getBannersList'),
  // 获取指定flag的产品列表
  getSpecificFlagGoodsList: (flag) => request.get('index/getSpecificFlagGoodsList', { flag }),
  // 获取总服务状态
  getServiceState: () => request.get('index/getServiceState'),
  // 获取指定地址是否可以配送
  getExpressServiceState: (addressId, allPrice) => request.get('index/getExpressServiceState', { addressId, allPrice }),
  getAppointmentTimes: () => request.get('index/getAppointmentTimes'),
  // 获取来自者的消息提示
  getFormerNotice: (from) => request.get('index/getFormerNotice', { from }),

  // 获取所有分类
  getAllCategory: () => request.get('category/getAllCategory'),
  // 根据分类获取商品
  getAllGoodsList: () => request.get('goods/getAllGoodsList'),
  getGoodsListByCategoryId: (categoryId) => request.get('goods/getGoodsListByCategoryId', { categoryId }),

  // 获取用户的地址列表
  getAddressById: (addressId) => request.get('address/getAddressById', { addressId }),
  getMyAddressList: () => request.get('address/getMyAddressList'),
  // 获取用户默认的地址
  getDefaultAddress: () => request.get('address/getDefaultAddress'),
  // 搜索一个地址
  searchAddress: (address) => request.post('address/searchAddress', { address }),
  // 新建一个地址
  createNewAddress: (address) => request.post('address/createNewAddress', address),
  // 更新一个地址
  updateAddress: (address) => request.post('address/updateAddress', address),
  deleteAddress: (addressId) => request.post('address/deleteAddress', { addressId }),

  selectOrderById: (orderId) => request.post('order/selectOrderById', { orderId }),
  getOrderListByPage: (arge) => request.post('order/getOrderListByPage', arge),
  getOrderCountByState: (orderState) => request.post('order/getOrderCountByState', { orderState }),
  // 确认收到订单
  confirmGetOrder: (orderId) => request.post('order/confirmGetOrder', { orderId }),
  // 创建一个订单
  createOrder: (order, orderItems, couponId) => request.post('order/createOrder', {
    order,
    orderItems,
    couponId
  }, { headers: { 'Content-Type': 'application/json' } }),
  deleteOrder: (orderId) => request.post('order/deleteOrder', { orderId }),
  createComment: (commentData) => request.post('order/createComment', commentData),
  getDeliveryArriveTime: (orderId) => request.post('order/getDeliveryArriveTime', { orderId }),
  getCanOrderNow: () => request.post('order/getCanOrderNow'),

  // 获取指定域的设置
  getSettingsByScope: (scope) => request.post('setting/getSettingsByScope', { scope }),
  getGoodsPageSettings: () => request.get('setting/getGoodsPageSettings'),

  // 获得所有活动
  getActivityById: (id) => request.get('activity/getActivityById', { id }),
  getAllActivityList: () => request.get('activity/getAllActivityList'),

  getCouponListU: () => request.get('coupon/getCouponListU'),
  getCouponLogByOrderId: (orderId) => request.post('coupon/getCouponLogByOrderId', { orderId }),
  checkCouponDiscountPrice: (order, orderItems, couponId) => request.post('coupon/checkCouponDiscountPrice', {
    order,
    orderItems,
    couponId
  }, { headers: { 'Content-Type': 'application/json' } }),
  exchangeCoupon: (couponId) => request.post('coupon/exchangeCoupon', { couponId })
}

export default api
