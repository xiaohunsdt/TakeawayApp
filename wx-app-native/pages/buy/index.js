import indexService from '../../services/index'
import userService from '../../services/user'
import orderService from '../../services/order'
import payService from '../../services/pay'
import addressService from '../../services/address'
import couponService from '../../services/coupon'
import util from '../../utils/util'
const watch = require("../../utils/watch.js")

let times = null

Page({
  watch: {
    address(newVal) {
      this.checkExpressState(newVal.id, this.data.cartAllPrice)
    },
    coupon(newVal) {
      if (newVal) {
        this.checkCouponDiscountPrice()
      } else {
        this.setData({
          couponDiscountPrice: 0,
          couponInfoTip: null,
          couponInfoDetail: null
        })
      }
    },
    cartAllPrice(newVal) {
      const _realPrice = this.data.cartAllPrice - this.data.couponDiscountPrice + this.data.deliveryPrice
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    couponDiscountPrice(newVal) {
      const _realPrice = this.data.cartAllPrice - this.data.couponDiscountPrice + this.data.deliveryPrice
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    deliveryPrice(newVal){
      const _realPrice = this.data.cartAllPrice - this.data.couponDiscountPrice + this.data.deliveryPrice
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    payWay(newVal) {
      if (newVal && this.data.coupon) {
        this.checkCouponDiscountPrice()
      }
    },
    showTimePicker(newVal) {
      if (!newVal && times) {
        const day = this.data.appointment[0]
        const hour = this.data.appointment[1]
        const minute = this.data.appointment[2]
        if (day === '今天' && hour === '尽快配送') {
          this.setData({
            deliveryType: '尽快配送'
          })
        } else {
          this.setData({
            deliveryType: '预约点餐',
            deliveryArriveTime: `${day} ${hour}:${minute}`
          })
        }
      }
    },
    deliveryType(newVal) {
      if (newVal === '尽快配送') {
        orderService.getDeliveryArriveTime()
          .then(res => {
            this.setData({
              deliveryArriveTime: `大约 ${res.date} ${res.time}`
            })
          })
      }
    }
  },
  data: {
    submitLoading: false,
    showOrderTip: false,
    disableService: false,
    tipNotice: '',
    fromNotice: null,
    signNotice: null,
    orderId: '',
    order: {},
    address: null,
    orderItems: [],
    cartAllCount: 0,
    cartAllPrice: 0,
    coupon: null,
    couponDiscountPrice: 0,
    couponInfoTip: null,
    couponInfoDetail: null,
    realPrice: 0,
    payWay: 'WEIXIN_PAY',
    psData: '',
    from: null,
    showTimePicker: false,
    appointment: [],
    appointmentIndex: [],
    appointmentTimes: [],
    deliveryType: '',
    deliveryArriveTime: null,
    deliveryPrice: 2000
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    watch.setWatcher(this)
    this.init()
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (this.data.orderId !== '') {
      wx.showLoading({
        title: '正在核对信息,请稍等...'
      })
      orderService.selectOrderById(this.data.orderId)
        .then(res => {
          this.setData({
            order: res
          })
          if (this.data.order.payState === 'PAID') {
            // mpvue.redirectTo({
            //   url: '/pages/order/main?state=waitEat'
            // })
            wx.reLaunch({
              url: `/pages/order/detail/index?orderId=${this.data.order.id}`
            })
          } else {
            wx.reLaunch({
              url: '/pages/order/index?state=WAIT_PAY'
            })
          }
        })
        .finally(() => {
          wx.hideLoading()
        })
    } else {
      if (!getApp().globalData.currentAddress) {
        addressService.getDefaultAddress().then(res => {
          if (res.address) {
            getApp().globalData.currentAddress = res
            this.setData({
              address: getApp().globalData.currentAddress
            })
          }
        })
      } else if (getApp().globalData.currentAddress !== this.data.address) {
        this.setData({
          address: getApp().globalData.currentAddress
        })
      }

      if (getApp().globalData.currentCoupon !== this.data.coupon) {
        this.setData({
          coupon: getApp().globalData.currentCoupon
        })
      }
      if (util.checkVersion('7.0.7') === -1) {
        this.setData({
          tipNotice: '您的微信版本过低,建议您升级最新版本获取全部新特性服务'
        })
      }
    }
  },
  init() {
    // 获取订单相关设置
    let temp = []
    getApp().globalData.cart.cartList
      .filter(item => item.count > 0)
      .forEach(item => {
        let orderItem = {}
        orderItem.goodsId = item.goodsId
        orderItem.goodsName = item.goods.name
        orderItem.goodsThumb = item.goods.thumb
        orderItem.goodsPrice = item.goods.price
        orderItem.goodsCount = item.count
        temp.push(orderItem)
      })

    // watch 监听，需要单独拿出来设置数据
    this.setData({
      cartAllPrice: getApp().globalData.cart.cartAllPrice,
      cartAllCount: getApp().globalData.cart.cartAllCount,
      orderItems: temp,
      from: getApp().globalData.from,
    })

    if (this.data.from) {
      indexService.getFormerNotice(this.data.from).then(res => {
        this.setData({
          fromNotice: res.message
        })
      })
    }
    userService.getSignInedCount().then(res => {
      if (res === 7) {
        this.setData({
          signNotice: `本周您已获得7000现金优惠卷(无门槛), 下周继续努力哦!`
        })
      } else {
        this.setData({
          signNotice: `本周您已经签到${res}次, 再签到${7 - res}次即可获得7000现金优惠卷(无门槛)`
        })
      }
    })

    indexService.getDeliveryPrice()
    .then(res => {
      this.setData({
        deliveryPrice: res
      })
    })

    // 获取预约时间项
    indexService.getAppointmentTimes()
      .then(res => {
        times = res.appointmentTimes
        const canDeliveryNow = res.canDeliveryNow
        if (canDeliveryNow && !Object.keys(times).includes('今天')) {
          times = Object.assign({}, {
            '今天': {
              '尽快配送': []
            }
          }, times)
        }

        const days = Object.keys(times)
        const day = days[0]
        const hours = Object.keys(times[day])
        const hour = hours[0]
        let minutes = times[day][hour]

        if (canDeliveryNow) {
          times['今天'] = Object.assign({}, {
            '尽快配送': []
          }, times['今天'])
          if (!hours.includes('尽快配送')) {
            hours.splice(0, 0, '尽快配送')
          }
          minutes = []
        }

        if (days[0] === '今天' && hours[0] === '尽快配送') {
          this.setData({
            deliveryType: '尽快配送'
          })
        } else {
          this.setData({
            deliveryType: '预约点餐',
            deliveryArriveTime: `${days[0]} ${hours[0]}:${minutes[0]}`
          })
        }

        this.setData({
          appointmentTimes: [days, hours, minutes],
          appointmentIndex: [0, 0, 0],
          appointment: [days[0], hours[0], minutes[0]]
        })
      })
  },
  setCoupon() {
    wx.navigateTo({
      url: '/pages/coupon/index'
    })
  },
  deleteCoupon() {
    getApp().globalData.currentCoupon = null
    this.setData({
      coupon: null
    })
  },
  setPs() {
    const $this = this
    wx.navigateTo({
      url: '/pages/buy/remark/index',
      events: {
        setPsContent(data) {
          $this.setData({
            psData: data.ps
          })
        }
      }
    })
  },
  payWayChange(event) {
    let payWay = event.currentTarget.dataset.payWay
    this.setData({
      payWay
    })
  },
  onSubmitOrder() {
    if (this.data.orderItems.length === 0) {
      wx.showToast({
        title: '请选择商品!!',
        image: '/static/images/error.png'
      })
      return 
    }

    this.setData({
      submitLoading: true
    })

    const $this = this
    const tmplIds = [
      'chtooPomhx0JrFECp0ZzYLlRZHc6tA7UdN-l5lAV0A4',
      '4tz6mHc6JK5tsCq6lT2IGh2Leo46QyeDWjLml__PNI0',
      '-fscbMujX6HY1jkr-Sy1bjqv1p6FDq9vkhGw-4hL9Xk'
    ]
    if (util.checkVersion('7.0.7') >= 0) {
      wx.requestSubscribeMessage({
        tmplIds,
        success(res) {
          console.log(res)
          let isReject = false
          for (let i = 0; i < tmplIds.length; i++) {
            if (res[tmplIds[i]] === 'reject') {
              isReject = true
              break
            }
          }

          if (isReject) {
            wx.showModal({
              title: '提示',
              content: `拒绝消息会导致您无法收到订单相关的消息通知\r\n请问是否继续下单?`,
              success(res) {
                if (res.cancel) {
                  wx.showModal({
                    title: '提示',
                    content: '请点击页面右上角"┅"按钮, 点击设置, 打开订阅消息权限',
                    showCancel: false
                  })

                  $this.setData({
                    submitLoading: false
                  })
                }

                if (res.confirm) {
                  $this.createOrder()
                }
              }
            })
          } else {
            $this.createOrder()
          }
        },
        fail(res) {
          $this.setData({
            submitLoading: false
          })

          if (res.errCode === 20004) {
            wx.showModal({
              title: '警告',
              content: '订阅消息权限被关闭! 请点击页面右上角"┅"按钮, 点击设置, 打开订阅消息权限',
              showCancel: false
            })
          }
        }
      })
    } else {
      wx.requestSubscribeMessage({
        tmplIds: [tmplIds[2]],
        success(res) {
          console.log(res)
          let isReject = false
          if (res[tmplIds[2]] === 'reject') {
            isReject = true
          }

          if (isReject) {
            wx.showModal({
              title: '提示',
              content: `拒绝消息会导致您无法收到订单相关的消息通知\r\n请问是否继续下单?`,
              success(res) {
                if (res.cancel) {
                  wx.showModal({
                    title: '提示',
                    content: '请点击页面右上角"┅"按钮, 点击设置, 打开订阅消息权限',
                    showCancel: false
                  })

                  $this.setData({
                    submitLoading: false
                  })
                }
                if (res.confirm) {
                  $this.createOrder()
                }
              }
            })
          } else {
            $this.createOrder()
          }
        },
        fail(res) {
          $this.setData({
            submitLoading: false
          })

          if (res.errCode === 20004) {
            wx.showModal({
              title: '警告',
              content: '订阅消息权限被关闭! 请点击页面右上角"┅"按钮, 点击设置, 打开订阅消息权限',
              showCancel: false
            })
          }
        }
      })
    }
  },
  createOrder() {
    orderService.createOrder(
      orderService.generateOrder(this.data.payWay, this.data.psData, indexService.formatAppointmentTime(this.data.deliveryType, this.data.appointment), this.data.from),
      this.data.orderItems,
      this.data.coupon,
      this.data.address
    ).then(res => {
      getApp().globalData.cart = {
        cartList: [],
        cartAllCount: 0,
        cartAllPrice: 0
      }
      getApp().globalData.currentCoupon = null
      getApp().globalData.from = null
      this.setData({
        orderItems: [],
        cartAllCount: 0,
        cartAllPrice: 0,
        coupon: null,
        from: null,
        orderId: res.message,
        submitLoading: false
      })
      payService.payOrder(this.data.orderId, this.data.payWay)
    }).catch(res => {
      this.setData({
        submitLoading: false
      })
    })
  },
  checkExpressState(addressId, allPrice) {
    this.setData({
      disableService: false
    })
    indexService.getExpressServiceState(addressId, allPrice)
      .then(res => {
        if (res.state !== 0) {
          this.setData({
            disableService: true,
            tipNotice: res.message
          })
        }
      })
  },
  checkCouponDiscountPrice() {
    this.setData({
      couponDiscountPrice: 0,
      couponInfoTip: null,
      couponInfoDetail: couponService.getCouponDetail(this.data.coupon)
    })

    couponService.checkCouponDiscountPrice(
      orderService.generateOrder(this.data.payWay, null, null, this.data.from),
      this.data.orderItems,
      this.data.coupon
    ).then(res => {
      this.setData({
        couponDiscountPrice: res.message
      })
    }).catch(res => {
      if (res) {
        this.setData({
          couponInfoTip: res.message
        })
      }
    })
  },
  onTimePickerChange(event) {
    const {
      value
    } = event.detail
    console.log(value)

    const days = Object.keys(times)
    const day = days[value[0]]
    const hours = Object.keys(times[day])
    if (hours.includes('尽快配送')) {
      hours.splice(hours.length - 1, 1)
      hours.splice(0, 0, '尽快配送')
    }
    const hour = hours[value[1]]
    const minutes = times[day][hour]

    // const minute = times[day][hour][value[2]]
    if (this.data.appointment[0] !== days[value[0]]) {
      this.setData({
        'appointmentTimes[1]': hours,
        'appointmentTimes[2]': minutes
      })
    }

    if (this.data.appointment[1] !== hours[value[1]]) {
      this.setData({
        'appointmentTimes[2]': minutes
      })
    }
    this.setData({
      'appointmentIndex': value
    })

    if (this.data.showTimePicker) {
      if (value[0] < days.length && value[1] < hours.length && value[2] < minutes.length) {
        this.setData({
          appointment: [days[value[0]], hours[value[1]], minutes[value[2]]]
        })
      } else {
        let minute = null
        if (minutes && minutes.length > 0) {
          minute = minutes[0]
        }
        this.setData({
          appointment: [days[0], hours[0], minute]
        })
      }
    }
  },
  onShowTimePicker() {
    this.setData({
      showTimePicker: true
    })
  },
  onCloseTimePicker() {
    this.setData({
      showTimePicker: false
    })
  }
})