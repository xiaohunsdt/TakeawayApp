<template>
  <naver-maps :height="height" :initLayers="initLayers" :mapOptions="mapOptions" @load="onLoad"
              v-if="mapOptions.lat && mapOptions.lng">
    <map-info-window
        :isOpen="isOpen"
        :marker="marker"
        @load="onWindowLoad"
        class="info-window"
        ref="info-window">
      <div class="info-window-container" v-if="selectedOrder">
        <base-card>
          <div>
            {{ selectedOrder.address.address }} {{ selectedOrder.address.detail }}
            <br/>
            {{ selectedOrder.address.phone }}
          </div>
          <div style="margin-top: 8px">
            <el-button-group>
              <!--                                <el-button-->
              <!--                                    size="mini"-->
              <!--                                    type="primary"-->
              <!--                                    v-clipboard:copy="`${selectedOrder.address.address} ${selectedOrder.address.detail}`"-->
              <!--                                    v-clipboard:error="onCopyError"-->
              <!--                                    v-clipboard:success="onCopySuccess">-->
              <!--                                    复制地址-->
              <!--                                </el-button>-->
              <el-button size="mini" type="primary">
                <a :href="`nmap://search?appname=http://admin.cxy.novaborn.net&query=${selectedOrder.address.address} ${selectedOrder.address.detail}`">打开地图</a>
              </el-button>
              <el-button size="mini" type="primary">
                <a :href="'tel:' + selectedOrder.address.phone">拨打手机</a>
              </el-button>
              <el-button @click="onFinishOrder(selectedOrder)" size="mini" type="success">
                完成订单
              </el-button>
            </el-button-group>
          </div>
        </base-card>
      </div>
    </map-info-window>
    <map-marker :lat="mapOptions.lat" :lng="mapOptions.lng" @click="onMarkerClicked" @load="onMarkerLoaded"/>
    <map-marker
        :icon="{content:`<div class='pin-number'>${order.number}</div><div class='pin ${order.orderState}'></div><div class='pulse'></div>`}"
        :key="order.id"
        :lat="order.address.y"
        :lng="order.address.x"
        :order="order"
        @click="onMarkerClicked"
        @load="onMarkerLoaded"
        v-for="order in orderList"/>
    <naver-circle :key="item.key" :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="item.key"
                  v-for="item in distancePriceArr"/>
    <!--    <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="1800"/>-->
    <!--    <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="2800"/>-->
    <!--    <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="3800"/>-->
    <!--      <naver-rectangle :south="36" :north="38" :west="126" :east="128"/>-->
    <!--      <naver-ellipse :bounds="{south:36,north:38,west:126,east:128}"/>-->
    <!--      <naver-polygon :paths="[[{lat:mapOptions.lat,lng:mapOptions.lng},{lat:38,lng:mapOptions.lng},{lat:38,lng:129},{lat:mapOptions.lat,lng:128}]]"/>-->
    <!--      <naver-polyline :path="[{lat:mapOptions.lat,lng:mapOptions.lng},{lat:38,lng:129}]"/>-->
    <!--      <naver-ground-overlay :url="'//logoproject.naver.com/img/img_about.gif'" :bounds="{south:36.7,north:36.9,west:126.5,east:127.5}"/>-->
  </naver-maps>
</template>

<script>
import { Message } from 'element-ui'
import BaseCard from '@/components/BaseCard'
import MapInfoWindow from './naver-map/MapInfoWindow'
import MapMarker from './naver-map/MapMarker'

import orderApi from '@/api/order'
import settingApi from '@/api/sys-setting'

