// components/goods-card/main-goods-card/index.js
import myBehavior from '../../mixins/goods-cart'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    food: Object,
    cartList: {
      type: Array,
      value: null,
      observer: "init"
    }
  },
  behaviors: [myBehavior],
  /**
   * 组件的初始数据
   */
  data: {
    currentFoodCount: 0,
    showThumbDialog: false
  },
  observers: {
    "food": function (newFood) {
      if (newFood) {
        this.setData({
          isHot: newFood.flags.indexOf('热门') > -1,
          isNew: newFood.flags.indexOf('新品') > -1
        })
      }
    }
  },
  lifetimes: {
    attached: function () {
      this.init()
    },
  },
  pageLifetimes: {
    show() {
      this.init()
    }
  },
  /**
   * 组件的方法列表
   */
  methods: {
    init() {
      let tempVal = this.getCurrentFoodCount()
      this.setData({
        currentFoodCount: tempVal
      })
    },
    onOpenThumbDialog() {
      this.triggerEvent('openThumbDialog', this.data.food)
      // this.setData({
      //   showThumbDialog: true
      // })
    }
  }
})