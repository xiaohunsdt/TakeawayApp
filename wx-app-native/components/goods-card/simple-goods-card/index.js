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
        this.init()
      }
    }
  },
  data: {
    currentFoodCount: 0
  },
  pageLifetimes: {
    show() {
      if(this.data.produce.selectedGoods){
        this.init()
      }
    }
  },
  methods: {}
})