export default {
  name: 'NaverMap',
  components: {
    BaseCard,
    MapInfoWindow,
    MapMarker
  },
  props: {
    height: {
      type: Number,
      default: 1000
    },
    allOrder: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      selectedOrder: null,
      isOpen: false,
      marker: null,
      map: null,
      mapOptions: {
        lat: null,
        lng: null,
        zoom: 14,
        zoomControl: true,
        zoomControlOptions: { position: 'CENTER' },
        mapTypeControl: true
      },
      distancePriceArr: [],
      initLayers: ['BACKGROUND', 'BACKGROUND_DETAIL', 'POI_KOREAN', 'TRANSIT', 'CHINESE'],
      orderList: [],
      timer: null
    }
  },
  created() {
    if (!this.allOrder) {
      this.getWaitEatOrderList()
      this.timer = setInterval(this.getWaitEatOrderList, 1000 * 60)
    } else {
      this.getAllTodayOrderList()
    }
    settingApi.getSettingByKey('store_address_x', 'STORE')
        .then(res => {
          this.mapOptions.lng = parseFloat(res.value)
        })
    settingApi.getSettingByKey('store_address_y', 'STORE')
        .then(res => {
          this.mapOptions.lat = parseFloat(res.value)
        })
    settingApi.getSettingByKey('distance_price_arr', 'EXPRESS')
        .then(res => {
          if (res) {
            this.distancePriceArr.push(...JSON.parse(res.value))
          }
        })
  },
  methods: {
    onLoad(vue) {
      this.map = vue
    },
    onWindowLoad(event) {
      console.log(event)
      event.infoWindow.borderWidth = 0
    },
    onMarkerClicked(event) {
      console.log(this.$refs)

      if (event.order) {
        this.selectedOrder = event.order
      } else {
        this.selectedOrder = null
      }
      // this.info = '1111111111111111111111111111111'

      if (this.marker !== event.event.overlay) {
        this.$refs['info-window'].close()
        this.$refs['info-window'].open(event.event.overlay)
        this.marker = event.event.overlay
        this.isOpen = true

      } else {
        this.marker = event.event.overlay
        this.isOpen = !this.isOpen
      }
    },
    onMarkerLoaded(event) {
      this.marker = event.marker
    },
    getWaitEatOrderList() {
      orderApi.getTodayOrderListByState('WAIT_EAT').then(res => {
        this.orderList = res
      })
    },
    getAllTodayOrderList() {
      orderApi.getTodayOrderList().then(res => {
        this.orderList = res
      })
    },
    onCopySuccess() {
      Message({
        message: '复制成功',
        type: 'success',
        duration: 2 * 1000
      })
    },
    onCopyError() {
      Message({
        message: '复制失败',
        type: 'error',
        duration: 2 * 1000
      })
    },
    onFinishOrder(order) {
      this.$confirm('确定当前订单已完成?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.finishOrder(order.id)
            .then(res => {
              this.$message({
                message: res.message,
                type: 'success'
              })
              this.isOpen = false
              if (!this.allOrder) {
                this.getWaitEatOrderList()
              } else {
                this.getAllTodayOrderList()
              }
            })
      })
    }
  },
  beforeDestroy() {
    clearInterval(this.timer)
  }
}
</script>
<style>
.info-window-container {
  width: 300px;
}

.info-window-container .base-card {
  margin-bottom: 17px;
}

.pin-number {
  position: relative;
  z-index: 100;
  font-weight: bolder;
  color: black;
  top: -4px;
  left: -5px;
}

.pin {
  width: 30px;
  height: 30px;
  -webkit-border-radius: 50% 50% 50% 0;
  border-radius: 50% 50% 50% 0;
  background: #3FAF3F;
  position: absolute;
  -webkit-transform: rotate(-45deg);
  -moz-transform: rotate(-45deg);
  -o-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
  transform: rotate(-45deg);
  left: 50%;
  top: 50%;
  margin: -20px 0 0 -20px;
  -webkit-animation-name: bounce;
  -moz-animation-name: bounce;
  -o-animation-name: bounce;
  -ms-animation-name: bounce;
  animation-name: bounce;
  -webkit-animation-fill-mode: both;
  -moz-animation-fill-mode: both;
  -o-animation-fill-mode: both;
  -ms-animation-fill-mode: both;
  animation-fill-mode: both;
  -webkit-animation-duration: 1s;
  -moz-animation-duration: 1s;
  -o-animation-duration: 1s;
  -ms-animation-duration: 1s;
  animation-duration: 1s;
}

.pin:after {
  content: '';
  width: 14px;
  height: 14px;
  margin: 8px 0 0 8px;
  background: #328932;
  position: absolute;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.pin.DELIVERING {
  background: #f4516c;
}

.pin.DELIVERING:after {
  background: #f4516c;
}

.pin.FINISHED {
  background: gray;
}

.pin.FINISHED:after {
  background: gray;
}

.pulse {
  background: rgba(0, 0, 0, 0.2);
  -webkit-border-radius: 50%;
  border-radius: 50%;
  height: 14px;
  width: 14px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin: 11px 0px 0px -12px;
  -webkit-transform: rotateX(55deg);
  -moz-transform: rotateX(55deg);
  -o-transform: rotateX(55deg);
  -ms-transform: rotateX(55deg);
  transform: rotateX(55deg);
  z-index: -2;
}

.pulse:after {
  content: "";
  -webkit-border-radius: 50%;
  border-radius: 50%;
  height: 40px;
  width: 40px;
  position: absolute;
  margin: -13px 0 0 -13px;
  -webkit-animation: pulsate 1s ease-out;
  -moz-animation: pulsate 1s ease-out;
  -o-animation: pulsate 1s ease-out;
  -ms-animation: pulsate 1s ease-out;
  animation: pulsate 1s ease-out;
  -webkit-animation-iteration-count: infinite;
  -moz-animation-iteration-count: infinite;
  -o-animation-iteration-count: infinite;
  -ms-animation-iteration-count: infinite;
  animation-iteration-count: infinite;
  opacity: 0;
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
  filter: alpha(opacity=0);
  -webkit-box-shadow: 0 0 1px 2px #89849b;
  box-shadow: 0 0 1px 2px #89849b;
  -webkit-animation-delay: 1.1s;
  -moz-animation-delay: 1.1s;
  -o-animation-delay: 1.1s;
  -ms-animation-delay: 1.1s;
  animation-delay: 1.1s;
}

@-moz-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}

@-webkit-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}

@-o-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}

@keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}

@-moz-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}

@-webkit-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}

@-o-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}

@keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}
</style>
