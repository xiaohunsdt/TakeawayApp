<template>
  <div class="foodView">
    <view class="food-panel-header" slot="header">
      <van-tag
        :type="titleStyle"
        mark
        size="large">
        {{ title }}
      </van-tag>
    </view>
    <view class="food-item-panel">
      <view class="food-item-content">
        <main-goods-card
          :food="food"
          :key="food.id"
          v-for="food in foodList"/>
      </view>
    </view>
  </div>
</template>

<script>
  import MainGoodsCard from '@/components/GoodsCard/MainGoodsCard'

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
      MainGoodsCard
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
    margin-bottom: 0.2rem;
    width: 100%;
    background-color: white;
    overflow: hidden;
    border: none !important;
  }
</style>
