// components/goods-card/main-goods-card/index.js
import myBehavior from '../../mixins/order-step'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    food: Object
  },
  behaviors:[myBehavior],
  /**
   * 组件的初始数据
   */
  data: {
    currentFoodCount: 0
  },
  /**
   * 组件的方法列表
   */
  methods: { }
})
