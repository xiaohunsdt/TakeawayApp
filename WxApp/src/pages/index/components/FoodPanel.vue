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
            <van-card
              v-for="food in foodList"
              :key="food.id"
              :title="food.name"
              :thumb="food.thumb"
              thumb-mode="aspectFill"
              custom-class="food-card-root"
              thumb-class="food-card-thumb"
              title-class="food-card-title">
              <view slot="desc" class="food-card-desc">
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
<!--                <van-button-->
<!--                  icon="goods-collect"-->
<!--                  size="small"-->
<!--                  type="primary"-->
<!--                  custom-class="orderBtn"-->
<!--                  round-->
<!--                  @click="addCart(food.id)">-->
<!--                  下单-->
<!--                </van-button>-->
                <order-stepper />
              </view>
            </van-card>
          </view>
        </van-panel>
      </view>
    </van-panel>
  </div>
</template>

<script>
  import OrderStepper from '@/components/OrderStepper'

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
    },
    components: {
      OrderStepper
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
    methods: {
      addCart (id) {
        console.log(`id is ${id}`)
      }
    }
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
  }

  .food-card-thumb {
    border-radius: 0.2rem;
    overflow: hidden;
  }

  .food-card-title {
    font-size: 0.4rem;
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

  .order-stepper-root{
    position: relative;
    top: -0.4rem;
    right: 0rem;
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
