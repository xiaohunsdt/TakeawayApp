<template>
  <div id="deliverer-wrapper">
    <div class="container">
      <div class="gradient-div"></div>
      <router-view :key="key"/>
    </div>
    <van-tabbar active-color="#FFD200" inactive-color="#757575" route>
      <van-tabbar-item icon="wap-home" replace to="/dashboard">
        首页
      </van-tabbar-item>
      <van-tabbar-item icon="label" replace to="/deliverer/index">
        待处理
      </van-tabbar-item>
      <van-tabbar-item icon="column" replace to="/deliverer/index">
        记录
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import { Tab, Tabbar, TabbarItem, Tabs } from 'vant'

export default {
  name: 'Deliverer',
  components: {
    [Tab.name]: Tab,
    [Tabs.name]: Tabs,
    [Tabbar.name]: Tabbar,
    [TabbarItem.name]: TabbarItem
  },
  computed: {
    cachedViews() {
      return this.$store.getters.cachedViews
    },
    key() {
      return this.$route.path
    }
  }
}
</script>

<style>
#deliverer-wrapper .container-contain {
  padding: .6rem;
  position: relative;
  z-index: 100;
}
</style>

<style scoped>
#deliverer-wrapper {
  height: 100%;
}

.container {
  background-color: #F0F0F0;
  min-height: 100%;
  width: 100%;
}

.gradient-div {
  position: absolute;
  background: linear-gradient(#FFD200, white, #F0F0F0);
  width: 100%;
  height: 30rem;
}
</style>
