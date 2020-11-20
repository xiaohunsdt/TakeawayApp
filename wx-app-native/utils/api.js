import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {
    code
  }),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),
  // 获取签到天数
  getSignInDays: () => request.get('user/getSignInDays'),
  getSignInedCount: () => request.get('user/getSignInedCount'),

  // 搜索商品
  searchGoods: (keyword) => request.post('search/searchGoods', {
    keyword
  }),

  // 获取首页横幅图片
  getBannersList: () => request.get('index/getBannersList'),
  // 获取指定flag的产品列表
  getSpecificFlagProduceList: (flag) => request.get('index/getSpecificFlagProduceList', {
    flag
  }),
  // 获取总服务状态
  getServiceState: () => request.get('index/getServiceState'),
  // 获取指定地址是否可以配送
  getExpressServiceState: (addressId, allPrice) => request.get('index/getExpressServiceState', {
    addressId,
    allPrice
  }),
  getDeliveryPrice: () => request.get('index/getDeliveryPrice'),
  getAppointmentTimes: (orderType) => request.post('index/getAppointmentTimes', {
    orderType
  }),
  // 获取来自者的消息提示
  getFormerNotice: (from) => request.get('index/getFormerNotice', {
    from
  }),

  // 获取所有分类
  getAllCategory: () => request.get('category/getAllCategory'),
  // 获取产品详细(包含spec，goods列表等)
  getProduceDetailById: (id) => request.get('produce/getDetailById', {
    id
  }),
  // 根据分类获取商品
  getAllProduceList: () => request.get('produce/getAllList'),
  getProduceListByCategoryId: (categoryId) => request.get('produce/getListByCategoryId', {
    categoryId
  }),

  // 获取用户的地址列表
  getAddressById: (addressId) => request.get('address/getAddressById', {
    addressId
  }),
  getMyAddressList: () => request.get('address/getMyAddressList'),
  // 获取用户默认的地址
  getDefaultAddress: () => request.get('address/getDefaultAddress'),
  // 搜索一个地址
  searchAddress: (address) => request.post('address/searchAddress', {
    address
  }),
  // 获取一个地址所在位置的静态地图
  getAddressStaticMap: (address) => request.get('address/getAddressStaticMap', address),
  // 新建一个地址
  createNewAddress: (address) => request.post('address/createNewAddress', address),
  // 更新一个地址
  updateAddress: (address) => request.post('address/updateAddress', address),
  // 设置默认地址
  setDefaultAddress: (addressId) => request.post('address/setDefaultAddress', {
    id: addressId
  }),
  deleteAddress: (addressId) => request.post('address/deleteAddress', {
    addressId
  }),
  
  selectOrderById: (orderId) => request.post('order/selectOrderById', {
    orderId
  }),
  selectOrderDetailById: (orderId) => request.post('order/selectOrderDetailById', {
    orderId
  }),
  getOrderListByPage: (arge) => request.post('order/getOrderListByPage', arge),
  getOrderCountByState: (orderState) => request.post('order/getOrderCountByState', {
    orderState
  }),
  // 确认收到订单
  confirmGetOrder: (orderId) => request.post('order/confirmGetOrder', {
    orderId
  }),
  // 创建一个订单
  createOrder: (order, orderDetail, orderItems, couponId) => request.post('order/createOrder', {
    order,
    orderDetail,
    orderItems,
    couponId
  }, {
    headers: {
      'Content-Type': 'application/json'
    }
  }),
  deleteOrder: (orderId) => request.post('order/deleteOrder', {
    orderId
  }),
  createComment: (commentData) => request.post('order/createComment', commentData),
  getDeliveryArriveTime: (orderId) => {
    let data = {}
    if (orderId) {
      data['orderId'] = orderId
    }
    return request.post('order/getDeliveryArriveTime', data)
  },
  getCanOrderNow: () => request.post('order/getCanOrderNow'),

  // 获取指定域的指定设置
  getSettingByName: (key, scope) => request.post('setting/getSettingByName', {
    key,
    scope
  }),
  // 获取指定域的设置
  getSettingsByScope: (scope) => request.post('setting/getSettingsByScope', {
    scope
  }),
  getGoodsPageSettings: () => request.get('setting/getGoodsPageSettings'),

  // 获得所有活动
  getActivityById: (id) => request.get('activity/getActivityById', {
    id
  }),
  getAllActivityList: () => request.get('activity/getAllActivityList'),

  getCouponListU: () => request.get('coupon/getCouponListU'),
  getCouponLogByOrderId: (orderId) => request.post('coupon/getCouponLogByOrderId', {
    orderId
  }),
  checkCouponDiscountPrice: (order, orderItems, couponId) => request.post('coupon/checkCouponDiscountPrice', {
    order,
    orderItems,
    couponId
  }, {
    headers: {
      'Content-Type': 'application/json'
    }
  }),
  exchangeCoupon: (couponId) => request.post('coupon/exchangeCoupon', {
    couponId
  })
}

export default api