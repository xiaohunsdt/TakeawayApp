<template>
  <div class="base-card">
    <slot/>
  </div>
</template>

<script>
    export default {
        name: 'BaseCard'
    }
</script>

<style lang="scss" scoped>
  .base-card {
    padding: 1rem;
    margin-bottom: 20px;
    border: 0px;
    border-radius: 0.625rem;
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
  }
</style>
