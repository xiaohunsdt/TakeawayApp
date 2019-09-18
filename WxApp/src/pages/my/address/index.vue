<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <address-card
        :address="address"
        :key="address.id"
        v-for="address in addressList"/>
      <van-button custom-class="add-address-btn" color="#FFD200" size="large" round>
        添加新地址
      </van-button>
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
                        phone: '01056511996',
                        isDefault: false
                    },
                    {
                        id: 'dfgdfkaxva113',
                        region: '신촌포스빌 707호',
                        detail: '楼下密码🗝1234#',
                        phone: '01056511996',
                        isDefault: false
                    },
                    {
                        id: 'dfgdfkaxva113',
                        region: '신촌포스빌 707호',
                        detail: '楼下密码🗝1234#',
                        phone: '01056511996',
                        isDefault: true
                    }
                ]
            }
        },
        methods: {}
    }
</script>
<style>
  .add-address-btn {
    position: relative;
    left: 50%;
    transform: translate(-50%, 0);
    width: 70% !important;
    margin-top: .2rem;
    color: black !important;
    font-weight: bolder !important;
    height: .8rem !important;
    line-height: .8rem !important;
  }
</style>
<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }
</style>
