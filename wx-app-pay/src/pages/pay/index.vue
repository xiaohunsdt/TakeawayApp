<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      {{orderId}}
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        orderId: ''
      }
    },
    onLoad (options) {
      this.orderId = options.orderId
    },
    methods: {}
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.3rem;
  }
</style>
