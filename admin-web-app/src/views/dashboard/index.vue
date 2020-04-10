<template>
  <div class="dashboard-container">
<!--    <div class="dashboard-text">name: {{ userData.name }}</div>-->
    <naver-map />
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import NaverMap from './order-naver-map'

  export default {
    name: 'Dashboard',
    components: {
      NaverMap
    },
    computed: {
      ...mapGetters([
        'userData'
      ])
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }

    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
