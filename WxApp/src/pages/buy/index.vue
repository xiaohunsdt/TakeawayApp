<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <div v-if="address">
            <van-cell
              :title="address.address + address.detail"
              is-link
              url="/pages/my/address/main">
              <view slot="icon" style="margin-right: 0.2rem">
                <van-icon color="#FFD200" name="location" size="1.2rem"/>
              </view>
            </van-cell>
            <van-cell
              :title="address.phone"
              is-link
              url="/pages/my/address/main">
              <view slot="icon" style="margin-right: 0.2rem">
                <van-icon color="#FFD200" name="phone" size="1.2rem"/>
              </view>
            </van-cell>
          </div>
          <div v-else>
            <van-cell is-link title="选择地址" url="/pages/my/address/main"/>
          </div>
        </base-panel>
      </div>
      <div id="buy-content">
        <base-panel>
          <order-item
            :itemDetail="item"
            :key="item.goodsName"
            v-for="item in orderItems"/>
          <div id="order-amount">
            共<span style="color: #FFD200">{{ cartCount }}</span>个商品,
            小计 <span style="color: #FFD200">₩ {{ cartAllPrice }}</span>
          </div>
        </base-panel>
        <base-panel>
          优惠卷
        </base-panel>
        <base-panel>
          备注
        </base-panel>
        <!--        <base-panel>-->
        <!--          配送服务-->
        <!--        </base-panel>-->
      </div>
      <div id="footer">
        <van-submit-bar
          :loading="submitLoading"
          :price="cartAllPrice"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submitBtn"
          button-text="立刻支付"
          custom-class="orderSubmitBar"
          price-class="orderPrice">
          <div id="order-bar-left-content">
            <img alt="" src="/static/images/order/cart.png">
            <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;">
              {{ cartCount }}
            </div>
          </div>
          <!--          <view slot="tip">当前下单高峰期, 您可能需要等待较长时间才能就餐!</view>-->
        </van-submit-bar>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex'
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'
  import orderService from '@/services/order'
  import addressService from '@/services/address'

  export default {
    components: {
      BasePanel,
      OrderItem
    },
    computed: {
      orderItems () {
        const cartGoodsList = this.$store.getters.cartGoodsList
        let orderItemList = []
        cartGoodsList.forEach(item => {
          let orderItem = {}
          orderItem.goodsId = item.goodsId
          orderItem.goodsName = item.goods.name
          orderItem.goodsThumb = item.goods.thumb
          orderItem.goodsPrice = item.goods.price
          orderItem.goodsCount = item.count
          orderItemList.push(orderItem)
        })

        return orderItemList
      },
      cartCount () {
        return this.$store.getters.cartAllCount
      },
      cartAllPrice () {
        return this.$store.getters.cartAllPrice
      },
      address () {
        return this.$store.getters.currentAddress
      }
    },
    onLoad () {
      console.log(this.address)
      addressService.getDefaultAddress().then(res => {
        if (res.address) {
          console.log('asdasdsa')
          this.SET_ADDRESS(res)
        }
      })
    },
    data () {
      return {
        submitLoading: false,
        orderId: '',
        order: {}
      }
    },
    methods: {
      ...mapMutations('cart', [
        'CLEAR_CART'
      ]),
      ...mapMutations('address', [
        'SET_ADDRESS'
      ]),
      onSubmitOrder () {
        this.submitLoading = true
        const cartGoodsList = this.$store.getters.cartGoodsList
        orderService.createOrder(cartGoodsList).then(res => {
          this.submitLoading = false
          this.CLEAR_CART()

          // 支付逻辑
        })
      }
    }
  }
</script>

<style scoped>
  #order-amount {
    font-weight: bolder;
    padding-top: .2rem;
    font-size: .25rem;
    text-align: right;
  }

  #order-bar-left-content {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #order-bar-left-content img {
    width: 0.6rem;
    height: 0.5rem;
  }
</style>
