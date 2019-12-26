<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <wx-parse v-if="activity" :content="activity.content" @preview="preview" @navigate="navigate"/>
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
