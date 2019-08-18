<template>
  <van-stepper
    min="0"
    :value="initValue"
    custom-class="order-stepper-root"
    input-class="order-stepper-input"
    plus-class="order-stepper-plus"
    minus-class="order-stepper-minus"
    @change="onChange"/>
</template>

<script>
  export default {
    props: {
      foodId: {
        type: Number,
        require: true
      },
      initValue: {
        type: Number,
        default: 0
      }
    },
    data () {
      return {
        currentValue: 0
      }
    },
    methods: {
      onChange (event) {
        console.log(event.mp.detail)
      }
    },
    created () {
      console.log(`order step init value is ${this.initValue}`)
      this.currentValue = this.initValue
    }
  }
</script>

<style>
  .order-stepper-input {
    background-color: transparent !important;
    width: 0.4rem !important;
    font-weight: 600 !important;
  }

  .order-stepper-plus, .order-stepper-minus {
    background-color: #FFD200 !important;
    font-weight: 800 !important;
    border-radius: 50% !important;
    width: 0.5rem !important;
    height: 0.5rem !important;
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
  }

  .order-stepper-plus {
  }

  .order-stepper-minus {
  }
</style>

<style scoped>

</style>
