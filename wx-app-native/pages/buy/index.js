import indexService from '../../services/index'
import userService from '../../services/user'
import orderService from '../../services/order'
import payService from '../../services/pay'
import addressService from '../../services/address'
import couponService from '../../services/coupon'
import cartService from '../../services/cart'
import util from '../../utils/util'
const watch = require("../../utils/watch.js")

let times = null

Page({
  watch: {
    address(newVal) {
      if (this.data.order.orderType === 'NORMAL' || this.data.order.orderType === 'APPOINTMENT') {
        this.checkExpressState(newVal.id, this.data.cartAllPrice)
      }
      this.setData({
        'order.addressId': newVal.id
      })
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
      let _realPrice = newVal - this.data.couponDiscountPrice
      if (this.data.order.orderType === 'NORMAL' || this.data.order.orderType === 'APPOINTMENT') {
        _realPrice += this.data.order.deliveryPrice
      }
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    couponDiscountPrice(newVal) {
      let _realPrice = this.data.cartAllPrice - newVal
      if (this.data.order.orderType === 'NORMAL' || this.data.order.orderType === 'APPOINTMENT') {
        _realPrice += this.data.order.deliveryPrice
      }
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    'order.deliveryPrice': function (newVal) {
      let _realPrice = this.data.cartAllPrice - this.data.couponDiscountPrice
      if (this.data.order.orderType === 'NORMAL' || this.data.order.orderType === 'APPOINTMENT') {
        _realPrice += newVal
      }
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    },
    'order.paymentWay': function (newVal) {
      if (newVal && this.data.coupon) {
        this.checkCouponDiscountPrice()
      }
    },
    showTimePicker(newVal) {
      if (!newVal && times) {
        const day = this.data.appointment[0]
        const hour = this.data.appointment[1]
        const minute = this.data.appointment[2]
        console.log(day)

        if ('NORMAL' === this.data.order.orderType || 'APPOINTMENT' === this.data.order.orderType) {
          if (day === '今天' && hour === '尽快配送') {
            this.setData({
              'order.orderType': 'NORMAL',
              'orderDetail.appointmentDate': null
            })
            orderService.getDeliveryArriveTime()
              .then(res => {
                this.setData({
                  deliveryArriveTime: `大约 ${res.date} ${res.time}`
                })
              })
          } else {
            this.setData({
              'order.orderType': 'APPOINTMENT',
              'orderDetail.appointmentDate': indexService.formatAppointmentTime('APPOINTMENT', this.data.appointment),
              deliveryArriveTime: `${day} ${hour}:${minute}`
            })
          }
        }
        if ('SELF' === this.data.order.orderType) {
          if (day === '今天' && hour === '立刻取餐') {
            this.setData({
              'orderDetail.appointmentDate': null,
              deliveryArriveTime: '立刻取餐'
            })
          } else {
            this.setData({
              'orderDetail.appointmentDate': indexService.formatAppointmentTime('APPOINTMENT', this.data.appointment),
              deliveryArriveTime: `${day} ${hour}:${minute}`
            })
          }
        }
      }
    },
    'order.orderType': function (newVal) {
      if (newVal === 'NORMAL' || newVal === 'APPOINTMENT') {
        // if (newVal === 'NORMAL') {
        //   orderService.getDeliveryArriveTime()
        //     .then(res => {
        //       this.setData({
        //         deliveryArriveTime: `大约 ${res.date} ${res.time}`
        //       })
        //     })
        // }
        if (this.data.order.addressId) {
          this.checkExpressState(this.data.order.addressId, this.data.cartAllPrice)
        }
      } else {
        if('SELF' === newVal){
          if (this.data.address) {
            this.setData({
              'orderDetail.phone': this.data.address.phone
            })
          }
        }
        this.setData({
          disableService: false,
          disableNotice: ''
        })
      }

      // 重新计算价格
      let _realPrice = this.data.cartAllPrice - this.data.couponDiscountPrice
      if (this.data.order.orderType === 'NORMAL' || this.data.order.orderType === 'APPOINTMENT') {
        _realPrice += this.data.order.deliveryPrice
      }
      this.setData({
        realPrice: _realPrice > 0 ? _realPrice * 100 : 0
      })
    }
  },
  data: {
    submitLoading: false,
    showOrderTip: false,
    disableService: false,
    disableNotice: '',
    tipNotice: '',
    fromNotice: null,
    signNotice: null,
    order: {
      id: null,
      addressId: null,
      couponId: null,
      paymentWay: 'WEIXIN_PAY',
      orderType: 'NORMAL',
      deliveryPrice: 2000
    },
    orderDetail: {
      from: null,
      ps: null,
      appointmentDate: null,
      phone: null
    },
    orderItems: [],
    cartAllCount: 0,
    cartAllPrice: 0,
    address: null,
    coupon: null,
    couponDiscountPrice: 0,
    couponInfoTip: null,
    couponInfoDetail: null,
    realPrice: 0,
    showTimePicker: false,
    appointment: [],
    appointmentIndex: [],
    appointmentTimes: [],
    deliveryArriveTime: null
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
    if (this.data.order.id !== null) {
      wx.showLoading({
        title: '正在核对信息,请稍等...'
      })
      orderService.selectOrderById(this.data.order.id)
        .then(res => {
          this.setData({
            order: res
          })
          if (this.data.order.payState === 'PAID') {
            wx.reLaunch({
              // url: '/pages/order/index?state=WAIT_EAT'
              url: `/pages/order/success/index?orderId=${this.data.order.id}`
            })
            // wx.reLaunch({
            //   url: `/pages/order/detail/index?orderId=${this.data.order.id}`
            // })
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
    cartService.getCartList()
      .filter(item => item.count > 0)
      .forEach(item => {
        let orderItem = {}
        orderItem.produceId = item.produce.id
        orderItem.goodsId = item.produce.selectedGoods.id
        orderItem.produceName = item.produce.name
        orderItem.goodsTitle = item.produce.selectedGoods.title
        orderItem.goodsThumb = item.produce.selectedGoods.thumb !== '' ? item.produce.selectedGoods.thumb : (item.produce.thumb !== '' ? item.produce.thumb : '')
        orderItem.goodsPrice = item.produce.selectedGoods.price
        orderItem.goodsCount = item.count
        temp.push(orderItem)
        // console.log(orderItem)
      })

    // watch 监听，需要单独拿出来设置数据
    this.setData({
      cartAllPrice: cartService.getCartAllPrice(),
      cartAllCount: cartService.getCartAllCount(),
      orderItems: temp,
      'orderDetail.from': getApp().globalData.from,
    })

    if (this.data.orderDetail.from) {
      indexService.getFormerNotice(this.data.orderDetail.from).then(res => {
        this.setData({
          fromNotice: res.message
        })
      })
    }
    userService.getSignInedCount().then(res => {
      this.setData({
        signNotice: `本月您已经签到${res}次, 达到规定签到数后，系统自动下发优惠券! 请到我的优惠券查看您的获奖优惠券!(订单完成后可查看)`
      })
    })

    indexService.getDeliveryPrice()
      .then(res => {
        this.setData({
          'order.deliveryPrice': res
        })
      })

    // 获取预约时间项
    this.getAppointmentTimes('APPOINTMENT')
  },
  getAppointmentTimes(orderType) {
    indexService.getAppointmentTimes(orderType)
      .then(res => {
        times = res.appointmentTimes
        const canDeliveryNow = res.canDeliveryNow

        // init
        if (canDeliveryNow) {
          let deliveryStr = ''
          if ((orderType === 'NORMAL' || orderType === 'APPOINTMENT')) {
            deliveryStr = '尽快配送'
          }else if(orderType === 'SELF'){
            deliveryStr = '立刻取餐'
          }

          if (!Object.keys(times).includes('今天')) {
            times = Object.assign({}, {
              '今天': {
                [deliveryStr]: []
              }
            }, times)
          } else {
            times['今天'] = Object.assign({}, {
              [deliveryStr]: []
            }, times['今天'])
          }
        }

        const days = Object.keys(times)
        const day = days.contains('今天')? '今天' : days[0]
        const hours = Object.keys(times[day])
        let hour = hours[0]
        let minutes = times[day][hour]

        if(hours.contains('尽快配送') || hours.contains('立刻取餐')){
          hour = hours[hours.length - 1]
          hours.splice(hours.length - 1, 1)
          hours.splice(0, 0, hour)
          minutes = []
        }

        if ((orderType === 'NORMAL' || orderType === 'APPOINTMENT')) {
          if (day === '今天' && hour === '尽快配送') {
            this.setData({
              'order.orderType': 'NORMAL',
              'orderDetail.appointmentDate': null,
              deliveryArriveTime: null
            })
            orderService.getDeliveryArriveTime()
              .then(res => {
                this.setData({
                  deliveryArriveTime: `大约 ${res.date} ${res.time}`
                })
              })
          } else {
            this.setData({
              'order.orderType': 'APPOINTMENT',
              'orderDetail.appointmentDate': indexService.formatAppointmentTime('APPOINTMENT', [days[0], hours[0], minutes[0]]),
              deliveryArriveTime: `${days[0]} ${hours[0]}:${minutes[0]}`
            })
          }
        }

        if (orderType === 'SELF') {
          if (day === '今天' && hour === '立刻取餐') {
            this.setData({
              'orderDetail.appointmentDate': null,
              deliveryArriveTime: '立刻取餐'
            })
          } else {
            this.setData({
              'orderDetail.appointmentDate': indexService.formatAppointmentTime('APPOINTMENT', [days[0], hours[0], minutes[0]]),
              deliveryArriveTime: `${days[0]} ${hours[0]}:${minutes[0]}`
            })
          }
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
            'orderDetail.ps': data.ps
          })
        }
      }
    })
  },
  paymentWayChange(event) {
    let paymentWay = event.currentTarget.dataset.paymentWay
    this.setData({
      'order.paymentWay': paymentWay
    })
  },
  orderTypeChange(event) {
    let orderType = event.currentTarget.dataset.orderType

    this.setData({
      'order.orderType': orderType
    })

    if (orderType === 'NORMAL' || orderType === 'APPOINTMENT' || orderType === 'SELF') {
      //获取预约时间
      this.getAppointmentTimes(orderType)
    }
  },
  selfPhoneInput(event) {
    this.setData({
      'orderDetail.phone': event.detail.value
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
          console.log(res)
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
      this.data.order,
      this.data.orderDetail,
      this.data.orderItems,
      this.data.coupon
    ).then(res => {
      cartService.clearCart()
      getApp().globalData.currentCoupon = null
      getApp().globalData.from = null
      this.setData({
        orderItems: [],
        cartAllCount: 0,
        cartAllPrice: 0,
        coupon: null,
        'order.id': res.message,
        submitLoading: false
      })
      payService.payOrder(this.data.order.id, this.data.order.paymentWay)
    }).catch(res => {
      this.setData({
        submitLoading: false
      })
    })
  },
  checkExpressState(addressId, allPrice) {
    indexService.getExpressServiceState(addressId, allPrice)
      .then(res => {
        this.setData({
          disableService: res.state !== 0,
          disableNotice: res.message
        })
      })
  },
  checkCouponDiscountPrice() {
    this.setData({
      couponDiscountPrice: 0,
      couponInfoTip: null,
      couponInfoDetail: couponService.getCouponDetail(this.data.coupon)
    })

    couponService.checkCouponDiscountPrice(
      this.data.order,
      this.data.orderItems,
      this.data.coupon.id
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
    if (hours.includes('立刻取餐')) {
      hours.splice(hours.length - 1, 1)
      hours.splice(0, 0, '立刻取餐')
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