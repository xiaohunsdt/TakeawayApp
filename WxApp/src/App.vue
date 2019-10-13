<script>
    import userService from '@/services/user'

    export default {
        created () {
            // 检测权限是否打开
            mpvue.getSetting({
                success (res) {
                    if (!res.authSetting['scope.userLocation']) {
                        wx.authorize({
                            scope: 'scope.userLocation',
                            success () {
                                console.log('userLocation 权限已经打开')
                            }
                        })
                    }
                }
            })

            // 检测登录
            userService.checkLogin()
                .catch(() => {
                    userService.loginByWx()
                        .then((res) => {
                            console.log(res)
                        })
                        .catch((res) => {
                            console.log(res)
                        })
                })
        }
    }
</script>

<style>
  ::-webkit-scrollbar {
    width: 0;
    height: 0;
    color: transparent;
  }

  page {
    height: 100%;
  }

  .container {
    background-color: #F0F0F0;
    min-height: 100%;
    width: 100%;
  }

  .container-contain {
    position: relative;
    z-index: 100;
  }

  .gradientDiv {
    position: absolute;
    background: linear-gradient(#FFD200, white, #F0F0F0);
    width: 100%;
    height: 7rem;
  }
</style>
