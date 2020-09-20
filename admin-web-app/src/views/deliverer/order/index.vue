<template>
  <div class="container-contain">
    <van-tabs sticky swipeable>
      <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
        <van-tab :badge="willDeliveryOrderCount" :style="{'min-height': scrollerHeight}" class="tab" title="待配送">
          <order-card v-for="order in orderList" :key="order.id" :order="order"></order-card>
        </van-tab>
        <van-tab :badge="myDeliveryOrderCount" :style="{'min-height': scrollerHeight}" class="tab" title="正在配送">
        </van-tab>
      </van-pull-refresh>
    </van-tabs>
    <right-panel :button-icon="'el-icon-location'" :button-top="300" :full-screen="true">
      <template v-slot:default="slotProps">
        <div style="margin-top: 10px">
          <el-button icon="el-icon-refresh" round size="small" style="position: absolute;right: 0;top: 10px"
                     type="primary" @click="onRefreshMap">
            刷新
          </el-button>
          <order-naver-map v-if="slotProps.show" :key="mapRefreshCount" ref="naver-map" :all-order="false"
                           :height="mapHeight"/>
        </div>
      </template>
    </right-panel>
  </div>
</template>

<script>
import orderService from '@a/order'
import OrderCard from './components/order-card'
import RightPanel from '@c/RightPanel'
import OrderNaverMap from '@v/order/components/OrderNaverMap'
import { PullRefresh, Tab, Tabs } from 'vant'

export default {
  name: 'DelivererOrderManagement',
  components: {
    OrderCard,
    RightPanel,
    OrderNaverMap,
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,
    [PullRefresh.name]: PullRefresh
  },
  data() {
    return {
      isLoading: true,
      orderList: [],
      willDeliveryOrderCount: 0,
      myDeliveryOrderCount: 0,
      mapRefreshCount: 0
    }
  },
  computed: {
    mapHeight() {
      return window.innerHeight - 100
    },
    scrollerHeight() {
      return window.innerHeight - 44 + 'px'
    }
  },
  watch: {
    orderList: {
      handler(newList) {
        this.willDeliveryOrderCount = 0
        this.myDeliveryOrderCount = 0
        newList.forEach(item => {
          if (item.orderState === 'WAITING_RECEIVE' || item.orderState === 'PRODUCING') {
            this.willDeliveryOrderCount++
          }
        })
      },
      deep: true
    }
  },
  created() {
    this.getOrderList()
  },
  methods: {
    getOrderList() {
      orderService.getTodayOrderListByState('WAIT_EAT').then(res => {
        this.orderList = res
      })
    },
    onRefresh() {
      this.isLoading = false
      this.getOrderList()
    },
    onRefreshMap() {
      this.mapRefreshCount++
      if (this.mapRefreshCount > 100000) {
        this.mapRefreshCount = 1
      }
    }
  }
}
</script>

<style>
.rightPanel-items {
  padding: 10px 0px 0px 0px !important;
}
</style>

<style scoped>
.container-contain {
  padding: unset !important;
}

.tab {
  padding: 0.6rem;
}
</style>
