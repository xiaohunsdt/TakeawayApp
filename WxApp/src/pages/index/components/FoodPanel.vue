<template>
  <div class="foodView">
    <van-panel>
      <view slot="header" class="food-panel-header">
        <van-tag
          mark
          size="large"
          :type="titleStyle">
          {{ title }}
        </van-tag>
      </view>
      <view>
        <van-panel custom-class="food-item-panel">
          <view class="food-item-content">
            <goods-card
              v-for="food in foodList"
              :key="food.goodsId"
              :food="food" />
          </view>
        </van-panel>
      </view>
    </van-panel>
  </div>
</template>

<script>
  import GoodsCard from '@/components/GoodsCard'

  export default {
    name: 'FoodPanel',
    props: {
      title: {
        type: String,
        required: true
      },
      foodList: {
        type: Array,
        required: true
      }
    },
    components: {
      GoodsCard
    },
    computed: {
      titleStyle () {
        let style = 'success'
        if (this.title === '热门') {
          style = 'danger'
        }
        return style
      }
    },
    methods: {}
  }
</script>

<style>
  .food-panel-header, .food-item-panel, .food-item-panel:after {
    border: none !important;
  }

  .food-panel-header {
    padding: 0.2rem;
    padding-bottom: 0rem;
  }

  .food-item-content {
    padding: 0.3rem 0.2rem;
  }
</style>

<style scoped>
  .foodView {
    margin-top: 0.2rem;
    width: 100%;
    background-color: white;
    overflow: hidden;
  }
</style>
