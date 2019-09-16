<template>
  <div class="base-panel">
    <div class="panel-content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'BasePanel',
    methods: {}
  }
</script>

<style scoped>
  .base-panel {
    padding: 0 .2rem;
    position: relative;
    z-index: 100;
    background-color: transparent;
  }

  .panel-content {
    position: relative;
    padding: 0.25rem;
    background-color: white;
    border-radius: 0.2rem;
    overflow: hidden;
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
  }
</style>
