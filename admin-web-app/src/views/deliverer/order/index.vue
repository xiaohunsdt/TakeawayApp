<template>
  <div class="container-contain">
    <van-tabs sticky swipeable>
      <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
        <van-tab :style="{'min-height': scrollerHeight}" class="tab" title="待配送">
          <order-card></order-card>
        </van-tab>
      </van-pull-refresh>
      <van-tab :style="{'min-height': scrollerHeight}" class="tab" title="正在配送">
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import OrderCard from './components/order-card'

import { PullRefresh, Tab, Tabs } from 'vant'

export default {
  name: 'DelivererOrderManagement',
  components: {
    OrderCard,
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,
    [PullRefresh.name]: PullRefresh
  },
  data() {
    return {
      isLoading: true
    }
  },
  computed: {
    scrollerHeight() {
      return window.innerHeight - 44 + 'px'
    }
  },
  methods: {
    onRefresh() {
      this.isLoading = false
    }
  }
}
</script>

<style scoped>
.container-contain {
  padding: unset !important;
}

.tab {
  padding: 0.6rem;
}
</style>
