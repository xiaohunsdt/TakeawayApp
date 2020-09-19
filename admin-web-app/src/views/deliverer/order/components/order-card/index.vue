<template>
  <base-card class="order-card">
    <div class="header" @click.stop="showMore=!showMore">
      <div class="number">
        <!--        <span class="appointment">-->
        <!--          <span style="font-size: .2rem">#</span>519002-->
        <!--        </span>-->
        <span class="normal">
          <span style="font-size: 1.5rem">#</span>16
        </span>
      </div>
      <div class="info">
        <div class="user-name">Jeremy.</div>
        <!--        <div class="create-date">09-12 12:20</div>-->
        <div class="appointment">
          <van-icon color="#ffd200" name="clock"/>
          <div class="create-date">09-12 12:20</div>
        </div>
      </div>
      <div class="action">
        <el-button round size="small" type="success" @click.stop="test">配送</el-button>
      </div>
    </div>
    <van-icon :name="showMore?'arrow-up':'arrow-down'" class="show-more-icon" color="#ffd200"
              @click.stop="showMore=!showMore"/>
    <div :style="{'max-height': showMore?'20rem':'0px'}" class="more-info">
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
  </base-card>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import { Icon } from 'vant'

export default {
  name: 'OrderCard',
  components: {
    BaseCard,
    [Icon.name]: Icon
  },
  data() {
    return {
      showMore: false
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
.header {
  height: 4rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
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
  transition: max-height .2s;
  font-weight: bolder;
  margin-bottom: 10px;
}

.more-info .address, .more-info .phone {
  margin-bottom: 2px;
}
</style>
