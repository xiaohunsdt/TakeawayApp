(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c5e8bb2c"],{"178e":function(e,t,r){"use strict";var n=r("b775"),i=r("4328"),a=r.n(i);function s(){return Object(n["a"])({url:"/setting/getAllSetting",method:"get"})}function o(e,t){return Object(n["a"])({url:"/setting/getSettingByKey",method:"post",data:{key:e,scope:t},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=a.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/setting/getSettingsByScope",method:"post",data:{scope:e},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=a.a.stringify(e),e}]})}function d(e,t){e.scope=t;var r=e;return Object(n["a"])({url:"/setting/updateSetting",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:r,transformRequest:[function(e){return e=a.a.stringify(e),e}]})}t["a"]={getAllSetting:s,getSettingByKey:o,getSettingsByScope:c,updateSetting:d}},2280:function(e,t,r){"use strict";var n=r("310e"),i=r.n(n);i.a},"240c":function(e,t,r){"use strict";r.r(t);var n,i,a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container-contain"},[r("van-tabs",{attrs:{sticky:"",swipeable:""}},[r("van-pull-refresh",{on:{refresh:e.onRefresh},model:{value:e.isLoading,callback:function(t){e.isLoading=t},expression:"isLoading"}},[r("van-tab",{staticClass:"tab",style:{"min-height":e.scrollerHeight},attrs:{badge:e.willDeliveryOrderList.length,title:"待配送"}},e._l(e.willDeliveryOrderList,(function(e){return r("order-card",{key:e.id,attrs:{order:e}})})),1),r("van-tab",{staticClass:"tab",style:{"min-height":e.scrollerHeight},attrs:{badge:e.myDeliveryOrderList.length,title:"正在配送"}},e._l(e.myDeliveryOrderList,(function(e){return r("order-card",{key:e.id,attrs:{order:e,type:"delivering"}})})),1)],1)],1),r("right-panel",{attrs:{"button-icon":"el-icon-location","button-top":300,"full-screen":!0},scopedSlots:e._u([{key:"default",fn:function(t){return[r("div",{staticStyle:{"margin-top":"10px"}},[r("el-button",{staticStyle:{position:"absolute",right:"0",top:"10px"},attrs:{icon:"el-icon-refresh",round:"",size:"small",type:"primary"},on:{click:e.onRefreshMap}},[e._v(" 刷新 ")]),t.show?r("order-naver-map",{key:e.mapRefreshCount,ref:"naver-map",attrs:{"all-order":!1,height:e.mapHeight}}):e._e()],1)]}}])})],1)},s=[],o=(r("4160"),r("b0c0"),r("d3b7"),r("159b"),r("5530")),c=r("ade3"),d=(r("ab71"),r("58e6")),u=(r("bda7"),r("5e46")),l=(r("da3c"),r("0b33")),p=r("2f62"),m=r("f8b7"),h=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("base-card",{staticClass:"order-card"},[r("div",{staticClass:"header",on:{click:function(t){t.stopPropagation(),e.showMore=!e.showMore}}},[r("div",{staticClass:"number"},[""===e.order.appointmentDate?r("span",{staticClass:"normal"},[r("span",{staticStyle:{"font-size":"1.5rem"}},[e._v("#")]),e._v(e._s(e.order.number)+" ")]):r("span",{staticClass:"appointment"},[r("span",{staticStyle:{"font-size":".2rem"}},[e._v("#")]),e._v(e._s(e.order.number)+" ")])]),r("div",{staticClass:"info"},[r("div",{staticClass:"user-name"},[e._v(e._s(e.order.userName))]),""===e.order.appointmentDate?r("div",{staticClass:"create-date"},[e._v(e._s(e.order.createDate))]):r("div",{staticClass:"appointment"},[r("van-icon",{attrs:{color:"#ffd200",name:"clock"}}),r("div",{staticClass:"create-date"},[e._v(e._s(e.order.appointmentDate))])],1)]),r("div",{staticClass:"action"},["WAITING_RECEIVE"===e.order.orderState||"PRODUCING"===e.order.orderState?r("el-button",{attrs:{disabled:"WAITING_RECEIVE"===e.order.orderState,round:"",size:"small",type:"success"},on:{click:function(t){return t.stopPropagation(),e.onDelivery(t)}}},[e._v(" 配送 ")]):e._e(),"DELIVERING"===e.order.orderState?r("el-button",{attrs:{round:"",size:"small",type:"success"},on:{click:function(t){return t.stopPropagation(),e.onFinish(t)}}},[e._v(" 完成 ")]):e._e()],1)]),r("van-icon",{staticClass:"show-more-icon",attrs:{name:e.showMore?"arrow-up":"arrow-down",color:"#ffd200"},on:{click:function(t){t.stopPropagation(),e.showMore=!e.showMore}}}),r("div",{staticClass:"more-info",style:{"max-height":e.showMore?"20rem":"0px"},on:{click:function(t){t.stopPropagation(),e.showMore=!e.showMore}}},[r("div",{staticClass:"address"},[e._v(e._s(e.order.address.address))]),r("div",{staticClass:"address"},[e._v(e._s(e.order.address.detail))]),r("div",{staticClass:"phone"},[e._v(e._s(e.order.address.phone))]),r("el-button-group",[r("el-button",{attrs:{size:"mini",type:"primary"}},[r("a",{attrs:{href:"nmap://search?appname=http://admin.cxy.novaborn.net&query="+e.order.address.address}},[e._v("打开地图")])]),r("el-button",{attrs:{size:"mini",type:"primary"}},[r("a",{attrs:{href:"tel:"+e.order.address.phone}},[e._v("拨打手机")])])],1)],1)],1)},f=[],g=(r("e17f"),r("2241")),v=(r("9a83"),r("f564")),b=(r("c3a6"),r("ad06")),O=r("a145"),y={name:"OrderCard",components:(n={BaseCard:O["a"]},Object(c["a"])(n,b["a"].name,b["a"]),Object(c["a"])(n,v["a"].Component.name,v["a"].Component),Object(c["a"])(n,g["a"].Component.name,g["a"].Component),n),props:{order:{type:Object,required:!0}},computed:Object(o["a"])({},Object(p["b"])(["userData"])),data:function(){return{showMore:!1}},methods:{onDelivery:function(){var e=this;m["a"].deliveryOrder(this.order.id).then((function(t){e.order.orderState="DELIVERING",e.order.deliverer=e.userData.id,Object(v["a"])({type:"success",message:t.message,duration:1500})}))},onFinish:function(){var e=this;g["a"].confirm({title:"提示",message:"确认订单已完成吗？"}).then((function(){m["a"].finishOrder(e.order.id).then((function(t){e.order.orderState="FINISHED",Object(v["a"])({type:"success",message:t.message,duration:1e3})}))}))}}},w=y,k=(r("2280"),r("2877")),C=Object(k["a"])(w,h,f,!1,null,"13980394",null),_=C.exports,L=r("7403"),I=r("555e"),S={name:"DelivererOrderManagement",components:(i={OrderCard:_,RightPanel:L["a"],OrderNaverMap:I["a"]},Object(c["a"])(i,l["a"].name,l["a"]),Object(c["a"])(i,u["a"].name,u["a"]),Object(c["a"])(i,d["a"].name,d["a"]),i),data:function(){return{isLoading:!1,orderList:[],willDeliveryOrderList:[],myDeliveryOrderList:[],mapRefreshCount:0}},computed:Object(o["a"])(Object(o["a"])({},Object(p["b"])(["userData"])),{},{mapHeight:function(){return window.innerHeight-100},scrollerHeight:function(){return window.innerHeight-44+"px"}}),watch:{orderList:{handler:function(e){var t=this;this.willDeliveryOrderList=[],this.myDeliveryOrderList=[],e.forEach((function(e){"WAITING_RECEIVE"!==e.orderState&&"PRODUCING"!==e.orderState||t.willDeliveryOrderList.push(e),"DELIVERING"===e.orderState&&e.deliverer&&e.deliverer===t.userData.id&&t.myDeliveryOrderList.push(e)}))},deep:!0}},created:function(){this.getOrderList()},methods:{getOrderList:function(){var e=this;m["a"].getTodayOrderListByState("WAIT_EAT").then((function(t){e.orderList=t})).finally((function(){e.isLoading=!1}))},onRefresh:function(){this.getOrderList()},onRefreshMap:function(){this.mapRefreshCount++,this.mapRefreshCount>1e5&&(this.mapRefreshCount=1)}}},E=S,j=(r("bd31"),r("72f8"),Object(k["a"])(E,a,s,!1,null,"bc8c78da",null));t["default"]=j.exports},"310e":function(e,t,r){},"555e":function(e,t,r){"use strict";var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return e.mapOptions.lat&&e.mapOptions.lng?r("naver-maps",{attrs:{height:e.height,initLayers:e.initLayers,mapOptions:e.mapOptions},on:{load:e.onLoad}},[r("map-info-window",{ref:"info-window",staticClass:"info-window",attrs:{isOpen:e.isOpen,marker:e.marker},on:{load:e.onWindowLoad}},[e.selectedOrder?r("div",{staticClass:"info-window-container"},[r("base-card",[r("div",[e._v(" "+e._s(e.selectedOrder.address.address)+" "+e._s(e.selectedOrder.address.detail)+" "),r("br"),e._v(" "+e._s(e.selectedOrder.address.phone)+" ")]),r("div",{staticStyle:{"margin-top":"8px"}},[r("el-button-group",[r("el-button",{attrs:{size:"mini",type:"primary"}},[r("a",{attrs:{href:"nmap://search?appname=http://admin.cxy.novaborn.net&query="+e.selectedOrder.address.address}},[e._v("打开地图")])]),r("el-button",{attrs:{size:"mini",type:"primary"}},[r("a",{attrs:{href:"tel:"+e.selectedOrder.address.phone}},[e._v("拨打手机")])]),r("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(t){return e.onFinishOrder(e.selectedOrder)}}},[e._v(" 完成订单 ")])],1)],1)])],1):e._e()]),r("map-marker",{attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng},on:{click:e.onMarkerClicked,load:e.onMarkerLoaded}}),e._l(e.orderList,(function(t){return r("map-marker",{key:t.id,attrs:{icon:{content:"<div class='pin-number'>"+t.number+"</div><div class='pin "+t.orderState+"'></div><div class='pulse'></div>"},lat:t.address.y,lng:t.address.x,order:t},on:{click:e.onMarkerClicked,load:e.onMarkerLoaded}})})),e._l(e.distancePriceArr,(function(t){return r("naver-circle",{key:t.key,attrs:{lat:e.mapOptions.lat,lng:e.mapOptions.lng,radius:t.key}})}))],2):e._e()},i=[],a=(r("d81d"),r("a9e3"),r("2909")),s=r("5530"),o=r("5c96"),c=r("a145"),d=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{ref:"content"},[e._t("default")],2)},u=[],l={name:"MapInfoWindow",props:{marker:Object,isOpen:Boolean},watch:{isOpen:function(e){e?this.infoWindow.open(this.map,this.marker):this.infoWindow.close(),this.$emit(e?"open":"close",this)}},data:function(){return{infoWindow:null,map:null}},methods:{open:function(e){this.infoWindow.open(this.map,e)},close:function(){this.infoWindow.close()}},mounted:function(){var e=this,t=function(t){console.log(t),e.map=t,e.infoWindow=new window.naver.maps.InfoWindow({content:e.$refs.content,borderWidth:0,backgroundColor:"transparent",disableAnchor:!0}),e.$emit("load",e)};window.$naverMapsLoaded?t(window.$naverMapsObject):window.$naverMapsCallback.push(t)},destroyed:function(){this.infoWindow.setMap(null)}},p=l,m=r("2877"),h=Object(m["a"])(p,d,u,!1,null,null,null),f=h.exports,g=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[e._t("default")],2)},v=[];r("4160");function b(e,t,r){naver.maps.Event.addListener(t,r,(function(t){return e.$emit(r,t)}))}function O(e,t,r,n){naver.maps.Event.addListener(t,r,(function(t){return e.$emit(r,{event:t,order:n})}))}var y={name:"MapMarker",props:{order:Object,otherOptions:Object,lat:{type:Number,required:!0},lng:{type:Number,required:!0},icon:Object},data:function(){return{marker:null,map:null}},watch:{lat:function(){this.setPosition({lng:this.lng,lat:this.lat})},lng:function(){this.setPosition({lng:this.lng,lat:this.lat})}},methods:{setClickable:function(e){return this.marker.setClickable(e),this},setCursor:function(e){return this.marker.setCursor(e),this},setDraggable:function(e){return this.marker.setDraggable(e),this},draw:function(){return this.marker.draw()},getAnimation:function(){return this.marker.getAnimation()},getClickable:function(){return this.marker.getClickable()},getCursor:function(){return this.marker.getCursor()},getDraggable:function(){return this.marker.getDraggable()},getDrawingRect:function(){return this.marker.getDrawingRect()},getIcon:function(){return this.marker.getIcon()},getOptions:function(e){return this.marker.getOptions(e)},getPosition:function(){return this.marker.getPosition()},getShape:function(){return this.marker.getShape()},getTitle:function(){return this.marker.getTitle()},getVisible:function(){return this.marker.getVisible()},getZIndex:function(){return this.marker.getZIndex()},onAdd:function(){return this.marker.onAdd()},onRemove:function(){return this.marker.onRemove()},setAnimation:function(e){return this.marker.setAnimation(naver.maps.Animation[e]),this},setIcon:function(e){return this.marker.setIcon(e),this},setOptions:function(e){return this.marker.setOptions(e),this},setPosition:function(e){return this.marker.setPosition(e),this},setShape:function(e){return this.marker.setShape(e),this},setTitle:function(e){return this.marker.setTitle(e),this},setVisible:function(e){return this.marker.setVisible(e),this},setZIndex:function(e){return this.marker.setZIndex(e),this}},mounted:function(){var e=this,t=function(t){e.map=t,e.marker=new window.naver.maps.Marker(Object.assign({position:new window.naver.maps.LatLng(e.lat,e.lng),map:t},e.otherOptions,e.icon?{icon:e.icon}:{}));var r=["mousedown","mouseup","dblclick","rightclick","mouseover","mouseout","mousemove","dragstart","drag","dragend","touchstart","touchmove","touchend","pinchstart","pinch","pinchend","tap","longtap","twofingertap","doubletap"];r.forEach((function(t){return b(e,e.marker,t)})),O(e,e.marker,"click",e.order),e.$emit("load",e)};window.$naverMapsLoaded?t(window.$naverMapsObject):window.$naverMapsCallback.push(t)},destroyed:function(){this.marker.setMap(null)}},w=y,k=Object(m["a"])(w,g,v,!1,null,null,null),C=k.exports,_=r("f8b7"),L=r("178e"),I=r("73f5"),S=r("2f62"),E={name:"NaverMap",components:{BaseCard:c["a"],MapInfoWindow:f,MapMarker:C},props:{height:{type:Number,default:1e3},allOrder:{type:Boolean,default:!1}},data:function(){return{selectedOrder:null,isOpen:!1,marker:null,map:null,mapOptions:{lat:null,lng:null,zoom:14,zoomControl:!0,zoomControlOptions:{position:"CENTER"},mapTypeControl:!0},distancePriceArr:[],initLayers:["BACKGROUND","BACKGROUND_DETAIL","POI_KOREAN","TRANSIT","CHINESE"],orderList:[],timer:null}},computed:Object(s["a"])({},Object(S["b"])(["userData"])),created:function(){var e=this;this.allOrder?this.getAllTodayOrderList():(this.getWaitEatOrderList(),this.timer=setInterval(this.getWaitEatOrderList,6e4)),I["a"].getStoreById(this.userData.storeId).then((function(t){e.mapOptions.lng=t.x,e.mapOptions.lat=t.y})),L["a"].getSettingByKey("distance_price_arr","DELIVERY").then((function(t){var r;t&&(r=e.distancePriceArr).push.apply(r,Object(a["a"])(JSON.parse(t.value)))})),L["a"].getSettingByKey("max_delivery_distance","DELIVERY").then((function(t){t&&e.distancePriceArr.push({key:parseInt(t.value)})}))},methods:{onLoad:function(e){this.map=e},onWindowLoad:function(e){console.log(e),e.infoWindow.borderWidth=0},onMarkerClicked:function(e){console.log(this.$refs),e.order?this.selectedOrder=e.order:this.selectedOrder=null,this.marker!==e.event.overlay?(this.$refs["info-window"].close(),this.$refs["info-window"].open(e.event.overlay),this.marker=e.event.overlay,this.isOpen=!0):(this.marker=e.event.overlay,this.isOpen=!this.isOpen)},onMarkerLoaded:function(e){this.marker=e.marker},getWaitEatOrderList:function(){var e=this;_["a"].getTodayOrderListByState("WAIT_EAT").then((function(t){e.orderList=t}))},getAllTodayOrderList:function(){var e=this;_["a"].getTodayOrderList().then((function(t){e.orderList=t}))},onCopySuccess:function(){Object(o["Message"])({message:"复制成功",type:"success",duration:2e3})},onCopyError:function(){Object(o["Message"])({message:"复制失败",type:"error",duration:2e3})},onFinishOrder:function(e){var t=this;this.$confirm("确定当前订单已完成?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){_["a"].finishOrder(e.id).then((function(e){t.$message.success(e.message),t.isOpen=!1,t.allOrder?t.getAllTodayOrderList():t.getWaitEatOrderList()}))}))}},beforeDestroy:function(){clearInterval(this.timer)}},j=E,D=(r("8107"),Object(m["a"])(j,n,i,!1,null,null,null));t["a"]=D.exports},"72f8":function(e,t,r){"use strict";var n=r("8063"),i=r.n(n);i.a},"73c8":function(e,t,r){},"73f5":function(e,t,r){"use strict";var n=r("b775"),i=r("4328"),a=r.n(i);function s(e){return Object(n["a"])({url:"/store/getStoreById",method:"post",data:{storeId:e},transformRequest:[function(e){return e=a.a.stringify(e),e}]})}function o(){return Object(n["a"])({url:"/store/getStoreQRcode",method:"get"})}function c(e,t){var r=Object.assign({},e,t);return Object(n["a"])({url:"/store/getListByPage",method:"post",data:r,transformRequest:[function(e){return e=a.a.stringify(e),e}]})}function d(e){return Object(n["a"])({url:"/store/create",method:"post",data:e,transformRequest:[function(e){return e=a.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/store/update",method:"post",data:e,transformRequest:[function(e){return e=a.a.stringify(e),e}]})}t["a"]={getStoreById:s,getStoreQRcode:o,getListByPage:c,create:d,update:u}},8063:function(e,t,r){},8107:function(e,t,r){"use strict";var n=r("b8d2"),i=r.n(n);i.a},b8d2:function(e,t,r){},bd31:function(e,t,r){"use strict";var n=r("73c8"),i=r.n(n);i.a}}]);