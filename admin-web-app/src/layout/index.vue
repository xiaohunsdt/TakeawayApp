<template>
  <div :class="classObj" class="app-wrapper">
    <div @click="handleClickOutside" class="drawer-bg" v-if="device==='mobile'&&sidebar.opened"/>
    <sidebar class="sidebar-container"/>
    <div :class="{hasTagsView}" class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <navbar/>
        <tags-view v-if="hasTagsView"/>
      </div>
      <app-main/>
      <right-panel v-if="showSettings">
        <sys-setting/>
      </right-panel>
    </div>
  </div>
</template>

<script>
  import RightPanel from '@/components/RightPanel'
  import {AppMain, Navbar, Sidebar, SysSetting, TagsView} from './components'
  import ResizeMixin from './mixin/ResizeHandler'
  import {mapState} from 'vuex'
  import orderApi from '@/api/order'

  export default {
    name: 'Layout',
    components: {
      Navbar,
      Sidebar,
      AppMain,
      SysSetting,
      TagsView,
      RightPanel
    },
    mixins: [ResizeMixin],
    computed: {
      ...mapState({
        sidebar: state => state.app.sidebar,
        device: state => state.app.device,
        showSettings: state => state.settings.showSettings,
        hasTagsView: state => state.settings.tagsView,
        fixedHeader: state => state.settings.fixedHeader
      }),
      classObj() {
        return {
          hideSidebar: !this.sidebar.opened,
          openSidebar: this.sidebar.opened,
          withoutAnimation: this.sidebar.withoutAnimation,
          mobile: this.device === 'mobile'
        }
      }
    },
    data() {
      return {
        timer: null,
        normalOrderAudio: null,
        appointmentOrderAudio: null
      }
    },
    methods: {
      handleClickOutside() {
        this.$store.dispatch('app/closeSideBar', {withoutAnimation: false})
      },
      getWaitingReceiveNormalOrderCount() {
        orderApi.getWaitingReceiveOrderCount('NORMAL')
          .then(res => {
            if (res > 0) {
              this.normalOrderAudio.play().catch(error => {
                console.log(error.toString())
                alert('有新的订单,请及时处理!')
              })
            }
          })
      },
      getWaitingReceiveAppointmentOrderCount() {
        orderApi.getWaitingReceiveOrderCount('APPOINTMENT')
          .then(res => {
            if (res > 0) {
              this.appointmentOrderAudio.play().catch(error => {
                console.log(error.toString())
                alert('有新的预约订单,请及时处理!')
              })
            }
          })
      }
    },
    mounted() {
      // init normal order audio
      this.normalOrderAudio = new Audio()
      this.normalOrderAudio.muted = false
      this.normalOrderAudio.autoplay = false
      this.normalOrderAudio.loop = false
      this.normalOrderAudio.id = 'audio'
      this.normalOrderAudio.src = require('@/assets/voice/order.mp3')
      this.timer = setInterval(this.getWaitingReceiveNormalOrderCount, 1000 * 10)

      // init appointment order audio
      this.appointmentOrderAudio = new Audio()
      this.appointmentOrderAudio.muted = false
      this.appointmentOrderAudio.autoplay = false
      this.appointmentOrderAudio.loop = false
      this.appointmentOrderAudio.id = 'audio'
      this.appointmentOrderAudio.src = require('@/assets/voice/order-appointment.mp3')
      setTimeout(() => {
        this.timer = setInterval(this.getWaitingReceiveAppointmentOrderCount, 1000 * 10)
      }, 4000)
    },
    beforeDestroy() {
      clearInterval(this.timer)
    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
