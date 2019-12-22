<template>
  <van-card
    :price="food.price"
    :title="food.name"
    currency="₩"
    custom-class="goods-card-root"
    lazy-load
    price-class="goods-card-price"
    thumb-mode="aspectFill"
    title-class="goods-card-title">
    <view slot="thumb" class="goods-card-thumb">
      <img
        style="height: 100%;width: 100%"
        mode="aspectFill"
        :src="food.thumb?food.thumb:'/static/images/no_image.gif'"/>
    </view>
    <view class="goods-card-desc" slot="desc">
      <div class="desc">
        <van-icon name="label"/>
        {{ food.desc }}
      </div>
      <div class="comment">
        <div>
          月销 {{ food.monthSale }}
        </div>
        <div class="dividLine"></div>
        <div>评分&nbsp;</div>
        <div style="color: #FFD200">{{ food.rate }}</div>
      </div>
    </view>
    <view slot="footer" style="height: 0.4rem">
      <order-stepper :food="food" v-if="currentFoodCount > 0"/>
      <van-button
        @click="addCart"
        custom-class="order-btn"
        icon="goods-collect"
        round
        size="small"
        type="primary"
        v-else>
        下单
      </van-button>
    </view>
  </van-card>
</template>

<script>
  import OrderStepper from '@/components/OrderStepper'

  import {mapMutations} from 'vuex'

  export default {
    name: 'MainGoodsCard',
    props: {
      food: {
        type: Object,
        required: true
      }
    },
    components: {
      OrderStepper
    },
    computed: {
      currentFoodCount () {
        return this.$store.getters.cartCountByGoodsId(this.food.id)
      }
    },
    methods: {
      ...mapMutations('cart', [
        'ADD_GOODS'
      ]),
      addCart () {
        console.log(`id is ${this.food.id}`)
        this.ADD_GOODS(this.food)
      }
    }
  }
</script>
<style>
  .goods-card-root {
    padding: unset !important;
    overflow: hidden;
  }

  .goods-card-root .van-card__thumb {
    width: 2.2rem;
    height: 1.7rem;
  }

  .goods-card-root {
    margin-bottom: 0.35rem;
    background-color: transparent !important;
  }

  .goods-card-thumb {
    border-radius: 0.2rem;
    overflow: hidden;
    height: inherit;
  }

  .goods-card-title {
    margin-top: .1rem;
    font-size: 0.35rem;
    font-weight: 800;
    margin-bottom: 0.2rem;
  }

  .van-card__bottom {
    position: absolute;
    top: .1rem;
    right: .1rem;
  }

  .goods-card-price {
    font-size: .3rem;
    color: #FFD200 !important;
  }

  .goods-card-desc .desc {
    color: gray;
  }

  .goods-card-desc .comment {
    color: gray;
    margin-top: 0.2rem;
    display: flex;
    flex-direction: row;
  }

  .goods-card-desc .comment .dividLine {
    border-right: #F3F3F3 0.01rem solid;
    width: 0.01rem;
    margin: 0 0.1rem;
  }

  .order-btn {
    background-color: #FFD200 !important;
    border: none !important;
    display: block;
    position: relative;
    top: -.7rem;
    right: 0;
  }

  .order-stepper-root {
    position: relative;
    top: -.7rem;
    right: 0;
  }
</style>
<style scoped>

</style>
