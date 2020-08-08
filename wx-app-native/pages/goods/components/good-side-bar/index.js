import orderService from '../../../../services/order'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    pageSettings: Object,
    categoryGoods: Object,
  },
  observers: {
    "categoryGoods": function (categoryGoods) {
      if (categoryGoods.length > 0) {
        this.setData({
          currentId: categoryGoods[0].id,
          contentId: `xh_${categoryGoods[0].id}`
        })
      }
    },
    "showCart": function (showCart) {
      if (showCart) {
        this.setData({
          cartList: getApp().globalData.cart.cartList
        })
      } else {
        this.initCartData()
      }
    }
  },
  data: {
    currentId: '',
    contentId: '',
    containerH: 0,
    showCart: false,
    cartList: [],
    cartAllCount: 0,
    cartAllPrice: 0
  },
  pageLifetimes: {
    show: function () {
      this.initCartData()
    }
  },
  methods: {
    initCartData() {
      this.setData({
        cartAllCount: getApp().globalData.cart.cartAllCount,
        cartAllPrice: getApp().globalData.cart.cartAllPrice
      })
    },
    chooseType(event) {
      this.setData({
        currentId: event.currentTarget.dataset.categoryId,
        contentId: `xh_${event.currentTarget.dataset.categoryId}`
      })
    },
    onCartChange() {
      this.initCartData()
    },
    onSubmitOrder() {
      orderService.getCanOrderNow()
        .then(res => {
          if (!res) {
            wx.showModal({
              title: '提示',
              content: `当前时间无法下单!\r\n请问是否要进行预约?`,
              success(res) {
                if (res.confirm) {
                  wx.navigateTo({
                    url: `/pages/buy/main`
                  })
                }
              }
            })
          } else {
            wx.navigateTo({
              url: `/pages/buy/main`
            })
          }
        })
    },
    onOpenCart() {
      this.setData({
        showCart: true
      })
    },
    onCloseCart() {
      this.setData({
        showCart: false
      })
    }
  }
})