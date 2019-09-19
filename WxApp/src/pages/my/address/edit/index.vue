<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <van-cell-group>
          <van-field
            :value="address.region"
            @change="regionInput"
            autosize
            clearable
            label="地址"
            placeholder="请输入地址"
            required
            type="textarea">
            <van-icon @click="clickRightIcon" name="question-o" size="1.1rem" slot="right-icon"/>
          </van-field>
          <van-field
            :border="false"
            :value="address.detail"
            label="详细"
            placeholder="楼下密码,送餐提示等详细信息"
            required
            @change="detailInput"/>
          <van-field
            :value="address.phone"
            label="手机号"
            placeholder="请输入手机号, 请务必填写正确!!"
            @change="phoneInput"
            required
            :border="false"/>
        </van-cell-group>
      </base-panel>
      <van-button custom-class="add-address-btn" color="#FFD200" size="large" round>
        添加新地址
      </van-button>
    </div>
    <van-toast id="van-toast"/>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import Toast from '@/../static/vant/toast/toast'

  export default {
    name: 'AddressEdit',
    props: {
      addressId: {
        type: Number,
        required: false
      }
    },
    components: {
      BasePanel
    },
    data () {
      return {
        address: {
          id: null,
          region: null,
          detail: null,
          phone: null,
          isDefault: false
        }
      }
    },
    methods: {
      clickRightIcon () {
        Toast('请输入街道名/小区名/道路名/门牌号\r\n例如: 신촌 포스빌 xxx호/신촌로 150 xxx호')
        // Toast(`${this.address.region} ${this.address.detail}`)
      },
      regionInput (value) {
        this.address.region = value.mp.detail
      },
      detailInput (value) {
        this.address.detail = value.mp.detail
      },
      phoneInput (value) {
        this.address.phone = value.mp.detail
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
