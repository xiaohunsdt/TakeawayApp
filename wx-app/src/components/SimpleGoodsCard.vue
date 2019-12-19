<template>
  <div>
    <div class="goods-name">
      {{food.name}}
    </div>
    <div class="goods-price">
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
  .goods-name, .goods-count, .goods-price {
    font-weight: bolder;
    padding-left: 0.2rem;
    display: inline-block;
    line-height: .7rem;
  }

  .goods-thumb, .goods-name {
    float: left;
  }

  .goods-count, .goods-price {
    float: right;
  }
  .goods-price{
    margin-left: .3rem;
  }
</style>
