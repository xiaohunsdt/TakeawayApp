import cartService from '../../services/cart'

export default Behavior({
  methods: {
    openSkuDialog(){
      this.selectComponent('#sku-dialog').openDialog(this.data.food)
    },
    getCurrentFoodCount() {
      let existData;
      if (this.data.cartList) {
        existData = this.data.cartList.find(item => item.produce.goods.id === this.data.food.goods.id)
      } else {
        existData = cartService.find(this.data.food.goods)
      }
      return existData !== undefined ? existData.count : 0
    },
    addCart() {
      let cartData;
      if (this.data.food.goodsCount === 1) {
        cartData = {
          produce: Object.assign({}, this.data.food),
          count: 1
        }
        cartService.getCartList().push(cartData)
        cartService.getCart().cartAllCount++
        cartService.getCart().cartAllPrice += cartData.produce.goods.price

        this.setData({
          currentFoodCount: this.data.currentFoodCount + 1
        })
        this.triggerEvent('cart-change', this.data.currentFoodCount)
      }else {
        this.openSkuDialog()
      }
    },
    addGoods() {
      let cartData = cartService.find(this.data.food.goods)
      if (cartData !== undefined) {
        cartData.count++
      } else {
        cartData = {
          produce: Object.assign({}, this.data.food),
          count: 1
        }
        cartService.getCartList().push(cartData)
      }
      cartService.getCart().cartAllCount++
      cartService.getCart().cartAllPrice += cartData.produce.goods.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    reduceGoods() {
      const index = cartService.findIndex(this.data.food.goods)
      if (index < 0) {
        return
      }
      const cartData = cartService.getCartList()[index]
      if (cartData !== undefined && cartData.count > 0) {
        cartData.count--
        if (cartData.count === 0) {
          cartService.getCartList().splice(index, 1)
        }

        cartService.getCart().cartAllCount--
        cartService.getCart().cartAllPrice -= cartData.produce.goods.price
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