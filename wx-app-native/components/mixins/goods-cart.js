import cartService from '../../services/cart'

export default Behavior({
  methods: {
    getCurrentFoodCount() {
      let existData;
      if (this.data.cartList) {
        existData = this.data.cartList.find(item => item.goodsId === this.data.food.id)
      } else {
        existData = cartService.find(this.data.food)
      }
      return existData !== undefined ? existData.count : 0
    },
    addCart() {
      const cartData = {
        goodsId: this.data.food.id,
        goods: this.data.food,
        count: 1
      }
      cartService.getCartList().push(cartData)
      cartService.getCart().cartAllCount++
      cartService.getCart().cartAllPrice += this.data.food.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })

      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    addGoods() {
      const existData = cartService.find(this.data.food)
      if (existData !== undefined) {
        existData.count++
      } else {
        const cartData = {
          goodsId: this.data.food.id,
          goods: this.data.food,
          count: 1
        }
        cartService.getCartList().push(cartData)
      }
      cartService.getCart().cartAllCount++
      cartService.getCart().cartAllPrice += this.data.food.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    reduceGoods() {
      const index = cartService.findIndex(this.data.food)
      if (index < 0) {
        return
      }
      const existData = cartService.getCartList()[index]
      if (existData !== undefined && existData.count > 0) {
        existData.count--
        if (existData.count === 0) {
          cartService.getCartList().splice(index, 1)
        }

        cartService.getCart().cartAllCount--
        cartService.getCart().cartAllPrice -= this.data.food.price
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