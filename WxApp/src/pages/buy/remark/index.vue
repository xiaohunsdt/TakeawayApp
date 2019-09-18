<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <textarea style="min-height: 6rem" placeholder="请填写备注信息" name="textarea" auto-height maxlength="128"/>
      </base-panel>

      <base-panel>
        <div class="shortcut-text">
          快捷标签
        </div>
        <div class="shortcut-tag">
          <van-button type="primary" size="small" round>加麻</van-button>
          <van-button type="primary" size="small" round>加辣</van-button>
          <van-button type="primary" size="small" round>加酸</van-button>
          <van-button type="primary" size="small" round>少麻</van-button>
          <van-button type="primary" size="small" round>少辣</van-button>
          <van-button type="primary" size="small" round>少酸</van-button>
          <van-button type="primary" size="small" round>不要葱</van-button>
          <van-button type="primary" size="small" round>不要香菜</van-button>
        </div>
      </base-panel>

      <van-button custom-class="finish-btn" color="#FFD200" size="large" round>
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
            return {}
        },
        methods: {}
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
