<template>
  <van-cell>
    <view slot="title">
<!--      <img :src="itemDetail.goodsThumb" mode="aspectFill" alt="" class="goods-thumb">-->
      <div class="goods-name">
        {{itemDetail.goodsName}}
      </div>
      <div class="goods-price">
        ₩ {{itemDetail.goodsPrice}}
      </div>
      <div class="goods-count">
        x {{itemDetail.goodsCount}}
      </div>
    </view>
  </van-cell>
</template>

<script>
  export default {
    name: 'OrderItem',
    props: {
      itemDetail: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style scoped>
  .goods-thumb {
    width: .7rem;
    height: .7rem;
    border-radius: .1rem;
  }

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
