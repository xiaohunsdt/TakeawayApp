<template>
  <div class="container-contain">
    <el-button icon="el-icon-switch-button" round size="small" style="margin-bottom: 10px;float: right" type="danger" @click="logout">
      退出
    </el-button>
    <base-card style="clear: both">
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
  },
  methods: {
    async logout() {
      await this.$store.dispatch('admin/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style scoped>

</style>
