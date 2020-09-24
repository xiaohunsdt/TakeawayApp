<template>
  <component :is="currentRole"></component>
</template>

<script>
import { mapGetters } from 'vuex'
import BaseDashboard from './base'
import DelivererDashboard from './deliverer'

export default {
  name: 'Dashboard',
  components: {
    BaseDashboard,
    DelivererDashboard
  },
  data() {
    return {
      currentRole: 'BaseDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (this.roles.includes('DELIVERER')) {
      this.currentRole = 'DelivererDashboard'
    }
  }
}
</script>
