<template>
  <view class="container">
    <view class="gradientview"></view>
    <view class="container-contain">
      <button @click="login" class="login-btn">微信登陆</button>
    </view>
  </view>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import userService from '@/services/user'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {}
    },
    methods: {
      login () {
        wx.showLoading({
          title: '正在登陆中...'
        })
        userService.loginByWx()
            .then(() => {
              const pages = getCurrentPages() // eslint-disable-line
              let pre = null
              let index = 2
              do {
                pre = pages[pages.length - index++]
              } while (pre.route === 'pages/auth/main' && pages.length >= index)

              mpvue.reLaunch({
                url: `/${pre.route}`
              })
            })
            .finally(() => {
              wx.hideLoading()
            })
      }
    }
  }
</script>

<style scoped>
.container-contain {
  padding: 0.3rem 0.3rem;
}

.activityImg {
  width: 100%;
  height: 3rem;
  margin-bottom: 0.3rem;
}

.login-btn {
  position: fixed;
  top: 40%;
  width: calc(100% - 0.3rem * 2);
  background: #FFD200;
}
</style>
