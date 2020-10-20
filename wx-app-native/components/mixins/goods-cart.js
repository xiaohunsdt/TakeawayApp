import cartService from '../../services/cart'

export default Behavior({
  methods: {
    openSkuDialog() {
      const node = this.selectComponent('#sku-dialog')
      if (node) {
        node.openDialog(this.data.produce)
      } else {
        this.triggerEvent('openSkuDialog', this.data.produce)
      }
    },
    getCurrentFoodCount() {
      let existData;
      if (this.data.cartList) {
        existData = this.data.cartList.find(item => this.data.produce.selectedGoods && item.produce.selectedGoods.id === this.data.produce.selectedGoods.id)
      } else {
        existData = cartService.find(this.data.produce.selectedGoods)
      }
      return existData !== undefined ? existData.count : 0
    },
    addCart() {
      let cartData;
      if (this.data.produce.goodsCount === 1) {
        if (!this.data.produce.selectedGoods) {
          wx.showModal({
            title: '警告',
            content: '请先选择您的商品',
            showCancel: false
          })
          return
        }
      } else if(this.data.produce.goodsCount > 1){
        if (!this.data.isShow) {
          this.openSkuDialog()
          return
        }
      }
      cartData = {
        produce: Object.assign({}, this.data.produce),
        count: 1
      }
      cartService.getCartList().push(cartData)
      cartService.getCart().cartAllCount++
      cartService.getCart().cartAllPrice += cartData.produce.selectedGoods.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    addGoods() {
      let cartData = cartService.find(this.data.produce.selectedGoods)
      if (cartData !== undefined) {
        cartData.count++
      } else {
        cartData = {
          produce: Object.assign({}, this.data.produce),
          count: 1
        }
        cartService.getCartList().push(cartData)
      }
      cartService.getCart().cartAllCount++
      cartService.getCart().cartAllPrice += cartData.produce.selectedGoods.price

      this.setData({
        currentFoodCount: this.data.currentFoodCount + 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    reduceGoods() {
      const index = cartService.findIndex(this.data.produce.selectedGoods)
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
        cartService.getCart().cartAllPrice -= cartData.produce.selectedGoods.price
      }

      this.setData({
        currentFoodCount: this.data.currentFoodCount - 1
      })
      this.triggerEvent('cart-change', this.data.currentFoodCount)
    },
    onChange(event) {
      const currentVal = event.detail
      // console.log(this.data.produce.name, '---', currentVal, '---', this.data.currentFoodCount)
      if (currentVal > this.data.currentFoodCount) {
        this.addGoods()
      } else if (currentVal < this.data.currentFoodCount) {
        this.reduceGoods()
      }
    },
    onCartChange(event) {
      this.triggerEvent('cart-change', event.detail)
    }
  }
})