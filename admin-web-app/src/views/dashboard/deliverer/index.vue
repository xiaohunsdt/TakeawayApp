<template>
  <div class="container-contain">
    <base-card>
      <div class="dashboard-text">name: {{ userData.userName }}</div>
    </base-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import BaseCard from '@/components/BaseCard'

export default {
  name: 'DelivererDashboard',
  components: {
    BaseCard
  },
  data() {
    return {}
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  }
}
</script>

<style scoped>

</style>
