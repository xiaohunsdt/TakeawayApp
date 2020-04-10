<template>
  <div>
    <naver-maps
      :height="height"
      :width="width"
      :mapOptions="mapOptions"
      :initLayers="initLayers"
      @load="onLoad">
      <naver-marker :lat="mapOptions.lat" :lng="mapOptions.lng" @load="onMarkerLoaded"/>
      <naver-marker
        v-for="order in waitEatOrders"
        :lat="order.address.y"
        :lng="order.address.x"
        :other-options="{title:order.id}"
        :key="order.id"
        @load="onMarkerLoaded(this)"/>
      <naver-circle :lat="mapOptions.lat" :lng="mapOptions.lng" :radius="500"/>
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
  // import BaseCard from '@/components/BaseCard'

  import orderApi from '@/api/order'

  export default {
    name: 'NaverMap',
    // components: {
    //   BaseCard
    // },
    data() {
      return {
        width: 1000,
        height: 1000,
        info: '',
        map: null,
        isCTT: false,
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
    methods: {
      onLoad(vue) {
        this.map = vue
      },
      onMarkerLoaded(vue) {}
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
</style>
