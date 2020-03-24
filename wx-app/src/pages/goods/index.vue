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
        <side-bar
          :categoryGoods="categories"
          :pageSettings="pageSettings"/>
      </div>
    </div>
  </div>
</template>

<script>
  import goodsService from '@/services/goods'
  import settingService from '@/services/setting'

  import BasePanel from '@/components/BasePanel'
  import SideBar from '@/components/SideBar/SideBar'
  import { mapMutations } from 'vuex'

  export default {
    components: {
      BasePanel,
      SideBar
    },
    data () {
      return {
        currentIndex: 0,
        categories: [],
        pageSettings: {}
      }
    },
    onLoad () {
      this.init()

      // 初始化购物车数量
      this.INIT_CRET()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    methods: {
      ...mapMutations('cart', [
        'INIT_CRET'
      ]),
      init () {
        // 先初始化数据
        Object.assign(this.$data, this.$options.data())

        // 获取相关设置项
        settingService.getGoodsPageSettings()
          .then(res => {
            Object.assign(this.pageSettings, res)
          })

        // 获取所有商品
        mpvue.showLoading({
          title: '正在加载中...'
        })
        goodsService.getAllGoodsList()
          .then(res => {
            this.categories.push(...res.categoryGoods)
            mpvue.hideLoading()
          })
          .catch(() => {
            mpvue.hideLoading()
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
</style>
