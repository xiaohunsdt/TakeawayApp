<template>
  <div class="simple-goods-card">
    <div class="name">
      {{food.name}}
    </div>
    <div class="price">
      ₩ {{food.price}}
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
  .action .order-stepper-root {
    bottom: unset !important;
    right: unset !important;
  }

</style>
<style scoped>
  .simple-goods-card {
    height: 1rem;
    display: flex;
    justify-content: space-between;
  }
  .name{
    width: 40%;
    overflow: hidden;
  }
  .name, .price, .action {
    font-weight: bolder;
    line-height: .7rem;
    display: table-column;
  }

  .action {
    width: 1.8rem;
    text-align: right;
  }
  .price{
    min-width: 1.5rem;
    margin-right: .2rem;
  }
</style>
