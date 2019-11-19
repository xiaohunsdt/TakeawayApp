<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <order-item
          :itemDetail="itemDetail"
          :key="itemDetail.goodsName"
          v-for="itemDetail in order.orderItemList"/>
      </base-panel>

      <base-panel>
        <div class="rate-class">
          <div class="rate-title">菜品口味:</div>
          <van-rate :value="rateData.delicious" @change="onDeliciousChange"/>
        </div>
        <div class="rate-class">
          <div class="rate-title">配送速度:</div>
          <van-rate :value="rateData.express" @change="onExpressChange"/>
        </div>
        <div class="rate-class">
          <div class="rate-title">服务态度:</div>
          <van-rate :value="rateData.service" @change="onServiceChange"/>
        </div>
      </base-panel>

      <base-panel>
        <textarea
          auto-height
          class="comment-textarea"
          maxlength="100"
          name="textarea"
          placeholder="请输入评价(不超过100字),评价内容我们会密切关注!一经采纳,我们将会通知您,并给予一定的奖励!"
          style="padding: .5rem"
          v-model="inputVal"/>
        <van-button round size="small" style="float: right;margin-top: .6rem" type="primary">提交</van-button>
      </base-panel>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'
  import orderService from '@/services/order'

  export default {
    props: {
      orderId: {
        type: Object,
        required: true
      }
    },
    components: {
      BasePanel,
      OrderItem
    },
    data () {
      return {
        order: {},
        rateData: {
          delicious: 0,
          express: 0,
          service: 0,
          comment: ''
        }
      }
    },
    onLoad (options) {
      if (options.orderId) {
        this.orderId = options.orderId
        this.init()
      }
    },
    methods: {
      init () {
        orderService.selectOrderById(this.orderId).then(res => {
          this.order = res
        })
      },
      onDeliciousChange (event) {
        this.rateData.delicious = event.mp.detail
      },
      onExpressChange (event) {
        this.rateData.express = event.mp.detail
      },
      onServiceChange (event) {
        this.rateData.service = event.mp.detail
      },
      onCommentChange (event) {
        this.rateData.comment = event.mp.detail
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }

  .rate-class {
    display: flex;
    justify-items: left;
  }

  .rate-class .rate-title {
    margin-right: .1rem;
  }

  .comment-textarea {
    min-height: 2rem;
    width: auto;
    background-color: rgba(0, 0, 0, .1);
    border-radius: .1rem
  }
</style>
