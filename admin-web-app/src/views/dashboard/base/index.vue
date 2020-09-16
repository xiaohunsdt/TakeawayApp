<template>
  <div class="dashboard-container">
    <!--    <div class="dashboard-text">name: {{ userData.name }}</div>-->
    <panel-group v-if="dashboardData" :dashboardData="dashboardData"/>
    <panel-group2 v-if="dashboardData" :dashboardData="dashboardData"/>
    <base-card style="margin: 20px 15px 0px 15px">
      <line-chart v-if="dashboardData" :dashboardData="dashboardData" style="margin-top: 20px"/>
    </base-card>
    <el-row :gutter="30" style="margin-top: 30px;padding: 0 15px">
      <el-col :lg="8" :sm="24" :xs="24">
        <base-card>
          <pie-chart v-if="dashboardData" :dashboardData="dashboardData"/>
        </base-card>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <base-card>
          <bar-chart v-if="dashboardData" :dashboardData="dashboardData"/>
        </base-card>
      </el-col>
      <el-col :lg="8" :sm="24" :xs="24">
        <base-card>
          <order-naver-map
              :all-order="true"
              :height="300"/>
        </base-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import dashboardApi from '@/api/dashboard'

import BaseCard from '@/components/BaseCard'
import PanelGroup from './components/PanelGroup'
import PanelGroup2 from './components/PanelGroup2'
import LineChart from './components/LineChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import OrderNaverMap from '@v/order/components/OrderNaverMap'

export default {
  name: 'BaseDashboard',
  components: {
    BaseCard,
    PanelGroup,
    PanelGroup2,
    LineChart,
    PieChart,
    BarChart,
    OrderNaverMap
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  },
  data() {
    return {
      dashboardData: null
    }
  },
  created() {
    dashboardApi.getDashboardData().then(res => {
      this.dashboardData = res
    })
  }
}
</script>

<style lang="scss" scoped>
$bg: #F3F3F9;

.dashboard {
  &-container {
    background-color: $bg;
    min-height: inherit;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
