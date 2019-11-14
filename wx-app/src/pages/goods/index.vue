<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <van-notice-bar
            :text="systemSettings.goods_page_notice"
            left-icon="volume-o"
          />
          <div id="activity-info">
            <van-tag type="success" v-for="tag in systemSettings.goods_page_tags">{{ tag }}</van-tag>
          </div>
        </base-panel>
      </div>
      <div id="order-content">
        <van-tabs
          @change="onChange"
          animated
          border
          custom-class="foodTab"
          nav-class="nav-class"
          swipeable>
          <van-tab
            :key="category.id"
            :title="category.name"
            v-for="(category,categoryIndex) in categories">
            <div :class="{'food-content':true,'has-submit-bar':cartCount > 0}">
              <goods-card
                :food="food"
                :key="food.id"
                v-for="(food,foodIndex) in category.goodsList"/>
            </div>
          </van-tab>
        </van-tabs>
      </div>
      <div id="footer" v-if="cartCount > 0">
        <van-submit-bar
          :disabled="!systemSettings.service_running"
          :price="cartAllPrice"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submit-btn"
          button-text="提交订单"
          currency="₩"
          custom-class="order-submit-bar"
          price-class="order-price">
          <div id="order-bar-left-content">
            <img alt="" src="/static/images/order/cart.png">
            <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;">
              {{ cartCount }}
            </div>
          </div>
          <view v-if="!systemSettings.service_running" slot="tip">{{systemSettings.service_close_notice}}</view>
        </van-submit-bar>
      </div>
    </div>
  </div>
</template>

<script>
  import categoryService from '@/services/category'
  import goodsService from '@/services/goods'
  import settingService from '@/services/setting'

  import BasePanel from '@/components/BasePanel'
  import GoodsCard from '@/components/GoodsCard'

  export default {
    components: {
      BasePanel,
      GoodsCard
    },
    data () {
      return {
        currentIndex: 0,
        categories: [],
        systemSettings: {}
      }
    },
    computed: {
      cartCount () {
        return this.$store.getters.cartAllCount
      },
      cartAllPrice () {
        return this.$store.getters.cartAllPrice
      }
    },
    onLoad () {
      this.init(this.currentIndex)
    },
    onPullDownRefresh () {
      this.init(this.currentIndex)
      mpvue.stopPullDownRefresh()
    },
    methods: {
      init (index) {
        // 获取相关设置项
        settingService.getSystemSettings().then(res => {
            this.systemSettings = Object.assign({}, res)
          }
        )
        // 先清除分类信息
        this.categories.splice(0, this.categories.length)
        // 获取所有分类
        categoryService.getAllCategory().then((res) => {
          res.forEach(item => {
            item.goodsList = []
            this.categories.push(item)
          })

          // 初始化数据
          this.getGoodsListByIndex(index)

          // 提前加载下一页,如果可能的话
          if (this.categories.length > index + 1) {
            this.getGoodsListByIndex(index + 1)
          }
        })
      },
      getGoodsListByIndex (index) {
        return new Promise((resolve, reject) => {
          if (this.categories.length > index) {
            // 如果已经存在数据就直接返回
            if (this.categories[index].goodsList.length > 0) {
              return
            }

            const categoryId = this.categories[index].id
            goodsService.getGoodsListByCategoryId(categoryId).then(res => {
              res.forEach(item => {
                this.categories[index].goodsList.push(item)
              })
              resolve()
            })
          }
        })
      },
      onChange (event) {
        const index = event.mp.detail.name
        this.currentIndex = index

        this.getGoodsListByIndex(index)

        // 提前加载下一页,如果可能的话
        if (this.categories.length > index + 1) {
          this.getGoodsListByIndex(index + 1)
        }
      },
      onSubmitOrder () {
        mpvue.navigateTo({
          url: `/pages/buy/main`
        })
      }
    }
  }
</script>

<style>
  .van-notice-bar {
    height: auto !important;
  }

  .van-submit-bar__bar--safe {
    padding-bottom: unset !important;
  }

  .nav-class .van-tabs__line {
    background-color: #FFD200;
  }

  #activity-info .van-tag {
    margin-left: 0.1rem;
    display: unset !important;
  }

  .order-submit-bar {
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
  }

  .submit-btn {
    font-weight: bolder !important;
    background-color: #FFD200 !important;
    border-color: #FFD200 !important;
  }

  .order-price {
    font-weight: bolder;
  }
</style>

<style scoped>
  #activityInfo {
    padding: 0 0.2rem;
    margin-top: 0.2rem;
  }

  #activityInfo > van-tag {
    margin-right: 0.1rem;
  }

  #order-content {
    min-height: 5rem;
    margin-top: 0.2rem;
    background-color: white;
  }

  .food-content {
    background-color: white;
    padding: 0.2rem;
  }

  .has-submit-bar {
    padding-bottom: .7rem;
  }

  #order-bar-left-content {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #order-bar-left-content img {
    width: 0.6rem;
    height: 0.5rem;
  }
</style>
