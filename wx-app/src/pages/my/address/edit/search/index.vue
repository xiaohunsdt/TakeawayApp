<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <search-bar @search="onSearch"/>
      <base-panel v-if="addressList.length > 0">
        <van-cell
          :title="address"
          icon="location-o"
          is-link
          @click="onSelect(address)"
          v-for="address in addressList"
          :key="address"
        />
      </base-panel>
    </div>
    <van-toast id="van-toast"/>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import SearchBar from '@/components/SearchBar'
  import addressService from '@/services/address'

  export default {
    name: 'AddressEdit',
    components: {
      BasePanel,
      SearchBar
    },
    data () {
      return {
        editLoading: false,
        addressList: []
      }
    },
    onLoad () {
      this.init()
    },
    methods: {
      init () {
        Object.assign(this.$data, this.$options.data())
      },
      onSearch (event) {
        addressService.searchAddress(event).then(res => {
          this.addressList = res
        })
      },
      onSelect (address) {
        let eventChannel = this.$mp.page.getOpenerEventChannel()
        eventChannel.emit('setSelectedAddress', { address })
        global.mpvue.navigateBack()
      }
    }
  }
</script>

<style>
  .van-icon, .van-icon:before {
    display: block !important;

  }

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
