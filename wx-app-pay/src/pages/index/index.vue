<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">诺本网络 <br/> 欢迎您</div>
      <div>
        <div style="padding: 1rem">公司公告</div>
        <div :key="item.title" class="notice-list" v-for="item in dataList">
          <div>{{item.title}}</div>
          <div>{{item.content}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'

  export default {
    components: {
      BasePanel
    },
    onShow () {
      console.log(Promise.prototype)
      console.log(this.dataList[0].title)
    },
    data () {
      return {
        dataList: [
          {
            title: '公司简介',
            content: '南京诺本网络科技有限公司办公室地址位于六朝古都、江苏省会南京.于2016年08月02日在南京市建邺区市场监督管理局注册成立，注册资本为100万元人民币，在公司发展壮大的3年里，我们始终为客户提供好的产品和技术支持、健全的售后服务，我公司主要经营计算机软硬件销售、技术研发、技术咨询、技术转让；网站建设；自营和代理各类商品和技术的进出口业务(国家限定企业经营或禁止进出口的商品和技术除外)。（依法须经批准的项目，经相关部门批准后方可开展经营活动）我们有好的产品和专业的销售和技术团队，我公司属于南京计算机网络设备厂黄页行业，如果您对我公司的产品服务有兴趣，期待您在线留言或者来电咨询'
          },
          {
            title: '新品上市',
            content: '南京诺本网络科技有限公司开发的点餐小程序已经上线!详情请联系客服'
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped>
  #header {
    font-size: 1rem;
    text-align: center;
  }

  .notice-list {
    position: relative;
    margin: 0.25rem;
    padding: 0.25rem;
    background-color: white;
    border-radius: 0.2rem;
    overflow: hidden;
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
  }
</style>
