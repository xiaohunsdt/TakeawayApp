// pages/index/components/red-packet/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    orderId: Number
  },
  observers: {
    orderId(newVal) {
      if (newVal) {
        this.animateF()
      }
    }
  },
  /**
   * 组件的初始数据
   */
  data: {
    openRedPacket: false
  },

  /**
   * 组件的方法列表
   */
  methods: {
    animateF() {
      if (this.data.openRedPacket) {
        return
      }
      this.animate('#red-packet-image',
        [{
          translateY: -10
        }, {
          translateY: -20
        }, {
          translateY: -10
        }],
        400,
        () => {
          this.animateF()
        })
    },
    openRedPacket() {
      this.setData({
        openRedPacket: true
      })
    },
    closeRedPacket() {
      this.setData({
        orderId: null,
        openRedPacket: false
      })
    }
  }
})