import request from './request'

const api = {
  // 微信接口
  authLoginByWeixin: (code) => request.post('wx/auth', {
    code
  }),
  setUserInfo: (userInfo) => request.post('wx/setUserInfo', userInfo),
  
  // 获取签到天数
  getSignInDays: (storeId) => request.get('user/getSignInDays', {
    storeId
  }),
  getSignInedCount: (storeId) => request.get('user/getSignInedCount', {
    storeId
  }),

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
  // 获取来自者的消息提示
  getFormerNotice: (from) => request.get('index/getFormerNotice', {
    from
  }),

  //获取指定店铺信息
  getStoreById: (storeId) => request.post('store/getStoreById', {
    storeId
  }),
  // 获取当前坐标范围内的所有店铺
  getAvailableStoreList: (x, y) => request.post('store/getAvailableStoreList', {
    x,
    y
  }),
  // 获取所有店铺列表
  getAllStoreList: () => request.get('store/getAllStoreList'),
  // 获取总服务状态
  getServiceState: (storeId) => request.get('store/getServiceState', {
    storeId
  }),
  // 获取指定地址是否可以配送
  getExpressServiceState: (storeId, addressId, allPrice) => request.get('store/getExpressServiceState', {
    storeId,
    addressId,
    allPrice
  }),
  getDeliveryPrice: (storeId) => request.get('store/getDeliveryPrice', {
    storeId
  }),
  getAppointmentTimes: (storeId) => request.get('store/getAppointmentTimes', {
    storeId
  }),

  // 获取所有分类
  getAllCategory: () => request.get('category/getAllCategory'),
  // 获取产品详细(包含spec，goods列表等)
  getProduceDetailById: (id) => request.get('produce/getDetailById', {
    id
  }),
  // 根据分类获取商品
  getAllProduceList: (storeId) => request.get('produce/getAllList', {
    storeId
  }),
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
  getOrderListByPage: (arge) => request.post('order/getOrderListByPage', arge),
  getOrderCountByState: (orderState) => request.post('order/getOrderCountByState', {
    orderState
  }),
  // 确认收到订单
  confirmGetOrder: (orderId) => request.post('order/confirmGetOrder', {
    orderId
  }),
  // 创建一个订单
  createOrder: (order, orderItems, couponId) => request.post('order/createOrder', {
    order,
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
  getDeliveryArriveTime: (storeId, orderId) => {
    const data = {
      storeId
    }
    if (orderId) {
      data['orderId'] = orderId
    }
    return request.post('order/getDeliveryArriveTime', data)
  },
  getCanOrderNow: (storeId) => request.post('order/getCanOrderNow', {
    storeId
  }),

  // 获取指定域的指定设置
  getSettingByName: (storeId, key, scope) => request.post('setting/getSettingByName', {
    storeId,
    key,
    scope
  }),
  // 获取指定域的设置
  getSettingsByScope: (storeId, scope) => request.post('setting/getSettingsByScope', {
    storeId,
    scope
  }),
  getGoodsPageSettings: (storeId) => request.get('setting/getGoodsPageSettings', {
    storeId
  }),

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