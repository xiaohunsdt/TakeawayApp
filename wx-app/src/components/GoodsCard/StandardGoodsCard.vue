<template>
  <div class="goods-card-root">
    <div class="goods-card-thumb">
      <img
        :src="food.thumb?food.thumb:'/static/images/no_image.gif'"
        mode="aspectFill"
        style="height: 100%;width: 100%"/>
    </div>
    <div class="goods-card-content">
      <div class="goods-card-title">
        {{food.name}}
      </div>
      <div class="goods-card-desc">
        <van-icon name="label"/>
        {{ food.desc }}
      </div>
      <!--      <div class="goods-card-origin-price">₩ {{ food.price }}</div>-->
      <span class="goods-card-price">₩ {{ food.price }}</span>
      <div style="height: 0rem">
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
      </div>
    </div>
  </div>
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
    position: absolute !important;
    bottom: .1rem;
    right: 0;
  }

  .order-stepper-root {
    position: absolute !important;
    bottom: .1rem;
    right: 0;
  }
</style>
<style scoped>
  .goods-card-root {
    padding: unset !important;
    overflow: hidden;
    margin-bottom: 0.35rem;
    background-color: transparent !important;
    display: flex;
  }

  .goods-card-thumb {
    width: 1.7rem;
    height: 1.7rem;
    border-radius: 0.2rem;
    overflow: hidden;
  }

  .goods-card-content {
    flex: 1;
    padding-left: .15rem;
    position: relative;
  }

  .goods-card-title {
    font-size: 0.32rem;
    font-weight: 800;
    margin-top: 0.05rem;
    margin-bottom: 0.1rem;
  }

  .goods-card-price, .goods-card-origin-price {
    font-size: .3rem;
    color: #FFD200 !important;
    font-weight: bold;
  }

  .goods-card-origin-price {
    color: gray !important;
    text-decoration: line-through
  }

  .goods-card-desc {
    font-size: .25rem;
    color: gray;
    overflow: hidden;
    text-overflow: ellipsis;
    max-height: .6rem;
    margin-bottom: 0.1rem;
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
