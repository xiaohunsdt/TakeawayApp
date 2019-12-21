<template>
  <div class="simple-goods-card">
    <div class="name">
      {{food.name}}
    </div>
    <div class="action">
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
    <div class="price">
      ₩ {{food.price}}
    </div>
  </div>
</template>

<script>
  import OrderStepper from '@/components/OrderStepper'

  import { mapMutations } from 'vuex'

  export default {
    name: 'GoodsCard',
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
        // console.log(`id is ${this.food.id}`)
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
  }
</style>
<style scoped>
  .simple-goods-card {
    height: 1rem;
  }

  .name, .price, .action {
    font-weight: bolder;
    display: inline-block;
    line-height: .7rem;
  }

  .name {
    float: left;
    /*padding-left: 0.2rem;*/
  }

  .action {
    width: 1.7rem;
    text-align: right;
  }
  .price{
    min-width: 1.5rem;
    margin-right: .2rem;
  }
  .price, .action {
    float: right;
  }
</style>
