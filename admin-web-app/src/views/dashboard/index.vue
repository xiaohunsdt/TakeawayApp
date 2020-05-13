<template>
    <div class="dashboard-container">
        <!--    <div class="dashboard-text">name: {{ userData.name }}</div>-->
        <panel-group :dashboardData="dashboardData" v-if="dashboardData"/>
        <panel-group2 :dashboardData="dashboardData" v-if="dashboardData"/>
        <base-card style="margin: 20px 15px 0px 15px">
            <line-chart style="margin-top: 20px"/>
        </base-card>
        <el-row :gutter="30" style="margin-top: 30px;padding: 0 15px">
            <el-col :lg="8" :sm="24" :xs="24">
                <base-card>
                    <pie-chart :dashboardData="dashboardData" v-if="dashboardData"/>
                </base-card>
            </el-col>
            <el-col :lg="8" :sm="24" :xs="24">
                <base-card>
                    <bar-chart/>
                </base-card>
            </el-col>
            <el-col :lg="8" :sm="24" :xs="24">
                <base-card>
                    <order-naver-map
                        :height="300"
                        :all-order="true"/>
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
  import OrderNaverMap from '../order/components/order-naver-map'

  export default {
	name: 'Dashboard',
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
