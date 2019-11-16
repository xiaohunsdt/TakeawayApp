<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <textarea
          auto-height
          maxlength="128"
          name="textarea"
          placeholder="请填写备注信息"
          style="min-height: 6rem"
          v-model="inputVal"/>
      </base-panel>

      <base-panel>
        <div class="shortcut-text">
          快捷标签
        </div>
        <div class="shortcut-tag">
          <van-button @click="addPs('加麻')" round size="small" type="primary">加麻</van-button>
          <van-button @click="addPs('加辣')" round size="small" type="primary">加辣</van-button>
          <van-button @click="addPs('加酸')" round size="small" type="primary">加酸</van-button>
          <van-button @click="addPs('少麻')" round size="small" type="primary">少麻</van-button>
          <van-button @click="addPs('少辣')" round size="small" type="primary">少辣</van-button>
          <van-button @click="addPs('少酸')" round size="small" type="primary">少酸</van-button>
          <van-button @click="addPs('不要葱')" round size="small" type="primary">不要葱</van-button>
          <van-button @click="addPs('不要香菜')" round size="small" type="primary">不要香菜</van-button>
        </div>
      </base-panel>

      <van-button @click="setPs" color="#FFD200" custom-class="finish-btn" round size="large">
        完成
      </van-button>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {
        inputVal: ''
      }
    },
    onLoad () {
      this.init()
    },
    methods: {
      init () {
        this.inputVal = ''
      },
      addPs (data) {
        if (this.inputVal) {
          this.inputVal += `,${data}`
        } else {
          this.inputVal = data
        }
      },
      setPs () {
        const eventChannel = this.$mp.page.getOpenerEventChannel()
        eventChannel.emit('setPsContent', { ps: this.inputVal })
        mpvue.navigateBack()
      }
    }
  }
</script>

<style>
  .finish-btn {
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

  .shortcut-text {
    font-size: .25rem;
    color: #999999;
    margin-bottom: .3rem;
  }

  .shortcut-tag {
    display: flex;
    flex-wrap: wrap;
  }

  .shortcut-tag van-button {
    margin-right: .1rem;
    margin-bottom: .1rem;
  }
</style>
