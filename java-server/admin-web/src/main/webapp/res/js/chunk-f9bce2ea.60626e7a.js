(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f9bce2ea"],{"037c":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"map-container"},[n("naver-map")],1)},i=[],a=n("555e"),s={name:"OrderMap",components:{NaverMap:a["a"]}},o=s,c=(n("65f8"),n("2877")),l=Object(c["a"])(o,r,i,!1,null,"879eacd0",null);t["default"]=l.exports},"179c":function(e,t,n){"use strict";var r=n("96f3"),i=n.n(r);i.a},"39e8":function(e,t,n){},"555e":function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("naver-maps",{attrs:{height:e.height,initLayers:e.initLayers,mapOptions:e.mapOptions},on:{load:e.onLoad}},[n("map-info-window",{ref:"info-window",staticClass:"info-window",attrs:{isOpen:e.isOpen,marker:e.marker},on:{load:e.onWindowLoad}},[e.selectedOrder?n("div",{staticClass:"info-window-container"},[n("base-card",[n("div",[e._v(" "+e._s(e.selectedOrder.address.address)+" "+e._s(e.selectedOrder.address.detail)+" "),n("br"),e._v(" "+e._s(e.selectedOrder.address.phone)+" ")]),n("div",{staticStyle:{"margin-top":"8px"}},[n("el-button-group",[n("el-button",{attrs:{size:"mini",type:"primary"}},[n("a",{attrs:{href:"nmap://search?appname=http://admin.cxy.novaborn.net&query="+e.selectedOrder.address.address+" "+e.selectedOrder.address.detail}},[e._v("打开地图")])]),n("el-button",{attrs:{size:"mini",type:"primary"}},[n("a",{attrs:{href:"tel:"+e.selectedOrder.address.phone}},[e._v("拨打手机")])]),n("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(t){return e.onFinishOrder(e.selectedOrder)}}},[e._v(" 完成订单 ")])],1)],1)])],1):e._e()]),n("map-marker",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng},on:{click:e.onMarkerClicked,load:e.onMarkerLoaded}}),e._l(e.orderList,(function(t){return n("map-marker",{key:t.id,attrs:{icon:{content:"<div class='pin-number'>"+t.number+"</div><div class='pin "+t.orderState+"'></div><div class='pulse'></div>"},lat:t.address.y,lng:t.address.x,order:t},on:{click:e.onMarkerClicked,load:e.onMarkerLoaded}})})),n("naver-circle",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng,radius:800}}),n("naver-circle",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng,radius:1800}}),n("naver-circle",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng,radius:2800}}),n("naver-circle",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng,radius:3800}})],2)},i=[],a=(n("d81d"),n("a9e3"),n("5c96")),s=n("a145"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"content"},[e._t("default")],2)},c=[],l={name:"MapInfoWindow",props:{marker:Object,isOpen:Boolean},watch:{isOpen:function(e){e?this.infoWindow.open(this.map,this.marker):this.infoWindow.close(),this.$emit(e?"open":"close",this)}},data:function(){return{infoWindow:null,map:null}},methods:{open:function(e){this.infoWindow.open(this.map,e)},close:function(){this.infoWindow.close()}},mounted:function(){var e=this,t=function(t){console.log(t),e.map=t,e.infoWindow=new window.naver.maps.InfoWindow({content:e.$refs.content,borderWidth:0,backgroundColor:"transparent",disableAnchor:!0}),e.$emit("load",e)};window.$naverMapsLoaded?t(window.$naverMapsObject):window.$naverMapsCallback.push(t)},destroyed:function(){this.infoWindow.setMap(null)}},d=l,u=n("2877"),p=Object(u["a"])(d,o,c,!1,null,null,null),m=p.exports,h=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[e._t("default")],2)},f=[];n("4160");function g(e,t,n){naver.maps.Event.addListener(t,n,(function(t){return e.$emit(n,t)}))}function v(e,t,n,r){naver.maps.Event.addListener(t,n,(function(t){return e.$emit(n,{event:t,order:r})}))}var O={name:"MapMarker",props:{order:Object,otherOptions:Object,lat:{type:Number,required:!0},lng:{type:Number,required:!0},icon:Object},data:function(){return{marker:null,map:null}},watch:{lat:function(){this.setPosition({lng:this.lng,lat:this.lat})},lng:function(){this.setPosition({lng:this.lng,lat:this.lat})}},methods:{setClickable:function(e){return this.marker.setClickable(e),this},setCursor:function(e){return this.marker.setCursor(e),this},setDraggable:function(e){return this.marker.setDraggable(e),this},draw:function(){return this.marker.draw()},getAnimation:function(){return this.marker.getAnimation()},getClickable:function(){return this.marker.getClickable()},getCursor:function(){return this.marker.getCursor()},getDraggable:function(){return this.marker.getDraggable()},getDrawingRect:function(){return this.marker.getDrawingRect()},getIcon:function(){return this.marker.getIcon()},getOptions:function(e){return this.marker.getOptions(e)},getPosition:function(){return this.marker.getPosition()},getShape:function(){return this.marker.getShape()},getTitle:function(){return this.marker.getTitle()},getVisible:function(){return this.marker.getVisible()},getZIndex:function(){return this.marker.getZIndex()},onAdd:function(){return this.marker.onAdd()},onRemove:function(){return this.marker.onRemove()},setAnimation:function(e){return this.marker.setAnimation(naver.maps.Animation[e]),this},setIcon:function(e){return this.marker.setIcon(e),this},setOptions:function(e){return this.marker.setOptions(e),this},setPosition:function(e){return this.marker.setPosition(e),this},setShape:function(e){return this.marker.setShape(e),this},setTitle:function(e){return this.marker.setTitle(e),this},setVisible:function(e){return this.marker.setVisible(e),this},setZIndex:function(e){return this.marker.setZIndex(e),this}},mounted:function(){var e=this,t=function(t){e.map=t,e.marker=new window.naver.maps.Marker(Object.assign({position:new window.naver.maps.LatLng(e.lat,e.lng),map:t},e.otherOptions,e.icon?{icon:e.icon}:{}));var n=["mousedown","mouseup","dblclick","rightclick","mouseover","mouseout","mousemove","dragstart","drag","dragend","touchstart","touchmove","touchend","pinchstart","pinch","pinchend","tap","longtap","twofingertap","doubletap"];n.forEach((function(t){return g(e,e.marker,t)})),v(e,e.marker,"click",e.order),e.$emit("load",e)};window.$naverMapsLoaded?t(window.$naverMapsObject):window.$naverMapsCallback.push(t)},destroyed:function(){this.marker.setMap(null)}},k=O,w=Object(u["a"])(k,h,f,!1,null,null,null),b=w.exports,y=n("f8b7"),L={name:"NaverMap",props:{height:{type:Number,default:1e3},allOrder:{type:Boolean,default:!1}},components:{BaseCard:s["a"],MapInfoWindow:m,MapMarker:b},data:function(){return{selectedOrder:null,isOpen:!1,marker:null,map:null,mapOptions:{lat:37.5586305,lng:126.9357696,zoom:14,zoomControl:!0,zoomControlOptions:{position:"CENTER"},mapTypeControl:!0},initLayers:["BACKGROUND","BACKGROUND_DETAIL","POI_KOREAN","TRANSIT","CHINESE"],orderList:[],timer:null}},mounted:function(){this.allOrder?this.getAllTodayOrderList():(this.getWaitEatOrderList(),this.timer=setInterval(this.getWaitEatOrderList,6e4))},methods:{onLoad:function(e){this.map=e},onWindowLoad:function(e){console.log(e),e.infoWindow.borderWidth=0},onMarkerClicked:function(e){console.log(this.$refs),e.order?this.selectedOrder=e.order:this.selectedOrder=null,this.marker!==e.event.overlay?(this.$refs["info-window"].close(),this.$refs["info-window"].open(e.event.overlay),this.marker=e.event.overlay,this.isOpen=!0):(this.marker=e.event.overlay,this.isOpen=!this.isOpen)},onMarkerLoaded:function(e){this.marker=e.marker},getWaitEatOrderList:function(){var e=this;y["a"].getTodayOrderListByState("WAIT_EAT").then((function(t){e.orderList=t}))},getAllTodayOrderList:function(){var e=this;y["a"].getTodayOrderList().then((function(t){e.orderList=t}))},onCopySuccess:function(){Object(a["Message"])({message:"复制成功",type:"success",duration:2e3})},onCopyError:function(){Object(a["Message"])({message:"复制失败",type:"error",duration:2e3})},onFinishOrder:function(e){var t=this;this.$confirm("确定当前订单已完成?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){y["a"].finishOrder(e.id).then((function(e){t.$message({message:e.message,type:"success"}),t.isOpen=!1,t.allOrder?t.getAllTodayOrderList():t.getWaitEatOrderList()}))}))}},beforeDestroy:function(){clearInterval(this.timer)}},C=L,M=(n("8107"),Object(u["a"])(C,r,i,!1,null,null,null));t["a"]=M.exports},"65f8":function(e,t,n){"use strict";var r=n("39e8"),i=n.n(r);i.a},8107:function(e,t,n){"use strict";var r=n("b8d2"),i=n.n(r);i.a},"96f3":function(e,t,n){},a145:function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"base-card"},[e._t("default")],2)},i=[],a={name:"BaseCard"},s=a,o=(n("179c"),n("2877")),c=Object(o["a"])(s,r,i,!1,null,"9e9f1e78",null);t["a"]=c.exports},b8d2:function(e,t,n){}}]);