<template>
  <div>
    <el-row :gutter="20" class="panel-group">
      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="waiting"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              待配送
            </div>
            <count-to :duration="3200" :end-val="userData.money" :start-val="0" class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="delivery"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              配送中
            </div>
            <count-to :duration="3200" :end-val="userData.money" :start-val="0" class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="finish"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              已完成
            </div>
            <count-to :duration="3200" :end-val="userData.money" :start-val="0" class="card-panel-num"/>
          </div>
        </div>
      </el-col>

      <el-col :lg="6" :sm="12" :xs="24" class="card-panel-col">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon class-name="card-panel-icon" icon-class="total"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总订单
            </div>
            <count-to :duration="3200" :end-val="userData.money" :start-val="0" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import CountTo from 'vue-count-to'
  import {mapGetters} from 'vuex'

  export default {
    components: {
      CountTo
    },
    computed: {
      ...mapGetters([
        'userData'
      ])
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
          background: #34bfa3;
        }
      }

      .icon-money {
        color: #34bfa3;
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
