// components/goods-card/main-goods-card/index.js
import myBehavior from '../../mixins/goods-cart'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    food: Object
  },
  behaviors: [myBehavior],
  /**
   * 组件的初始数据
   */
  data: {
    currentFoodCount: 0
  },
  lifetimes:{
    attached: function() {
      this.init()
    },
  },
  pageLifetimes:{
    show(){
      this.init()
    }
  },
  /**
   * 组件的方法列表
   */
  methods: {
    init(){
      let tempVal = this.getCurrentFoodCount()
      this.setData({
        currentFoodCount: tempVal
      })
    }
  }
})