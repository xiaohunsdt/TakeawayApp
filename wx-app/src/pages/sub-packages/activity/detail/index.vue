<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <wx-parse
        v-if="activity"
        :content="activity.content"
        @preview="preview"
        @navigate="navigate"/>
    </div>
  </div>
</template>

<script>
  import wxParse from 'mpvue-wxparse'
  import activityService from '@/services/activity'

  export default {
    components: {
      wxParse
    },
    onLoad (options) {
      if (options.activityId) {
        this.activityId = options.activityId
        this.init()
      }
    },

    data () {
      return {
        activityId: null,
        activity: null
      }
    },
    onShareAppMessage: function () {
      return {
        title: this.activity.title,
        desc: '川香苑品牌中餐厅',
        path: `/pages/sub-packages/activity/detail/main?activityId=${this.activityId}`
      }
    },
    methods: {
      init () {
        activityService.getActivityById(this.activityId).then(res => {
          this.activity = res
        })
      },
      preview (src, e) {
        console.log(src, e)
      },
      navigate (href, e) {
        console.log(href, e)
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    /*padding: 0.3rem 0.3rem;*/
  }
</style>
