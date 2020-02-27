<template>
  <div style="display: unset">
    <div class="container" v-if="activityList.length > 0">
      <div class="gradientDiv"></div>
      <div class="container-contain">
        <img
          v-for="activity in activityList"
          :key="activity.id" class="activityImg"
          :src="activity.mainImg"
          @click="onActivityClick(activity.id)">
      </div>
    </div>
    <div class="none-content-div" v-if="activityList.length === 0">
      <img mode="aspectFit" src="/static/images/none/no_news.png" style="width: 10rem">
      <div style="margin-top: .5rem">暂无活动</div>
    </div>
  </div>
</template>

<script>
  import activityService from '@/services/activity'

  export default {
    data () {
      return {
        activityList: []
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
        activityService.getAllActivityList().then(res => {
          this.activityList = []
          this.activityList.push(...res)
        })
      },
      onActivityClick (id) {
        mpvue.navigateTo({
          url: `/pages/activity/detail/main?activityId=${id}`
        })
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.3rem;
  }

  .activityImg {
    width: 100%;
    height: 3rem;
    margin-bottom: 0.3rem;
  }

  .none-content-div {
    position: relative;
    z-index: 100;
    width: 100%;
    height: 100%;
    text-align: center;
  }
</style>
