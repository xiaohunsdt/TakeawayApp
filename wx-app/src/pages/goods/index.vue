<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <van-notice-bar
            :text="pageSettings.goodsPageNotice"
            left-icon="volume-o"
            scrollable="false"
            wrapable/>
          <div id="activity-info">
            <van-tag :key="tag" type="success" v-for="tag in pageSettings.goodsPageTags">{{ tag }}</van-tag>
          </div>
        </base-panel>
      </div>
      <div id="goods-content">
        <!--        <van-tabs-->
        <!--          @change="onChange"-->
        <!--          border-->
        <!--          custom-class="foodTab"-->
        <!--          nav-class="nav-class">-->
        <!--          <van-tab-->
        <!--            :key="categoryIndex"-->
        <!--            :title="category.name"-->
        <!--            v-for="(category,categoryIndex) in categories">-->
        <!--            <div :class="{'food-content':true,'has-submit-bar':cartAllCount > 0}">-->
        <!--              <simple-goods-card-->
        <!--                :food="food"-->
        <!--                :key="foodIndex"-->
        <!--                v-for="(food,foodIndex) in category.goodsList"/>-->
        <!--            </div>-->
        <!--          </van-tab>-->
        <!--        </van-tabs>-->
        <!--        <van-sidebar :active-key="currentIndex" bind:change="onChange">-->
        <!--          <van-sidebar-item-->
        <!--            :key="categoryIndex"-->
        <!--            :title="category.name"-->
        <!--            v-for="(category,categoryIndex) in categories">-->
        <!--          </van-sidebar-item>-->
        <!--        </van-sidebar>-->
        <side-bar
          :categoryGoods="categories"
          :pageSettings="pageSettings"/>
      </div>
    </div>
  </div>
</template>

<script>
  import categoryService from '@/services/category'
  import goodsService from '@/services/goods'
  import settingService from '@/services/setting'

  import BasePanel from '@/components/BasePanel'
  import SideBar from '@/components/SideBar/SideBar'
  import SimpleGoodsCard from '@/components/GoodsCard/SimpleGoodsCard'

  export default {
    components: {
      BasePanel,
      SideBar,
      SimpleGoodsCard
    },
    data () {
      return {
        currentIndex: 0,
        categories: [],
        pageSettings: {}
      }
    },
    watch: {
      showCart (newVal) {
        if (newVal) {
          this.getOrderItems()
        }
      }
    },
    onLoad () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    methods: {
      init () {
        // 先初始化数据
        Object.assign(this.$data, this.$options.data())

        // 获取相关设置项
        settingService.getGoodsPageSettings()
          .then(res => {
            this.pageSettings = res
          })

        // 获取所有分类
        categoryService.getAllCategory().then((res) => {
          res.forEach(item => {
            item.goodsList = []
          })

          this.categories = []
          this.categories.push(...res)
          // 获取数据
          // if (this.categories.length > 0) {
          //   this.getGoodsListByIndex(0)
          // }
          if (this.categories.length > 0) {
            mpvue.showLoading({
              title: '加载数据中...'
            })
            goodsService.getAllGoodsList()
              .then(res => {
                this.categories.forEach(category => {
                  res.forEach(goods => {
                    if (goods.category === category.name) {
                      category.goodsList.push(goods)
                    }
                  })
                })
                mpvue.hideLoading()
              })
              .catch(() => {
                mpvue.hideLoading()
              })
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
              this.categories[index].goodsList.push(...res)
              resolve()
            })
          }
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
  .container, .container-contain, #goods-content {
    height: 100%;
  }

  /*#activityInfo {*/
  /*  padding: 0 0.2rem;*/
  /*  margin-top: 0.2rem;*/
  /*}*/

  /*#activityInfo > van-tag {*/
  /*  margin-right: 0.1rem;*/
  /*}*/

  /*#order-content {*/
  /*  min-height: 5rem;*/
  /*  margin-top: 0.2rem;*/
  /*  background-color: white;*/
  /*}*/

  /*.food-content {*/
  /*  background-color: white;*/
  /*  padding: 0.2rem;*/
  /*}*/

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

  .cart-content {
    padding: .2rem;
  }
</style>
