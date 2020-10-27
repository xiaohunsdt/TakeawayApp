// components/goods-card/main-goods-card/index.js
import myBehavior from '../../mixins/goods-cart'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    produce: Object,
    cartList: {
      type: Array,
      value: null,
      observer: "init"
    }
  },
  behaviors: [myBehavior],
  observers: {
    "produce": function (newValue) {
      if (newValue) {
        if (newValue.goodsCount === 1 && !newValue.selectedGoods) {
          newValue.selectedGoods = newValue.goods
          this.setData({
            produce: newValue
          })
          return
        }
        this.setData({
          isHot: newValue.flags.indexOf('热门') > -1,
          isNew: newValue.flags.indexOf('新品') > -1
        })
        this.init()
      }
    }
  },
  data: {
    currentFoodCount: 0,
    showThumbDialog: false
  },
  pageLifetimes: {
    show() {
      if (this.data.produce.selectedGoods) {
        this.init()
      }
    }
  },
  methods: {
    onOpenThumbDialog() {
      this.triggerEvent('openThumbDialog', this.data.produce)
    }
  }
})