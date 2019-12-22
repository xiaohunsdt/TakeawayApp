<template>
  <div class="container-side-bar">
    <div class="nav">
      <scroll-view class="nav-scroll" scroll-y="true" style="height: 100%;">
        <div
          :class="{'active':currentId===category.id}"
          :key="category.id"
          @click="chooseType(category.id)"
          class="nav-item"
          v-for="category in categoryGoods">
          {{category.name}}
        </div>
      </scroll-view>
    </div>
    <div class="content content-class">
      <scroll-view
        :scroll-into-view="contentId"
        class="content-scroll"
        scroll-with-animation="true"
        :scroll-y="contentId"
        style="height: 100%;">
        <div
          :id="'xh_'+category.id"
          :key="category.id"
          class="pesticide"
          v-for="category in categoryGoods">
          <div class="type-name">
            <div class="line"></div>
            <div class="name">{{category.name}}</div>
            <div class="line"></div>
          </div>
          <div class="pesticide-container">
            <standard-goods-card
              :food="food"
              :key="foodIndex"
              v-for="(food,foodIndex) in category.goodsList"/>
          </div>
        </div>
        <view class="has-no-more">
          <view class="line"></view>
          <view class="name">没有更多</view>
          <view class="line"></view>
        </view>
      </scroll-view>
    </div>
  </div>
</template>

<script>
  import StandardGoodsCard from '@/components/GoodsCard/StandardGoodsCard'

  export default {
    name: 'SideBar',
    props: {
      categoryGoods: {
        type: Object,
        required: true
      }
    },
    components: {
      StandardGoodsCard
    },
    watch: {
      categoryGoods () {
        if (this.categoryGoods && this.categoryGoods.length > 0) {
          this.currentId = this.categoryGoods[0].id
          this.setContentId()
        }
      }
    },
    data () {
      return {
        currentId: '',
        contentId: ''
      }
    },
    methods: {
      chooseType (event) {
        this.currentId = event
        this.setContentId()
      },
      setContentId () {
        this.contentId = `xh_${this.currentId}`
      }
    }
  }
</script>

<style scoped>
  .container-side-bar {
    width: 100%;
    height: 100%;
    display: flex;
    overflow: scroll;
    color: #3a3a3a;
  }

  .container-side-bar .nav {
    width: 1.6rem;
    background: #F2F2F2;
    /*height: 100%;*/
    /*border-right: solid .01rem rgba(0, 0, 0, .13);*/
  }

  .container-side-bar .nav .nav-scroll {
    overflow: scroll;
  }

  .container-side-bar .nav .nav-item {
    text-align: center;
    width: 100%;
    display: block;
    /*min-height: 100rpx;*/
    /*line-height: 100rpx;*/
    color: #313131;
    padding: .32rem 0;
    font-size: .32rem;
  }

  .container-side-bar .nav .active {
    background: #ffffff;
    width: calc(100% - .06rem);
    border-right: solid .06rem #FFD200;
    font-weight: bold;
  }

  .container-side-bar .content {
    flex: 1;
    background: #fff;
    height: 100%;
  }

  .container-side-bar .content .content-scroll {
    overflow: hidden;
  }

  .container-side-bar .pesticide .type-name {
    display: flex;
    justify-content: center;
    height: .72rem;
    align-items: center;
  }

  .container-side-bar .pesticide .type-name .line {
    width: .4rem;
    height: 0.02rem;
    border-bottom: solid .01rem #ddd;
  }

  .container-side-bar .pesticide .type-name .name {
    margin: 0 .16rem;
  }

  .pesticide .pesticide-container {
    padding: 0 .24rem;
  }

  .content .has-no-more {
    display: flex;
    justify-content: center;
    padding: .3rem 0;
    color: #ccc;
    align-items: center;
  }

  .content .has-no-more .line {
    width: .4rem;
    height: .02rem;
    border-bottom: solid .01rem #ddd;
  }

  .content .has-no-more .name {
    margin: 0 .16rem;
  }
</style>
