<template>
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
</template>

<script>
  import {mapMutations} from 'vuex'

  export default {
    props: {
      food: {
        type: Object,
        required: true
      }
    },
    computed: {
      currentFoodCount () {
        return this.$store.getters.cartCountByGoodsId(this.food.id)
      }
    },
    methods: {
      ...mapMutations('cart', [
        'ADD_GOODS',
        'REDUCE_GOODS'
      ]),
      onChange (event) {
        const currentVal = event.mp.detail
        if (currentVal > this.currentFoodCount) {
          this.ADD_GOODS(this.food)
        } else {
          this.REDUCE_GOODS(this.food)
        }
      }
    }
  }
</script>

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

</style>
