<template>
  <div>
    <naver-maps
      :height="height"
      :mapOptions="mapOptions"
      :initLayers="initLayers"
      @load="onLoad">
      <map-info-window
        class="info-window"
        @load="onWindowLoad"
        :isOpen="isOpen"
        :marker="marker"
        ref="info-window">
        <div class="info-window-container">
          <base-card>
            {{info}}
          </base-card>
        </div>
      </map-info-window>
      <map-marker :lat="mapOptions.lat" :lng="mapOptions.lng" @click="onMarkerClicked" @load="onMarkerLoaded"/>
      <map-marker
        v-for="order in waitEatOrders"
        :lat="order.address.y"
        :lng="order.address.x"
        :order="order"
        :key="order.id"
        :icon="{
          content:`<div class='pin-number'>${order.number}</div><div class='pin'></div><div class='pulse'></div>`
        }"
        @click="onMarkerClicked"
        @load="onMarkerLoaded"/>
      <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="800"/>
      <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="1800"/>
      <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="2800"/>
      <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="3800"/>
      <!--      <naver-rectangle :south="36" :north="38" :west="126" :east="128"/>-->
      <!--      <naver-ellipse :bounds="{south:36,north:38,west:126,east:128}"/>-->
      <!--      <naver-polygon :paths="[[{lat:mapOptions.lat,lng:mapOptions.lng},{lat:38,lng:mapOptions.lng},{lat:38,lng:129},{lat:mapOptions.lat,lng:128}]]"/>-->
      <!--      <naver-polyline :path="[{lat:mapOptions.lat,lng:mapOptions.lng},{lat:38,lng:129}]"/>-->
      <!--      <naver-ground-overlay :url="'//logoproject.naver.com/img/img_about.gif'" :bounds="{south:36.7,north:36.9,west:126.5,east:127.5}"/>-->
    </naver-maps>
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'
  import MapInfoWindow from './components/naver-map/MapInfoWindow'
  import MapMarker from './components/naver-map/MapMarker'

  import orderApi from '@/api/order'

  export default {
    name: 'NaverMap',
    components: {
      BaseCard,
      MapInfoWindow,
      MapMarker
    },
    data() {
      return {
        height: 1000,
        info: '',
        isOpen: false,
        marker: null,
        map: null,
        mapOptions: {
          lat: 37.5586305,
          lng: 126.9357696,
          zoom: 14,
          zoomControl: true,
          zoomControlOptions: {position: 'CENTER'},
          mapTypeControl: true
        },
        initLayers: ['BACKGROUND', 'BACKGROUND_DETAIL', 'POI_KOREAN', 'TRANSIT', 'CHINESE'],
        waitEatOrders: []
      }
    },
    computed: {
      hello() {
        return `Hello, World! × ${this.count}`
      }
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
        if (event.order) {
          this.info = `${event.order.address.address} ${event.order.address.detail} \r\n ${event.order.address.phone}`
        } else {
          this.info = ''
        }
        // this.info = '1111111111111111111111111111111'

        if (this.marker !== event.event.overlay) {
          this.$refs['info-window'].close()
          this.$refs['info-window'].open(event.event.overlay)
          this.marker = event.event.overlay
          this.isOpen = true
          return
        } else {
          this.marker = event.event.overlay
          this.isOpen = !this.isOpen
        }
      },
      onMarkerLoaded(event) {
        this.marker = event.marker
      }
    },
    mounted() {
      orderApi.getOrderListByState('WAIT_EAT').then(res => {
        this.waitEatOrders = res
      })
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
    color: red;
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
