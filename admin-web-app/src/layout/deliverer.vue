<template>
  <router-view :key="key"/>
</template>

<script>
export default {
  name: 'Deliverer',
  computed: {
    cachedViews() {
      return this.$store.getters.cachedViews
    },
    key() {
      return this.$route.path
    }
  }
}
</script>

<style scoped>

</style>
