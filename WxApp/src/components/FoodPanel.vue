<template>
  <div class="foodView">
    <van-panel>
      <view slot="header" style="border: none!important;">
        <van-button size="small">{{ title }}</van-button>
      </view>
      <view>
        <van-panel
          custom-class="food-item-panel"
          header-class="food-item-header"
          footer-class="food-item-footer">
          <view>
            <van-button
              icon="goods-collect"
              size="small"
              type="primary"
              custom-class="orderBtn"
              round>下单
            </van-button>
            <view style="clear:both;height:0"></view>
          </view>
        </van-panel>
      </view>
    </van-panel>
  </div>
</template>

<script>
  export default {
    name: 'FoodPanel',
    props: {
      title: {
        type: String,
        require: true
      },
      foodList: {
        type: Array,
        require: true
      }
    }
  }
</script>

<style>
  .orderBtn {
    background-color: #FFD200 !important;
    border: none !important;
    float: right;
    display: block;
  }

  .food-item-panel:after {
    border: none !important;
  }
</style>

<style scoped>
  .foodView {
    margin-top: 0.2rem;
    width: 100%;
    background-color: white;
  }
</style>
