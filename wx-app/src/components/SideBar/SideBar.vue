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
        scroll-y="true"
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
            <simple-goods-card
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
  import SimpleGoodsCard from '@/components/SimpleGoodsCard'

  export default {
    name: 'SideBar',
    props: {
      categoryGoods: {
        type: Object,
        required: true
      }
    },
    components: {
      SimpleGoodsCard
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
    width: 1.8rem;
    background: #F2F2F2;
    /*height: 100%;*/
    border-right: solid .01rem rgba(0, 0, 0, .13);
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
    border-left: solid .06rem #FFD200;
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

  .pesticide .pesticide-item {
    height: 1.72rem;
    padding: .24rem 0;
    margin-left: .24rem;
    padding-right: .24rem;
    display: flex;
    align-items: center;
    /*border-bottom: solid 1px #dddddd;*/
    position: relative;
    color: #333333;
  }

  .pesticide .pesticide-item image {
    width: 1.36rem;
    height: 1.36rem;
    margin-right: .24rem;
  }

  .pesticide .pesticide-item .pesticide-item-content {
    flex: 1;
    position: relative;
  }

  .pesticide .pesticide-item .management-item-content {
    min-height: 1.36rem;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }

  .pesticide .pesticide-item .describe {
    overflow: hidden;
    text-align: left;
    word-break: break-all;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    font-size: .28rem;
  }

  .pesticide .pesticide-item .inventory {
    font-size: .24rem;
    color: #999;
    margin-top: .08rem;
    display: inline;
  }

  .pesticide .pesticide-item .price {
    color: #FF8200;
    margin-top: .16rem;
    font-size: .28rem;
  }

  .pesticide .pesticide-item .price .init {
    margin-left: .08rem;
    color: #999;
    font-size: .24rem;
  }

  .pesticide .pesticide-item .amount {
    font-size: .24rem;
    color: #999;
  }

  .pesticide .pesticide-item .stepper {
    position: absolute;
    width: 1.7rem;
    height: .5rem;
    right: .3rem;
    bottom: 0;
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
