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
      </div>
    </div>
  </div>
</template>

<script>
  import OrderStepper from '@/components/OrderStepper'

  import { mapMutations } from 'vuex'

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
    color:gray !important;
    text-decoration:line-through
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
