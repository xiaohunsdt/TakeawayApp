<template>
  <van-card
    :thumb="food.thumb"
    :title="food.name"
    custom-class="food-card-root"
    thumb-class="food-card-thumb"
    thumb-mode="aspectFill"
    title-class="food-card-title">
    <view class="food-card-desc" slot="desc">
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
    <view slot="footer" style="height: 40rpx">
      <van-button
        @click="addCart(food.id)"
        custom-class="orderBtn"
        icon="goods-collect"
        round
        size="small"
        type="primary">
        下单
      </van-button>
      <!--      <order-stepper />-->
    </view>
  </van-card>
</template>

<script>
  import OrderStepper from '@/components/OrderStepper'

  export default {
    name: 'GoodsCard',
    props: {
      food: {
        type: Object,
        require: true
      }
    },
    components: {
      OrderStepper
    },
    methods: {
      addCart (id) {
        console.log(`id is ${id}`)
      }
    }
  }
</script>
<style>
  .food-card-root {
    padding: unset !important;
    overflow: hidden;
  }

  .food-card-root .van-card__thumb {
    width: 2.2rem;
    height: 1.7rem;
  }

  .food-card-root {
    margin-bottom: 0.35rem;
    background-color: transparent !important;
  }

  .food-card-thumb {
    border-radius: 0.2rem;
    overflow: hidden;
  }

  .food-card-title {
    font-size: 0.35rem;
    font-weight: 800;
    margin-bottom: 0.2rem;
  }

  .food-card-desc .desc {
    color: gray;
  }

  .food-card-desc .comment {
    color: gray;
    margin-top: 0.2rem;
    display: flex;
    flex-direction: row;
  }

  .food-card-desc .comment .dividLine {
    border-right: #F3F3F3 0.01rem solid;
    width: 0.01rem;
    margin: 0 0.1rem;
  }

  .orderBtn {
    background-color: #FFD200 !important;
    border: none !important;
    display: block;
    position: relative;
    top: -0.5rem;
    right: 0rem;
  }

  .order-stepper-root {
    position: relative;
    top: -0.4rem;
    right: 0rem;
  }
</style>
<style scoped>

</style>
