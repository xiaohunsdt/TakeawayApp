import userService from '../../services/user'

Component({
  options: {
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },
  properties: {
    tip: String,
    tipIcon: String,
    type: Number,
    price: {
      type: Number,
      observer: 'updatePrice',
    },
    label: String,
    loading: Boolean,
    disabled: Boolean,
    buttonText: String,
    currency: {
      type: String,
      value: '¥'
    },
    buttonType: {
      type: String,
      value: 'danger'
    },
    decimalLength: {
      type: Number,
      value: 2
    },
    suffixLabel: String
  },

  lifetimes: {
    attached: function () {
      if (wx.getStorageSync('userInfo')) {
        this.setData({
          userInfo: wx.getStorageSync('userInfo')
        })
      }
    }
  },
  data: {
    integerStr: null,
    decimalStr: null,
    userInfo: null
  },
  methods: {
    updatePrice: function () {
      var price = this.data.price,
        decimalLength = this.data.decimalLength;
      var priceStrArr =
        typeof price === 'number' &&
        (price / 100).toFixed(decimalLength).split('.');
      this.setData({
        integerStr: priceStrArr && priceStrArr[0],
        decimalStr: decimalLength && priceStrArr ? '.' + priceStrArr[1] : '',
      });
    },
    onSubmit(event) {
      this.triggerEvent('submit', event.detail)
    },
    getWxUserInfo(event) {
      if (event.mp.detail.userInfo) {
        // 将用户信息保存到服务器，保存成功后将被存储到本地
        userService.setUserInfo()
          .then(() => {
            this.setData({
              userInfo: event.detail.userInfo
            })
            this.onSubmit(event)
          })
      } else {
        console.error('授权失败!!!')
      }
    }
  }
})