<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <address-card
        :address="address"
        :key="address.id"
        @delete-address="deleteAddressEvent"
        @set-default="setDefaultEvent"
        v-for="address in addressList"/>
      <van-button
        @click="addNewAddress"
        color="#FFD200"
        custom-class="add-address-btn"
        round
        size="large">
        添加新地址
      </van-button>
    </div>
  </div>
</template>

<script>
  import { mapMutations } from 'vuex'

  import BasePanel from '@/components/BasePanel'
  import AddressCard from './components/AddressCard'
  import addressService from '@/services/address'

  export default {
    components: {
      BasePanel,
      AddressCard
    },
    data () {
      return {
        addressList: []
      }
    },
    onShow () {
      this.init()
    },
    onUnload () {
      const selectedAddress = this.addressList.find(item => item.isDefault)
      if (selectedAddress) {
        this.SET_ADDRESS(selectedAddress)
      }
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    methods: {
      ...mapMutations('address', [
        'SET_ADDRESS'
      ]),
      init () {
        this.addressList.splice(0, this.addressList.length)
        addressService.getMyAddressList()
          .then(res => {
            res.forEach(item => {
              this.addressList.push(item)
            })
          })
      },
      addNewAddress () {
        mpvue.navigateTo({
          url: '/pages/my/address/edit/main'
        })
      },
      setDefaultEvent (addressId) {
        addressService.setDefault(addressId)
          .then(res => {
            // 将当前的地址设置成订单地址
            const address = this.addressList.find(item => item.id === addressId)
            this.SET_ADDRESS(address)
            this.init()
          })
      },
      deleteAddressEvent (addressId) {
        const $this = this
        wx.showModal({
          title: '提示',
          content: '您确定要删除这个地址吗?',
          success (res) {
            if (res.confirm) {
              addressService.deleteAddress(addressId)
                .then(res => {
                  $this.init()
                })
            }
          }
        })
      }
    }
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
