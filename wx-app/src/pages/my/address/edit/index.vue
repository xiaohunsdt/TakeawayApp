<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <van-cell-group>
          <van-cell is-link>
            <view slot="title" style="display: flex;">
              <view style="width: 3.8em">地址</view>
              <view @click="onSearchAddress" style="width: calc(100% - 3.8em)">
                {{address.address?address.address:'请点击输入地址'}}
              </view>
            </view>
          </van-cell>
          <van-field
            :border="false"
            :value="address.detail"
            @change="detailInput"
            label="详细"
            placeholder="门牌号,楼下密码,送餐提示等详细信息"
            title-width="3em"/>
          <van-field
            :border="false"
            :value="address.phone"
            @change="phoneInput"
            label="手机号"
            placeholder="请输入手机号,手机格式为:01056511996"
            required
            title-width="3em"/>
        </van-cell-group>
      </base-panel>
      <van-button
        :disable="editLoading"
        :loading="editLoading"
        @click="editBtnClick"
        color="#FFD200"
        custom-class="add-address-btn"
        loading-type="spinner"
        round
        size="large">
        <div v-if="addressId">修改地址</div>
        <div v-else>添加新地址</div>
      </van-button>
    </div>
    <van-toast id="van-toast"/>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import Toast from '@/../static/vant/toast/toast'
  import addressService from '@/services/address'

  export default {
    name: 'AddressEdit',
    components: {
      BasePanel
    },
    data () {
      return {
        editLoading: false,
        addressId: '',
        address: {
          address: '',
          detail: '',
          phone: '',
          x: 0,
          y: 0
        }
      }
    },
    onLoad (options) {
      this.init()
      if (options.hasOwnProperty('addressId')) {
        this.addressId = options.addressId
        addressService.getAddressById(this.addressId).then(res => {
          this.address = Object.assign({}, this.address, res)
          console.log(this.address)
        })
      }
    },
    methods: {
      init () {
        Object.assign(this.$data, this.$options.data())
      },
      clickRightIcon () {
        Toast('请输入街道名,小区名,道路名\r\n例如: 서울 마포구 신촌로 150')
      },
      regionInput (value) {
        console.log(value.mp.detail)
        this.address.address = value.mp.detail
      },
      detailInput (value) {
        this.address.detail = value.mp.detail
      },
      phoneInput (value) {
        this.address.phone = value.mp.detail
      },
      onSearchAddress (event) {
        console.log(event)

        var $this = this
        mpvue.navigateTo({
            url: 'search/main',
            events: {
              setSelectedAddress (data) {
                console.log(data)
                $this.address.address = data.address
                $this.address.x = data.x
                $this.address.y = data.y
              }
            }
          }
        )
      },
      editBtnClick () {
        if (this.addressId) {
          this.create()
        } else {
          this.edit()
        }
      },
      create () {
        this.editLoading = true
        addressService.updateAddress(this.address)
          .then(res => {
            mpvue.showToast({
              title: res.message,
              icon: 'success',
              duration: 2000
            })
            this.editLoading = false
            mpvue.navigateBack()
          })
          .catch(() => {
            this.editLoading = false
          })
      },
      edit () {
        const { address, detail, phone } = this.address
        if (!address || !phone) {
          mpvue.showToast({
            title: '请填写完整信息',
            icon: 'none',
            duration: 2000
          })
          return
        }

        this.editLoading = true
        addressService.createNewAddress({ address, detail, phone })
          .then(res => {
            mpvue.showToast({
              title: res.message,
              icon: 'success',
              duration: 2000
            })
            this.editLoading = false
            mpvue.navigateBack()
          })
          .catch(() => {
            this.editLoading = false
          })
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
