<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" @toggleClick="toggleSideBar" class="hamburger-container"/>

    <breadcrumb class="breadcrumb-container"/>

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img class="user-avatar" src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80" />
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu class="user-dropdown" slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              Home
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span @click="logout" style="display:block;">Log Out</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { Howl, Howler } from 'howler'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import orderApi from '@/api/order'

export default {
  name: 'Navbar',
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ])
  },
  data() {
    return {
      timer1: null,
      timer2: null,
      normalOrderAudio: null,
      appointmentOrderAudio: null
    }
  },
  mounted() {
    // init normal order audio
    this.normalOrderAudio = new Howl({
      src: [require('@/assets/voice/order.mp3')],
      volume: 0.2,
      onplayerror: function() {
        alert('有新的订单,请及时处理!')
      }
    })
    this.timer1 = setInterval(this.getWaitingReceiveNormalOrderCount, 1000 * 10)

    // init appointment order audio
    this.appointmentOrderAudio = new Howl({
      src: [require('@/assets/voice/order-appointment.mp3')],
      volume: 0.2,
      onplayerror: function() {
        alert('有新的预约订单,请及时处理!')
      }
    })
    Howler.autoUnlock = true
    setTimeout(() => {
      this.timer2 = setInterval(this.getWaitingReceiveAppointmentOrderCount, 1000 * 10)
    }, 4000)
  },
  beforeDestroy() {
    clearInterval(this.timer1)
    clearInterval(this.timer2)
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('admin/logout')
      clearInterval(this.timer1)
      clearInterval(this.timer2)
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    getWaitingReceiveNormalOrderCount() {
      orderApi.getWaitingReceiveOrderCount('NORMAL')
          .then(res => {
            if (res > 0) {
              if (Howler.state === 'running' && Howler.ctx.state !== 'interrupted') {
                this.normalOrderAudio.play()
              } else {
                alert('有新的订单,请及时处理!')
              }
            }
          })
    },
    getWaitingReceiveAppointmentOrderCount() {
      orderApi.getWaitingReceiveOrderCount('APPOINTMENT')
          .then(res => {
            if (res > 0) {
              if (Howler.state === 'running' && Howler.ctx.state !== 'interrupted') {
                this.appointmentOrderAudio.play()
              } else {
                alert('有新的预约订单,请及时处理!')
              }
            }
          })
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
