<template>
  <div>
    <el-row :gutter="20" class="panel-group">
      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,'WEIXIN_PAY')" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="wechat-pay"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.wechatOrderAllPrice" :start-val="0"
                      class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              订单数
            </div>
            <count-to :duration="3200" :end-val="dashboardData.wechatOrderAllCount" :start-val="0"
                      class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,'ALI_PAY')" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="alipay"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.alipayOrderAllPrice" :start-val="0"
                      class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              订单数
            </div>
            <count-to :duration="3200" :end-val="dashboardData.alipayOrderAllCount" :start-val="0"
                      class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,'TRANSFER')" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="transfer"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.transferOrderAllPrice" :start-val="0"
                      class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              订单数
            </div>
            <count-to :duration="3200" :end-val="dashboardData.transferOrderAllCount" :start-val="0"
                      class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,'CREDIT_CARD')" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="card"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.creditOrderAllPrice" :start-val="0"
                      class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              订单数
            </div>
            <count-to :duration="3200" :end-val="dashboardData.creditOrderAllCount" :start-val="0"
                      class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,'CASH')" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="cash"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.cashOrderAllPrice" :start-val="0"
                      class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              订单数
            </div>
            <count-to :duration="3200" :end-val="dashboardData.cashOrderAllCount" :start-val="0"
                      class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div @click="onCardClick(null,null)" class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="total"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总金额
            </div>
            <count-to :duration="3200" :end-val="dashboardData.allPrice" :start-val="0" class="card-panel-num"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总订单
            </div>
            <count-to :duration="3200" :end-val="dashboardData.allCount" :start-val="0" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
    </el-row>
    <simple-order-dialog ref="simple-order-dialog"/>
  </div>
</template>

<script>
  import CountTo from 'vue-count-to'
  import SimpleOrderDialog from './SimpleOrderDialog'
  import { mapGetters } from 'vuex'

  export default {
    props: {
      dashboardData: {
        type: Object,
        required: true
      }
    },
    components: {
      CountTo,
      SimpleOrderDialog
    },
    computed: {
      ...mapGetters([
        'userData'
      ])
    },
    methods: {
      onCardClick(orderState, paymentWay) {
        this.$refs['simple-order-dialog'].openDialog(orderState, paymentWay)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .panel-group {
    /*margin-top: 18px;*/
    padding: 15px;

    .card-panel-col {
      margin-bottom: 15px;
    }

    .card-panel {
      height: 108px;
      cursor: pointer;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      /*color: #666;*/
      background-color: #fff;
      box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
      border-radius: 0.625rem;

      &:hover {
        .card-panel-icon-wrapper {
          color: #fff;
        }

        .icon-money {
          background: #f4516c;
        }
      }

      .icon-money {
        color: #f4516c;
      }

      .card-panel-icon-wrapper {
        float: left;
        margin: 14px 0 0 14px;
        padding: 16px;
        transition: all 0.38s ease-out;
        border-radius: 6px;
      }

      .card-panel-icon {
        float: left;
        font-size: 48px;
      }

      .card-panel-description {
        float: right;
        font-weight: bold;
        margin: 26px;
        margin-left: 0px;

        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 16px;
          margin-bottom: 12px;
        }

        .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }

  /*@media (max-width: 550px) {*/
  /*  .card-panel-description {*/
  /*    display: none;*/
  /*  }*/
  /*  .card-panel-icon-wrapper {*/
  /*    float: none !important;*/
  /*    width: 100%;*/
  /*    height: 100%;*/
  /*    margin: 0 !important;*/

  /*    .svg-icon {*/
  /*      display: block;*/
  /*      margin: 14px auto !important;*/
  /*      float: none !important;*/
  /*    }*/
  /*  }*/
  /*}*/
</style>
