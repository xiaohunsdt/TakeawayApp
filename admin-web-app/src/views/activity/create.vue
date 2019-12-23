<template>
  <div class="container">
    <base-card class="container-main">

    </base-card>
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'

  export default {
    name: 'CreateActivity',
    components: {
      BaseCard
    },
    data() {
      return {
        formData: {}
      }
    },
    methods: {
    }
  }
</script>

<style lang="scss" scoped>
  $bg: #F3F3F9;

  .container {
    padding: 1rem;
    min-height: inherit;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
  }

  .el-form-item {
    margin-bottom: unset !important;
  }
</style>
