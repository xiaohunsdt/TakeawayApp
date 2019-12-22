<template>
  <div style="display: unset">
    <!--  <div class="container">-->
    <!--    <div class="gradientDiv"></div>-->
    <!--    <div class="container-contain">-->
    <!--      <img alt="" class="activityImg" src="/static/images/activities/activityggj.png">-->
    <!--      <img alt="" class="activityImg" src="/static/images/activities/activitypraise.png">-->
    <!--      <img alt="" class="activityImg" src="/static/images/activities/activityrun.png">-->
    <!--      <img alt="" class="activityImg" src="/static/images/activities/activityrush.png">-->
    <!--    </div>-->
    <!--  </div>-->
    <div class="none-content-div">
      <img mode="aspectFit" src="/static/images/none/no_news.png" style="width: 10rem">
      <div style="margin-top: .5rem">暂无活动</div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {}
    },
    methods: {}
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
