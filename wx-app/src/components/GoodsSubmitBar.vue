<template>
  <view class="goods-submit-bar">
    <slot name="top"/>

    <view class="goods-submit-bar__tip">
      <van-icon
        :name="tipIcon"
        custom-class="goods-submit-bar__tip-icon"
        size="12px"
        v-if="tipIcon"/>
      <view class="goods-submit-bar__tip-text" v-if="tip">
        {{ tip }}
      </view>
      <slot name="tip"/>
    </view>

    <view class="bar-class goods-submit-bar__bar">
      <slot/>
      <view class="goods-submit-bar__text">
        <text>{{ label || '合计：' }}</text>
        <text class="goods-submit-bar__price">
          <text class="goods-submit-bar__currency">{{ currency }}</text>
          <text class="goods-submit-bar__price-integer">{{ integerStr }}</text>
          <text>{{decimalStr}}</text>
        </text>
        <text class="goods-submit-bar__suffix-label">{{ suffixLabel }}</text>
      </view>

      <van-button
        :disabled="disabled"
        :loading="loading"
        :type="buttonType"
        @click="onSubmit"
        class="goods-submit-bar__button"
        custom-class="submit-btn"
        custom-style="width: 100%;"
        round
        v-if="userInfo">
        {{ loading ? '' : buttonText }}
      </van-button>
      <van-button
        :disabled="disabled"
        :loading="loading"
        :type="buttonType"
        @getuserinfo="getWxUserInfo"
        class="goods-submit-bar__button"
        custom-class="submit-btn"
        custom-style="width: 100%;"
        open-type="getUserInfo"
        round
        v-else>
        {{ loading ? '' : buttonText }}
      </van-button>
    </view>
  </view>
</template>

<script>
  import userService from '@/services/user'

  export default {
    name: 'GoodsSubmitBar',
    props: {
      tip: String,
      tipIcon: String,
      type: Number,
      price: Number,
      label: String,
      loading: Boolean,
      disabled: Boolean,
      buttonText: String,
      currency: {
        type: String,
        default: '¥'
      },
      buttonType: {
        type: String,
        default: 'danger'
      },
      decimalLength: {
        type: Number,
        default: 2
      },
      suffixLabel: String
    },
    computed: {
      hasPrice () {
        return (typeof this.price) === 'number'
      },
      integerStr () {
        const priceStrArr = typeof this.price === 'number' && (this.price / 100).toFixed(this.decimalLength).split('.')
        return priceStrArr && priceStrArr[0]
      },
      decimalStr () {
        const priceStrArr = typeof this.price === 'number' && (this.price / 100).toFixed(this.decimalLength).split('.')
        return this.decimalLength && priceStrArr ? `.${priceStrArr[1]}` : ''
      }
    },
    data () {
      return {
        userInfo: null
      }
    },
    onLoad () {
      // 获取用户信息
      if (mpvue.getStorageSync('userInfo')) {
        this.userInfo = mpvue.getStorageSync('userInfo')
      }
    },
    methods: {
      onSubmit (event) {
        if (this.disabled) {
          return
        }
        this.$emit('submit', event.detail)
      },
      getWxUserInfo (event) {
        if (event.mp.detail.userInfo) {
          // 将用户信息保存到服务器，保存成功后将被存储到本地
          userService.setUserInfo()
            .then(() => {
              this.userInfo = event.mp.detail.userInfo
              this.onSubmit(event)
            })
        } else {
          console.error('授权失败!!!')
        }
      }
    }
  }
</script>

<style>
  .submit-btn {
    font-weight: bolder !important;
    background-color: #FFD200 !important;
    border-color: #FFD200 !important;
  }
</style>

<style scoped>
  .goods-submit-bar {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    user-select: none;
    z-index: 100;
    background-color: #fff;
    box-shadow: 0 .1rem .5rem 0 rgba(0, 0, 0, .13);
  }

  .goods-submit-bar__tip {
    padding: .2rem;
    color: #f56723;
    font-size: .24rem;
    line-height: 1.5;
    background-color: #fff7cc;
  }

  .goods-submit-bar__tip:empty {
    display: none
  }

  .goods-submit-bar__tip-icon {
    width: .24rem;
    height: .24rem;
    margin-right: .04rem;
    vertical-align: middle;
    font-size: .24rem;
    min-width: .36rem;
  }

  .goods-submit-bar__tip-text {
    display: inline;
    vertical-align: middle
  }

  .goods-submit-bar__bar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 .32rem;
    height: 1rem;
    font-size: .28rem;
    background-color: #fff;
  }

  .goods-submit-bar__text {
    -webkit-flex: 1;
    flex: 1;
    text-align: right;
    color: #323233;
    padding-right: .48rem;
  }

  .goods-submit-bar__price {
    color: #ee0a24;
    font-size: .48rem;
    font-weight: bolder;
  }

  .goods-submit-bar__price, .goods-submit-bar__text {
    font-weight: 500;
  }

  .goods-submit-bar__price-integer {
    font-size: .4rem;
    font-family: Avenir-Heavy, PingFang SC, Helvetica Neue, Arial, sans-serif
  }

  .goods-submit-bar__currency {
    font-size: .24rem;
  }

  .goods-submit-bar__suffix-label {
    margin-left: .1rem;
  }

  .goods-submit-bar__button {
    width: 2.2rem;
    font-weight: 500;
    --button-default-height: .8rem !important;
    --button-line-height: .8rem !important;
  }

</style>
