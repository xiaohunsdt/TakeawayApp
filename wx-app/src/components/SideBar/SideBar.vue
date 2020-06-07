<template>
  <div class="container-side-bar">
    <div class="nav" :class="{'has-padding':cartAllCount > 0}">
      <scroll-view class="nav-scroll" scroll-y="true" style="height: 100%;">
        <div
          :class="{'active':currentId === category.id}"
          :key="category.id"
          @click="chooseType(category.id)"
          class="nav-item"
          v-for="category in categoryGoods">
          {{category.name}}
        </div>
      </scroll-view>
    </div>
    <div class="content">
      <scroll-view
        :scroll-into-view="contentId"
        :scroll-y="contentId"
        class="content-scroll"
        :class="{'has-padding':cartAllCount > 0}"
        scroll-with-animation="true"
        style="height: 100%;">
        <div class="content-container">
          <div
            :id="'xh_'+category.id"
            :key="category.id"
            class="pesticide"
            v-for="category in categoryGoods"
            v-show="category.goodsList.length > 0 && 'xh_'+category.id === contentId">
            <div class="type-name">
              <div class="line"></div>
              <div class="name">{{category.name}}</div>
              <div class="line"></div>
            </div>
            <div class="pesticide-container">
              <standard-goods-card
                :food="food"
                :key="foodIndex"
                v-for="(food,foodIndex) in category.goodsList"/>
            </div>
          </div>
        </div>
        <view class="has-no-more">
          <view class="line"></view>
          <view class="name">没有更多</view>
          <view class="line"></view>
        </view>
      </scroll-view>
    </div>
    <van-action-sheet
      :show="showCart"
      :z-index="99999"
      @cancel="showCart =false"
      @click-overlay="showCart =false"
      @close="showCart =false"
      close-on-click-overlay
      overlay
      title="购物车">
      <view class="cart-content">
        <simple-goods-card
          :food="item"
          :key="item.id"
          v-for="item in orderItems"/>
      </view>
    </van-action-sheet>
    <div id="footer" v-if="cartAllCount > 0">
      <goods-submit-bar
        :decimal-length="0"
        :disabled="pageSettings.disableService"
        :price="cartAllPrice * 100"
        @submit="onSubmitOrder"
        button-text="提交订单"
        currency="₩">
        <div @click="onOpenCart" id="order-bar-left-content">
          <img alt="" src="/static/images/order/cart.png">
          <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;position:relative;top: -0.15rem;">
            {{ cartAllCount }}
          </div>
        </div>
        <view slot="tip" v-if="pageSettings.disableService">{{pageSettings.disableServiceNotice}}</view>
<!--        <view slot="tip">asdasdasasdsa</view>-->
      </goods-submit-bar>
    </div>
  </div>
</template>

