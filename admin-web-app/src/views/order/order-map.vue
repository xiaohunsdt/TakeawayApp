<template>
  <div class="map-container">
    <naver-map/>
  </div>
</template>

<script>
  import NaverMap from './components/OrderNaverMap'

  export default {
    name: 'OrderMap',
    components: {
      NaverMap
    }
  }
</script>

<style lang="scss" scoped>
  .map {
    &-container {}

    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
