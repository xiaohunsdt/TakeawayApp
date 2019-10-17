<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <van-notice-bar
            left-icon="volume-o"
            text="家的味道,优惠的价格! 留学的期间, 有我有你! 让我们共同成长!"
          />
          <div id="activity-info">
            <van-tag type="success">免费配送</van-tag>
            <van-tag type="success">满1w5送月饼</van-tag>
            <van-tag type="success">满2w送饮料</van-tag>
          </div>
        </base-panel>
      </div>
      <div id="order-content">
        <van-tabs
          :active="0"
          @change="onChange"
          animated
          border
          custom-class="foodTab"
          nav-class="nav-class"
          sticky
          swipeable>
          <van-tab
            :key="category.id"
            :title="category.name"
            v-for="(category,categoryIndex) in categories">
            <div class="foodContent">
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
          :loading="submitLoading"
          :price="cartAllPrice"
          currency="₩"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submit-btn"
          button-text="提交订单"
          custom-class="order-submit-bar"
          price-class="order-price">
          <div id="orderBarLeftContent">
            <img alt="" src="/static/images/order/cart.png">
            <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;">
              {{ cartCount }}
            </div>
          </div>
          <!--          <view slot="tip">当前下单高峰期, 您可能需要等待较长时间才能就餐!</view>-->
        </van-submit-bar>
      </div>
    </div>
  </div>
</template>

<script>
  import categoryService from '@/services/category'
  import goodsService from '@/services/goods'
  import BasePanel from '@/components/BasePanel'
  import GoodsCard from '@/components/GoodsCard'

  export default {
    components: {
      BasePanel,
      GoodsCard
    },
    data () {
      return {
        submitLoading: false,
        categories: []
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
    created () {
      this.init()
    },
    methods: {
      init () {
        // 获取所有分类
        categoryService.getAllCategory().then((res) => {
          res.forEach(item => {
            item.goodsList = []
            this.categories.push(item)
          })

          // 初始化数据
          this.getGoodsListByIndex(0)

          // 提前加载下一页,如果可能的话
          if (this.categories.length > 1) {
            this.getGoodsListByIndex(1)
          }
        })
      },
      getGoodsListByIndex (index) {
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
          })
        }
      },
      onChange (event) {
        const index = event.mp.detail.index

        this.getGoodsListByIndex(index)

        // 提前加载下一页,如果可能的话
        if (this.categories.length > index + 1) {
          this.getGoodsListByIndex(index + 1)
        }
      },
      onSubmitOrder () {
        wx.showToast({
          title: '提交订单',
          icon: 'none'
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

  .foodContent {
    background-color: white;
    padding: 0.2rem;
  }

  #orderBarLeftContent {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #orderBarLeftContent img {
    width: 0.6rem;
    height: 0.5rem;
  }
</style>
