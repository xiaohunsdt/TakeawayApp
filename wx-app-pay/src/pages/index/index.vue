<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <h1 id="header">诺本网络 <br/> 欢迎您</h1>

    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {
        dataList: [
          {
            title: '测试1',
            content: ''
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped>
  .container-contain {
    display: flex;
    align-content: center;
    justify-content: center;
    height: max-content;
  }

  #header {
    font-size: 1rem;
    text-align: center;
  }
</style>
