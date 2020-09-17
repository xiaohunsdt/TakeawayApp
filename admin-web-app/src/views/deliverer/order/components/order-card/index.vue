<template>
  <base-card class="order-card">
    <van-collapse v-model="activeNames" :border="false" :is-link="false">
      <van-collapse-item name="1">
        <template slot="title">
          <div class="number">#15</div>
          <div class="user-name">Jeremy.</div>
          <div class="create-date">09-12 12:20</div>
          <div class="appointment">
            <van-icon color="#ffd200" name="clock"/>
            <div class="create-date">09-12 12:20</div>
          </div>
          <div class="action">
            <el-button round size="mini" type="success" @click.stop="test">配送</el-button>
          </div>
        </template>
        <div class="more-info">
          <div class="address">신촌포스빌 707호</div>
          <div class="phone">01056511996</div>
          <el-button-group>
            <el-button size="mini" type="primary">
              <a :href="`nmap://search?appname=http://admin.cxy.novaborn.net&query=신촌포스빌`">打开地图</a>
            </el-button>
            <el-button size="mini" type="primary">
              <a :href="'tel:' + '01056511996'">拨打手机</a>
            </el-button>
          </el-button-group>
        </div>
      </van-collapse-item>
    </van-collapse>
  </base-card>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import { Collapse, CollapseItem, Icon } from 'vant'

export default {
  name: 'OrderCard',
  components: {
    BaseCard,
    [Icon.name]: Icon,
    [Collapse.name]: Collapse,
    [CollapseItem.name]: CollapseItem
  },
  data() {
    return {
      activeNames: ['']
    }
  },
  methods: {
    test() {
      console.log('asdsad')
    }
  }

}
</script>

<style scoped>

</style>
