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
      <div :style="'height:'+height+'px'" id="goods-content">
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
        height: 800,
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
        // Object.assign(this.$data, this.$options.data())

        // 获取相关设置项
        settingService.getGoodsPageSettings()
          .then(res => {
            Object.assign(this.pageSettings, res)
            // 设置高度
            const _this = this
            setTimeout(function () {
              _this.setHeaderHeight()
            }, 1000)
          })

        // 获取所有商品
        mpvue.showLoading({
          title: '正在加载中...'
        })
        goodsService.getAllGoodsList()
          .then(res => {
            this.categories = []
            this.categories.push(...res.categoryGoods)
            mpvue.hideLoading()
          })
          .catch(() => {
            mpvue.hideLoading()
          })
      },
      setHeaderHeight () {
        const _this = this

        mpvue.createSelectorQuery().select('#header').boundingClientRect(function (headerRect) {
          mpvue.getSystemInfo({
            success: function (res, rect) {
              if (headerRect && headerRect.height) {
                _this.height = res.windowHeight - headerRect.height - 10
              }
            }
          })
        }).exec()
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
</style>

<style scoped></style>