<script>
  import StandardGoodsCard from '@/components/GoodsCard/StandardGoodsCard'
  import SimpleGoodsCard from '@/components/GoodsCard/SimpleGoodsCard'
  import GoodsSubmitBar from '@/components/GoodsSubmitBar'

  import orderService from '@/services/order'

  export default {
    name: 'SideBar',
    props: {
      pageSettings: {
        type: Object,
        required: true
      },
      categoryGoods: {
        type: Object,
        required: true
      }
    },
    components: {
      StandardGoodsCard,
      SimpleGoodsCard,
      GoodsSubmitBar
    },
    watch: {
      categoryGoods () {
        if (this.categoryGoods.length > 0) {
          this.currentId = this.categoryGoods[0].id
          this.contentId = `xh_${this.currentId}`
        }
      }
    },
    computed: {
      cartAllCount () {
        return this.$store.getters.cartAllCount
      },
      cartAllPrice () {
        return this.$store.getters.cartAllPrice
      },
      orderItems () {
        if (!this.showCart) {
          return []
        }
        const cartGoodsList = this.$store.getters.cartGoodsList
        let orderItems = []
        cartGoodsList
          .filter(item => item.count > 0)
          .forEach(item => {
            orderItems.push(item.goods)
          })
        return orderItems
      }
    },
    data () {
      return {
        currentId: '',
        contentId: '',
        heightArr: [],
        containerH: 0,
        showCart: false
      }
    },
    mounted () {
      if (this.categoryGoods.length > 0) {
        this.currentId = this.categoryGoods[0].id
        this.contentId = `xh_${this.currentId}`
      }
    },
    methods: {
      chooseType (event) {
        this.currentId = event
        this.contentId = `xh_${this.currentId}`
      },
      // onScroll (event) {
      //   let scrollTop = event.mp.detail.scrollTop
      //   let scrollArr = this.heightArr
      //   if (!scrollTop >= scrollArr[scrollArr.length - 1] - this.containerH) {
      //     for (let i = 0; i < scrollArr.length; i++) {
      //       if (scrollTop >= 0 && scrollTop < scrollArr[0]) {
      //         this.currentId = this.categoryGoods[0].id
      //       } else if (scrollTop >= scrollArr[i - 1] && scrollTop < scrollArr[i]) {
      //         this.currentId = this.categoryGoods[i].id
      //       }
      //     }
      //   }
      // },
      // getDivInfo () {
      //   this.heightArr.splice(0, this.heightArr.length)
      //
      //   let query = mpvue.createSelectorQuery()
      //   query.select('.content-container').boundingClientRect((res) => {
      //     if (res) {
      //       this.containerH = res.height
      //     }
      //   }).exec()
      //   let s = 0
      //   query.selectAll('.pesticide').boundingClientRect((react) => {
      //     react.forEach((res) => {
      //       s += res.height
      //       if (this.heightArr.findIndex(item => s === item) < 0) {
      //         this.heightArr.push(s)
      //       }
      //     })
      //   }).exec()
      // },
      onSubmitOrder () {
        orderService.getCanOrderNow()
          .then(res => {
            if (!res) {
              mpvue.showModal({
                title: '提示',
                content: `当前时间无法下单!\r\n请问是否要进行预约?`,
                success (res) {
                  if (res.confirm) {
                    mpvue.navigateTo({
                      url: `/pages/buy/main`
                    })
                  }
                }
              })
            } else {
              mpvue.navigateTo({
                url: `/pages/buy/main`
              })
            }
          })
      },
      onOpenCart () {
        this.showCart = true
      }
    }
  }
</script>

<style scoped>
  .container-side-bar {
    width: 100%;
    height: 100%;
    display: flex;
    overflow: scroll;
    color: #3a3a3a;
  }

  .container-side-bar .nav {
    width: 1.6rem;
    background: #F2F2F2;
  }

  .container-side-bar .nav .nav-scroll {
    overflow: scroll;
  }

  .container-side-bar .nav .nav-item {
    text-align: center;
    width: 100%;
    display: block;
    color: #313131;
    padding: .32rem 0;
    font-size: .32rem;
  }

  .container-side-bar .nav .active {
    background: #ffffff;
    width: calc(100% - .06rem);
    border-right: solid .06rem #FFD200;
    font-weight: bold;
  }

  .container-side-bar .content {
    flex: 1;
    background: #fff;
    height: 100%;
  }

  .container-side-bar .content .content-container {
    display: unset;
  }

  .container-side-bar .content .content-scroll {
    overflow: hidden;
  }

  .container-side-bar .pesticide .type-name {
    display: flex;
    justify-content: center;
    height: .72rem;
    align-items: center;
  }

  .container-side-bar .pesticide .type-name .line {
    width: .4rem;
    height: 0.02rem;
    border-bottom: solid .01rem #ddd;
  }

  .container-side-bar .pesticide .type-name .name {
    margin: 0 .16rem;
  }

  .pesticide .pesticide-container {
    padding: 0 .24rem;
  }

  .content .has-no-more {
    display: flex;
    justify-content: center;
    padding: .3rem 0;
    color: #ccc;
    align-items: center;
  }

  .content .has-no-more .line {
    width: .4rem;
    height: .02rem;
    border-bottom: solid .01rem #ddd;
  }

  .content .has-no-more .name {
    margin: 0 .16rem;
  }

  /*.has-submit-bar {*/
  /*  padding-bottom: .7rem;*/
  /*}*/

  #order-bar-left-content {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #order-bar-left-content img {
    width: 0.6rem;
    height: 0.5rem;
  }

  .cart-content {
    padding: .2rem;
  }

  .has-padding{
    box-sizing:border-box;
    padding-bottom: 1rem;
  }
</style>
