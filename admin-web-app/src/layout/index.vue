<template>
  <component :is="currentRole"></component>
</template>

<script>
import { mapGetters } from 'vuex'
import BaseLayout from './base'
import DelivererLayout from './deliverer'

export default {
  name: 'Layout',
  components: {
    BaseLayout,
    DelivererLayout
  },
  data() {
    return {
      currentRole: 'BaseLayout'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    console.log(this.roles)
    if (this.roles.includes('DELIVERER')) {
      this.currentRole = 'DelivererLayout'
    }
  }
}
</script>

