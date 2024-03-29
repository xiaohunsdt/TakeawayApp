<template>
  <base-card class="order-card">
    <div class="header" @click.stop="showMore=!showMore">
      <div class="number">
        <span v-if="order.appointmentDate===''" class="normal">
          <span style="font-size: 1.5rem">#</span>{{ order.number }}
        </span>
        <span v-else class="appointment">
          <span style="font-size: .2rem">#</span>{{ order.number }}
        </span>
      </div>
      <div class="info">
        <div class="user-name">{{ order.userName }}</div>
        <div v-if="!order.appointmentDate" class="create-date">{{ order.createDate }}</div>
        <div v-else class="appointment">
          <van-icon color="#ffd200" name="clock"/>
          <div class="create-date">{{ order.appointmentDate }}</div>
        </div>
      </div>
      <div class="action">
        <el-button
            v-if="order.orderState === 'WAITING_RECEIVE'|| order.orderState === 'PRODUCING'"
            :disabled="order.orderState === 'WAITING_RECEIVE'"
            round
            size="small"
            type="success"
            @click.stop="onDelivery">
          配送
        </el-button>
        <el-button v-if="order.orderState === 'DELIVERING'" round size="small" type="success" @click.stop="onFinish">
          完成
        </el-button>
      </div>
    </div>
    <van-icon
        :name="showMore?'arrow-up':'arrow-down'"
        class="show-more-icon"
        color="#ffd200"
        @click.stop="showMore=!showMore"/>
    <div :style="{'max-height': showMore?'20rem':'0px'}" class="more-info" @click.stop="showMore=!showMore">
      <div class="address">{{ order.address.address }}</div>
      <div class="address">{{ order.address.detail }}</div>
      <div class="phone">{{ order.address.phone }}</div>
      <el-button-group>
        <el-button size="mini" type="primary">
          <a :href="`nmap://search?appname=http://admin.cxy.novaborn.net&query=${order.address.address}`">打开地图</a>
        </el-button>
        <el-button size="mini" type="primary">
          <a :href="'tel:' + order.address.phone">拨打手机</a>
        </el-button>
      </el-button-group>
    </div>
  </base-card>
</template>

<script>
import orderService from '@a/order'

import BaseCard from '@/components/BaseCard'
import { Dialog, Icon, Notify } from 'vant'
import { mapGetters } from 'vuex'

export default {
  name: 'OrderCard',
  components: {
    BaseCard,
    [Icon.name]: Icon,
    [Notify.Component.name]: Notify.Component,
    [Dialog.Component.name]: Dialog.Component
  },
  props: {
    order: {
      type: Object,
      required: true
    }
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  },
  data() {
    return {
      showMore: false
    }
  },
  methods: {
    onDelivery() {
      orderService.deliveryOrder(this.order.id).then(res => {
        this.order.orderState = 'DELIVERING'
        this.order.deliverer = this.userData.id
        Notify({
          type: 'success',
          message: res.message,
          duration: 1500
        })
      })
    },
    onFinish() {
      Dialog.confirm({
        title: '提示',
        message: '确认订单已完成吗？'
      }).then(() => {
        orderService.finishOrder(this.order.id).then(res => {
          this.order.orderState = 'FINISHED'
          Notify({
            type: 'success',
            message: res.message,
            duration: 1000
          })
        })
      })
    }
  }
}
</script>

<style scoped>
.base-card {
  padding: .4rem .8rem
}

.header {
  /*height: 4rem;*/
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.header .number {
  font-weight: bolder;
  margin-right: .5rem;
}

.header .number .normal {
  font-size: 3rem;
}

.header .number .appointment {
  font-size: 1.2rem;
}

.header .info {
  text-align: center;
  font-weight: bolder;
}

.header .info .user-name {
  font-size: 1.2rem;
}

.header .info .create-date {
  display: inline-block;
  margin-left: 5px;
}

.show-more-icon {
  left: calc(50% - 8px);
  position: absolute;
  bottom: 4px;
}

.more-info {
  overflow: hidden;
  transition: max-height .3s;
  font-weight: bolder;
  margin-bottom: 10px;
}

.more-info .address, .more-info .phone {
  margin-bottom: 2px;
}
</style>
