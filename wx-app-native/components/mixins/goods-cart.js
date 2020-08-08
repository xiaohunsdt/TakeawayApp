export default Behavior({
  methods: {
    getCurrentFoodCount() {
      let existData;
      if (this.data.cartList) {
        existData = this.data.cartList.find(item => item.goodsId === this.data.food.id)
      } else {
        existData = getApp().globalData.cart.cartList.find(item => item.goodsId === this.data.food.id)
      }
      return existData !== undefined ? existData.count : 0
    },
    addCart() {
      const cartData = {
        goodsId: this.data.food.id,
        goods: this.data.food,
        count: 1
      }
      getApp().globalData.cart.cartList.push(cartData)
      getApp().globalData.cart.cartAllCount++
      getApp().globalData.cart.cartAllPrice += this.data.food.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })

      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    addGoods() {
      const existData = getApp().globalData.cart.cartList.find(item => item.goodsId === this.data.food.id)
      if (existData !== undefined) {
        existData.count++
      } else {
        const cartData = {
          goodsId: this.data.food.id,
          goods: this.data.food,
          count: 1
        }
        getApp().globalData.cart.cartList.push(cartData)
      }
      getApp().globalData.cart.cartAllCount++
      getApp().globalData.cart.cartAllPrice += this.data.food.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    reduceGoods() {
      const index = getApp().globalData.cart.cartList.findIndex(item => item.goodsId === this.data.food.id)
      if (index < 0) {
        return
      }
      const existData = getApp().globalData.cart.cartList[index]
      if (existData !== undefined && existData.count > 0) {
        existData.count--
        if (existData.count === 0) {
          getApp().globalData.cart.cartList.splice(index, 1)
        }

        getApp().globalData.cart.cartAllCount--
        getApp().globalData.cart.cartAllPrice -= this.data.food.price
      }

      this.setData({
        currentFoodCount: this.data.currentFoodCount - 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    onChange(event) {
      const currentVal = event.detail
      // console.log(this.data.food.name, '---', currentVal, '---', this.data.currentFoodCount)
      if (currentVal > this.data.currentFoodCount) {
        this.addGoods()
      } else if (currentVal < this.data.currentFoodCount) {
        this.reduceGoods()
      }
    }
  }
})