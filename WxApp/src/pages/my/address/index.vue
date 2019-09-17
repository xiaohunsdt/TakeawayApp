<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <address-card>

      </address-card>
    </div>
  </div>
</template>

<script>
    import BasePanel from '@/components/BasePanel'
    import AddressCard from './components/AddressCard'

    export default {
        components: {
            BasePanel,
            AddressCard
        },
        data () {
            return {
                addressList: [
                    {
                        id: 'dfgdfkaxva113',
                        region: '신촌포스빌 707호',
                        detail: '楼下密码🗝1234#',
                        isDefault: true
                    },
                    {
                        id: 'dfgdfkaxva113',
                        region: '신촌포스빌 707호',
                        detail: '楼下密码🗝1234#',
                        isDefault: true
                    },
                    {
                        id: 'dfgdfkaxva113',
                        region: '신촌포스빌 707호',
                        detail: '楼下密码🗝1234#',
                        isDefault: true
                    }
                ]
            }
        },
        methods: {}
    }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }
</style>
