<template>
  <van-card
    :price="food.price"
    :title="food.name"
    currency="₩"
    custom-class="goods-card-root"
    price-class="goods-card-price"
    thumb-mode="aspectFill"
    title-class="goods-card-title">
    <view class="goods-card-thumb" slot="thumb">
      <image
        :src="food.thumb?food.thumb:'/static/images/no_image.gif'"
        lazy-load
        mode="aspectFill"
        style="height: 100%;width: 100%"/>
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
      <van-stepper
        async-change
        :value="currentFoodCount"
        @change="onChange"
        custom-class="order-stepper-root"
        disable-input="true"
        input-class="order-stepper-input"
        min="0"
        minus-class="order-stepper-minus"
        plus-class="order-stepper-plus"
        v-if="currentFoodCount>0"/>
      <van-button
        :disabled="food.state!=='ON'"
        @click="addCart"
        custom-class="order-btn"
        icon="goods-collect"
        round
        size="small"
        type="primary"
        v-else>
        <span v-if="food.state==='ON'">
          下单
        </span>
        <span v-else-if="food.state==='SHORTAGE'">
          缺货
        </span>
      </van-button>
    </view>
  </van-card>
</template>

<script>
  import { mapMutations } from 'vuex'

  import orderStepper from '../mixins/order-stepper'

  export default {
    name: 'MainGoodsCard',
    props: {
      food: {
        type: Object,
        required: true
      }
    },
    mixins: [orderStepper],
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
        // console.log(`id is ${this.food.id}`)
        if (this.food.state !== 'ON') {
          return
        }
        this.ADD_GOODS(this.food)
      }
    }
  }
</script>
<style>
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
</style>
<style>
  .order-stepper-input {
    background-color: transparent !important;
    width: 0.4rem !important;
    font-weight: 600 !important;
  }

  .order-stepper-input.van-stepper__input--disabled {
    color: black;
  }

  .order-stepper-plus, .order-stepper-minus {
    background-color: #FFD200 !important;
    font-weight: 800 !important;
    border-radius: 50% !important;
    width: 0.5rem !important;
    height: 0.5rem !important;
  }

  .order-stepper-plus:active, .order-stepper-minus:active {
    background-color: #ffb105 !important;
  }

  .order-stepper-plus:before,
  .order-stepper-plus:after,
  .order-stepper-minus:before, .order-stepper-minus:after {
    background-color: white !important;
  }

  .order-stepper-minus:before, .order-stepper-plus:before {
    height: 0.05rem !important;
    width: 0.24rem !important;
  }

  .order-stepper-minus:after, .order-stepper-plus:after {
    width: 0.05rem !important;
    height: 0.24rem !important;
  }
</style>
