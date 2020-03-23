<template>
  <div class="simple-goods-card">
    <div class="name">
      {{food.name}}
    </div>
    <div class="price">
      ₩ {{food.price}}
    </div>
    <div class="action">
      <van-stepper
        async-change
        :value="currentFoodCount"
        @change="onChange"
        custom-class="order-stepper-root"
        disable-input="true"
        input-class="order-stepper-input"
        min="0"
        minus-class="order-stepper-minus"
        plus-class="order-stepper-plus"/>
      <!--      <van-button-->
      <!--        :disabled="food.state!=='ON'"-->
      <!--        @click="addCart"-->
      <!--        custom-class="order-btn"-->
      <!--        icon="goods-collect"-->
      <!--        round-->
      <!--        size="small"-->
      <!--        type="primary"-->
      <!--        v-else>-->
      <!--        <span v-if="food.state==='ON'">-->
      <!--          下单-->
      <!--        </span>-->
      <!--        <span v-else-if="food.state==='SHORTAGE'">-->
      <!--          缺货-->
      <!--        </span>-->
      <!--      </van-button>-->
    </div>
  </div>
</template>

<script>
  import { mapMutations } from 'vuex'

  import orderStepper from '../mixins/order-stepper'

  export default {
    name: 'GoodsCard',
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
    // data () {
    //   return {
    //     currentFoodCount: 0
    //   }
    // },
    // mounted () {
    //   this.currentFoodCount = this.$store.getters.cartCountByGoodsId(this.food.id)
    // },
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
  }

  .action .order-stepper-root {
    bottom: unset !important;
    right: unset !important;
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
<style scoped>
  .simple-goods-card {
    height: 1rem;
    display: flex;
    justify-content: space-between;
  }

  .name {
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

  .price {
    min-width: 1.5rem;
    margin-right: .2rem;
  }
</style>
