<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <button @click="login" class="login-btn">微信登陆</button>
    </div>
  </div>
</template>

<script>
  import userApi from '@/services/user'
  import util from '@/utils/util'

  export default {
    data () {
      return {}
    },
    methods: {
      login () {
        mpvue.showLoading({
          title: '正在登陆中...'
        })
        userApi.loginByWx()
          .then(() => {
            mpvue.hideLoading()
            const pages = util.getPages()
            const pre = pages[pages.length - 2]
            mpvue.reLaunch({
              url: `/${pre.route}`
            })
          })
          .catch(err => {
            mpvue.hideLoading()
            console.error(err)
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
