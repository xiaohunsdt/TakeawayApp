(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-09dac37f"],{"01b6":function(t,a,e){"use strict";var r=e("3562"),n=e.n(r);n.a},1732:function(t,a,e){"use strict";var r=e("8c23"),n=e.n(r);n.a},"178e":function(t,a,e){"use strict";var r=e("b775"),n=e("4328"),s=e.n(n);function i(){return Object(r["a"])({url:"/setting/getAllSetting",method:"get"})}function o(t,a){return Object(r["a"])({url:"/setting/getSettingByKey",method:"post",data:{key:t,scope:a},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return t=s.a.stringify(t),t}]})}function l(t){return Object(r["a"])({url:"/setting/getSettingsByScope",method:"post",data:{scope:t},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return t=s.a.stringify(t),t}]})}function c(t,a){t.scope=a;var e=t;return Object(r["a"])({url:"/setting/updateSetting",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(t){return t=s.a.stringify(t),t}]})}a["a"]={getAllSetting:i,getSettingByKey:o,getSettingsByScope:l,updateSetting:c}},"179c":function(t,a,e){"use strict";var r=e("96f3"),n=e.n(r);n.a},2895:function(t,a,e){},3562:function(t,a,e){},"555e":function(t,a,e){"use strict";var r=function(){var t=this,a=t.$createElement,e=t._self._c||a;return t.mapOptions.lat&&t.mapOptions.lng?e("naver-maps",{attrs:{height:t.height,initLayers:t.initLayers,mapOptions:t.mapOptions},on:{load:t.onLoad}},[e("map-info-window",{ref:"info-window",staticClass:"info-window",attrs:{isOpen:t.isOpen,marker:t.marker},on:{load:t.onWindowLoad}},[t.selectedOrder?e("div",{staticClass:"info-window-container"},[e("base-card",[e("div",[t._v(" "+t._s(t.selectedOrder.address.address)+" "+t._s(t.selectedOrder.address.detail)+" "),e("br"),t._v(" "+t._s(t.selectedOrder.address.phone)+" ")]),e("div",{staticStyle:{"margin-top":"8px"}},[e("el-button-group",[e("el-button",{attrs:{size:"mini",type:"primary"}},[e("a",{attrs:{href:"nmap://search?appname=http://admin.cxy.novaborn.net&query="+t.selectedOrder.address.address}},[t._v("打开地图")])]),e("el-button",{attrs:{size:"mini",type:"primary"}},[e("a",{attrs:{href:"tel:"+t.selectedOrder.address.phone}},[t._v("拨打手机")])]),e("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return t.onFinishOrder(t.selectedOrder)}}},[t._v(" 完成订单 ")])],1)],1)])],1):t._e()]),e("map-marker",{attrs:{lat:t.mapOptions.lat,lng:t.mapOptions.lng},on:{click:t.onMarkerClicked,load:t.onMarkerLoaded}}),t._l(t.orderList,(function(a){return e("map-marker",{key:a.id,attrs:{icon:{content:"<div class='pin-number'>"+a.number+"</div><div class='pin "+a.orderState+"'></div><div class='pulse'></div>"},lat:a.address.y,lng:a.address.x,order:a},on:{click:t.onMarkerClicked,load:t.onMarkerLoaded}})})),t._l(t.distancePriceArr,(function(a){return e("naver-circle",{key:a.key,attrs:{lat:t.mapOptions.lat,lng:t.mapOptions.lng,radius:a.key}})}))],2):t._e()},n=[],s=(e("d81d"),e("a9e3"),e("2909")),i=e("5c96"),o=e("a145"),l=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{ref:"content"},[t._t("default")],2)},c=[],d={name:"MapInfoWindow",props:{marker:Object,isOpen:Boolean},watch:{isOpen:function(t){t?this.infoWindow.open(this.map,this.marker):this.infoWindow.close(),this.$emit(t?"open":"close",this)}},data:function(){return{infoWindow:null,map:null}},methods:{open:function(t){this.infoWindow.open(this.map,t)},close:function(){this.infoWindow.close()}},mounted:function(){var t=this,a=function(a){console.log(a),t.map=a,t.infoWindow=new window.naver.maps.InfoWindow({content:t.$refs.content,borderWidth:0,backgroundColor:"transparent",disableAnchor:!0}),t.$emit("load",t)};window.$naverMapsLoaded?a(window.$naverMapsObject):window.$naverMapsCallback.push(a)},destroyed:function(){this.infoWindow.setMap(null)}},u=d,p=e("2877"),h=Object(p["a"])(u,l,c,!1,null,null,null),m=h.exports,f=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[t._t("default")],2)},v=[];e("4160");function g(t,a,e){naver.maps.Event.addListener(a,e,(function(a){return t.$emit(e,a)}))}function b(t,a,e,r){naver.maps.Event.addListener(a,e,(function(a){return t.$emit(e,{event:a,order:r})}))}var y={name:"MapMarker",props:{order:Object,otherOptions:Object,lat:{type:Number,required:!0},lng:{type:Number,required:!0},icon:Object},data:function(){return{marker:null,map:null}},watch:{lat:function(){this.setPosition({lng:this.lng,lat:this.lat})},lng:function(){this.setPosition({lng:this.lng,lat:this.lat})}},methods:{setClickable:function(t){return this.marker.setClickable(t),this},setCursor:function(t){return this.marker.setCursor(t),this},setDraggable:function(t){return this.marker.setDraggable(t),this},draw:function(){return this.marker.draw()},getAnimation:function(){return this.marker.getAnimation()},getClickable:function(){return this.marker.getClickable()},getCursor:function(){return this.marker.getCursor()},getDraggable:function(){return this.marker.getDraggable()},getDrawingRect:function(){return this.marker.getDrawingRect()},getIcon:function(){return this.marker.getIcon()},getOptions:function(t){return this.marker.getOptions(t)},getPosition:function(){return this.marker.getPosition()},getShape:function(){return this.marker.getShape()},getTitle:function(){return this.marker.getTitle()},getVisible:function(){return this.marker.getVisible()},getZIndex:function(){return this.marker.getZIndex()},onAdd:function(){return this.marker.onAdd()},onRemove:function(){return this.marker.onRemove()},setAnimation:function(t){return this.marker.setAnimation(naver.maps.Animation[t]),this},setIcon:function(t){return this.marker.setIcon(t),this},setOptions:function(t){return this.marker.setOptions(t),this},setPosition:function(t){return this.marker.setPosition(t),this},setShape:function(t){return this.marker.setShape(t),this},setTitle:function(t){return this.marker.setTitle(t),this},setVisible:function(t){return this.marker.setVisible(t),this},setZIndex:function(t){return this.marker.setZIndex(t),this}},mounted:function(){var t=this,a=function(a){t.map=a,t.marker=new window.naver.maps.Marker(Object.assign({position:new window.naver.maps.LatLng(t.lat,t.lng),map:a},t.otherOptions,t.icon?{icon:t.icon}:{}));var e=["mousedown","mouseup","dblclick","rightclick","mouseover","mouseout","mousemove","dragstart","drag","dragend","touchstart","touchmove","touchend","pinchstart","pinch","pinchend","tap","longtap","twofingertap","doubletap"];e.forEach((function(a){return g(t,t.marker,a)})),b(t,t.marker,"click",t.order),t.$emit("load",t)};window.$naverMapsLoaded?a(window.$naverMapsObject):window.$naverMapsCallback.push(a)},destroyed:function(){this.marker.setMap(null)}},C=y,_=Object(p["a"])(C,f,v,!1,null,null,null),w=_.exports,O=e("f8b7"),D=e("178e"),k={name:"NaverMap",components:{BaseCard:o["a"],MapInfoWindow:m,MapMarker:w},props:{height:{type:Number,default:1e3},allOrder:{type:Boolean,default:!1}},data:function(){return{selectedOrder:null,isOpen:!1,marker:null,map:null,mapOptions:{lat:null,lng:null,zoom:14,zoomControl:!0,zoomControlOptions:{position:"CENTER"},mapTypeControl:!0},distancePriceArr:[],initLayers:["BACKGROUND","BACKGROUND_DETAIL","POI_KOREAN","TRANSIT","CHINESE"],orderList:[],timer:null}},created:function(){var t=this;this.allOrder?this.getAllTodayOrderList():(this.getWaitEatOrderList(),this.timer=setInterval(this.getWaitEatOrderList,6e4)),D["a"].getSettingByKey("store_address_x","STORE").then((function(a){t.mapOptions.lng=parseFloat(a.value)})),D["a"].getSettingByKey("store_address_y","STORE").then((function(a){t.mapOptions.lat=parseFloat(a.value)})),D["a"].getSettingByKey("distance_price_arr","EXPRESS").then((function(a){var e;a&&(e=t.distancePriceArr).push.apply(e,Object(s["a"])(JSON.parse(a.value)))}))},methods:{onLoad:function(t){this.map=t},onWindowLoad:function(t){console.log(t),t.infoWindow.borderWidth=0},onMarkerClicked:function(t){console.log(this.$refs),t.order?this.selectedOrder=t.order:this.selectedOrder=null,this.marker!==t.event.overlay?(this.$refs["info-window"].close(),this.$refs["info-window"].open(t.event.overlay),this.marker=t.event.overlay,this.isOpen=!0):(this.marker=t.event.overlay,this.isOpen=!this.isOpen)},onMarkerLoaded:function(t){this.marker=t.marker},getWaitEatOrderList:function(){var t=this;O["a"].getTodayOrderListByState("WAIT_EAT").then((function(a){t.orderList=a}))},getAllTodayOrderList:function(){var t=this;O["a"].getTodayOrderList().then((function(a){t.orderList=a}))},onCopySuccess:function(){Object(i["Message"])({message:"复制成功",type:"success",duration:2e3})},onCopyError:function(){Object(i["Message"])({message:"复制失败",type:"error",duration:2e3})},onFinishOrder:function(t){var a=this;this.$confirm("确定当前订单已完成?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){O["a"].finishOrder(t.id).then((function(t){a.$message.success(t.message),a.isOpen=!1,a.allOrder?a.getAllTodayOrderList():a.getWaitEatOrderList()}))}))}},beforeDestroy:function(){clearInterval(this.timer)}},S=k,x=(e("8107"),Object(p["a"])(S,r,n,!1,null,null,null));a["a"]=x.exports},"79e3":function(t,a,e){},8107:function(t,a,e){"use strict";var r=e("b8d2"),n=e.n(r);n.a},"8c23":function(t,a,e){},9406:function(t,a,e){"use strict";e.r(a);var r=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e(t.currentRole,{tag:"component"})},n=[],s=(e("caad"),e("2532"),e("5530")),i=e("2f62"),o=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"dashboard-container"},[t.dashboardData?e("panel-group",{attrs:{dashboardData:t.dashboardData}}):t._e(),t.dashboardData?e("panel-group2",{attrs:{dashboardData:t.dashboardData}}):t._e(),e("base-card",{staticStyle:{margin:"20px 15px 0px 15px"}},[t.dashboardData?e("line-chart",{staticStyle:{"margin-top":"20px"},attrs:{dashboardData:t.dashboardData}}):t._e()],1),e("el-row",{staticStyle:{"margin-top":"30px",padding:"0 15px"},attrs:{gutter:30}},[e("el-col",{attrs:{lg:8,sm:24,xs:24}},[e("base-card",[t.dashboardData?e("pie-chart",{attrs:{dashboardData:t.dashboardData}}):t._e()],1)],1),e("el-col",{attrs:{lg:8,sm:24,xs:24}},[e("base-card",[t.dashboardData?e("bar-chart",{attrs:{dashboardData:t.dashboardData}}):t._e()],1)],1),e("el-col",{attrs:{lg:8,sm:24,xs:24}},[e("base-card",[e("order-naver-map",{attrs:{"all-order":!0,height:300}})],1)],1)],1)],1)},l=[],c=e("b775");function d(){return Object(c["a"])({url:"/getDashboardData",method:"get"})}var u={getDashboardData:d},p=e("a145"),h=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("el-row",{staticClass:"panel-group",attrs:{gutter:20}},[e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,"WEIXIN_PAY")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"wechat-pay"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.wechatOrderAllPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 订单数 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.wechatOrderAllCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,"ALI_PAY")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"alipay"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.alipayOrderAllPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 订单数 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.alipayOrderAllCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,"TRANSFER")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"transfer"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.transferOrderAllPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 订单数 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.transferOrderAllCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,"CREDIT_CARD")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"card"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.creditOrderAllPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 订单数 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.creditOrderAllCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,"CASH")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"cash"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.cashOrderAllPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 订单数 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.cashOrderAllCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel",on:{click:function(a){return t.onCardClick(null,null)}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"total"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总金额 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.allPrice,"start-val":0}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 总订单 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.allCount,"start-val":0}})],1)])])],1),e("simple-order-dialog",{ref:"simple-order-dialog"})],1)},m=[],f=e("ec1b"),v=e.n(f),g=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("el-dialog",{staticClass:"simple-order-dialog",attrs:{"close-on-click-modal":!1,"lock-scroll":!1,"modal-append-to-body":!1,title:t.title,visible:t.dialogFormVisible,size:"mini",width:"800px"},on:{"update:visible":function(a){t.dialogFormVisible=a}}},[e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:t.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""},on:{"expand-change":t.getOrderDetail}},[e("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(a){return[a.row.detail.hasOwnProperty("address")?e("div",{staticClass:"order-expand"},[e("el-table",{staticStyle:{width:"100%"},attrs:{data:a.row.detail.orderItemList,"show-header":!1,stripe:""}},[e("el-table-column",{attrs:{prop:"goodsName"}}),e("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(a){return[""!==a.row.goodsThumb?e("img",{staticStyle:{height:"30px",width:"auto"},attrs:{src:t.$VUE_APP_BASE_API+a.row.goodsThumb}}):t._e()]}}],null,!0)}),e("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(a){return[t._v(" ₩ "+t._s(a.row.goodsPrice.toLocaleString())+" ")]}}],null,!0)}),e("el-table-column",{scopedSlots:t._u([{key:"default",fn:function(a){return[t._v(" x "+t._s(a.row.goodsCount)+" ")]}}],null,!0)})],1),e("el-form",{staticClass:"order-expand-form",attrs:{"label-position":"left"}},[e("el-row",[e("el-col",{attrs:{span:12}},[e("el-form-item",{attrs:{label:"订单 ID"}},[e("span",[t._v(t._s(a.row.id))])]),e("el-form-item",{attrs:{label:"用户 ID"}},[e("span",[t._v(t._s(a.row.userId))])]),e("el-form-item",{attrs:{label:"总金额"}},[e("span",[t._v("₩ "+t._s(a.row.allPrice.toLocaleString()))])]),e("el-form-item",{attrs:{label:"优惠"}},[e("span",[t._v("₩ "+t._s(a.row.discountedPrices.toLocaleString()))]),""!=a.row.discount?e("span",[t._v("("+t._s(a.row.discount)+"折)")]):t._e()]),""!==a.row.ps?e("el-form-item",{attrs:{label:"备注"}},[e("span",[t._v(t._s(a.row.ps))])]):t._e()],1),e("el-col",{attrs:{span:12}},[e("el-form-item",{attrs:{label:"地址"}},[e("div",[e("div",[t._v(t._s(a.row.detail.address.address))]),e("div",[t._v(t._s(a.row.detail.address.detail))])])]),e("el-form-item",{attrs:{label:"联系方式"}},[e("span",[t._v(t._s(a.row.detail.address.phone))])])],1)],1)],1)],1):t._e()]}}])}),e("el-table-column",{attrs:{align:"center",label:"单号",prop:"number",width:"60"}}),e("el-table-column",{attrs:{align:"center",label:"用户"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("div",[t._v(t._s(a.row.userName))])]}}])}),e("el-table-column",{attrs:{align:"center",label:"商品数量",prop:"goodsCount",width:"80"}}),e("el-table-column",{attrs:{align:"center",label:"总金额",prop:"allPrice"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("div",[t._v("₩ "+t._s(a.row.allPrice.toLocaleString()))])]}}])}),e("el-table-column",{attrs:{align:"center",label:"实际金额"},scopedSlots:t._u([{key:"default",fn:function(a){return[e("div",[t._v("₩ "+t._s(a.row.realPrice.toLocaleString()))])]}}])}),e("el-table-column",{attrs:{align:"center",label:"支付状态"},scopedSlots:t._u([{key:"default",fn:function(a){return["PAID"===a.row.payState?e("el-tag",{attrs:{type:"success"}},[t._v(" "+t._s(t._f("payStateFormat")(a.row.payState))+" ")]):t._e(),"PAY_LATER"===a.row.payState?e("el-tag",{attrs:{type:"warning"}},[t._v(" "+t._s(t._f("payStateFormat")(a.row.payState))+" ")]):t._e(),"UN_PAY"===a.row.payState?e("el-tag",{attrs:{type:"danger"}},[t._v(" "+t._s(t._f("payStateFormat")(a.row.payState))+" ")]):t._e()]}}])}),e("el-table-column",{attrs:{align:"center",label:"订单状态"},scopedSlots:t._u([{key:"default",fn:function(a){return["FINISHED"===a.row.orderState?e("el-tag",{attrs:{type:"success"}},[t._v(" "+t._s(t._f("orderStateFormat")(a.row.orderState))+" ")]):"REFUND"===a.row.orderState?e("el-tag",{attrs:{type:"danger"}},[t._v(" "+t._s(t._f("orderStateFormat")(a.row.orderState))+" ")]):"EXPIRED"===a.row.orderState?e("el-tag",{attrs:{type:"info"}},[t._v(" "+t._s(t._f("orderStateFormat")(a.row.orderState))+" ")]):e("el-tag",[t._v(" "+t._s(t._f("orderStateFormat")(a.row.orderState))+" ")]),1===a.row.deleted?e("el-tag",{attrs:{type:"info"}},[t._v(" 已删除 ")]):t._e()]}}])}),e("el-table-column",{attrs:{align:"center",label:"创建时间",prop:"createDate"}})],1),e("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":t.page.current,"page-size":t.page.size,"page-sizes":[10,50,100],total:t.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":t.handleCurrentChange,"size-change":t.handleSizeChange}})],1)},b=[],y=(e("7db0"),e("4160"),e("d3b7"),e("159b"),e("96cf"),e("1da1")),C=e("f8b7"),_=e("ed08"),w={name:"SimpleOrderDialog",filters:{orderStateFormat:function(t){return Object(_["e"])(t)},payStateFormat:function(t){return Object(_["f"])(t)}},watch:{dialogFormVisible:function(){this.dialogFormVisible&&(this.page.current=1,this.getList())}},computed:{title:function(){var t="";return t=this.formData.orderState?Object(_["e"])(this.formData.orderState):this.formData.paymentWay?Object(_["g"])(this.formData.paymentWay):"全部",t+"的订单"}},data:function(){return{dialogFormVisible:!1,page:{current:1,size:10,total:0},formData:{orderState:null,paymentWay:null,showDelete:0},listLoading:!1,tableData:[]}},methods:{getList:function(){var t=this,a=Object(_["j"])(new Date,"{y}-{m}-{d}"),e=Object.assign({},this.formData);e.startDate=a,e.endDate=a,C["a"].getOrderListByPage(this.page,e).then((function(a){var e=a.records;e.forEach((function(t){t.detail={}})),t.tableData=e,t.page.total=parseInt(a.total)})).finally((function(){t.listLoading=!1}))},getOrderDetail:function(t,a){var e=this;return Object(y["a"])(regeneratorRuntime.mark((function r(){var n;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:if(n=a.find((function(a){return a.id===t.id})),void 0===n){r.next=4;break}return r.next=4,C["a"].getOrderDetail(t.id).then((function(t){e.$set(n,"detail",t)}));case 4:case"end":return r.stop()}}),r)})))()},openDialog:function(t,a){this.formData.orderState=t,this.formData.paymentWay=a,this.dialogFormVisible=!0},handleSizeChange:function(t){this.page.size=t,this.getList()},handleCurrentChange:function(t){this.page.current=t,this.getList()}}},O=w,D=(e("b6ae"),e("eb41"),e("2877")),k=Object(D["a"])(O,g,b,!1,null,"67f0310a",null),S=k.exports,x={props:{dashboardData:{type:Object,required:!0}},components:{CountTo:v.a,SimpleOrderDialog:S},computed:Object(s["a"])({},Object(i["b"])(["userData"])),methods:{onCardClick:function(t,a){this.$refs["simple-order-dialog"].openDialog(t,a)}}},E=x,$=(e("01b6"),Object(D["a"])(E,h,m,!1,null,"c7c820bc",null)),L=$.exports,A=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("el-row",{staticClass:"panel-group",attrs:{gutter:20}},[e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"waiting"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 待配送 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.waitDeliveryCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"delivery"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 配送中 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.deliveringCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"finish"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 已完成 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.finishCount,"start-val":0}})],1)])]),e("el-col",{staticClass:"card-panel-col",attrs:{lg:6,sm:12,xs:24}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"class-name":"card-panel-icon","icon-class":"refund"}})],1),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v(" 已退款 ")]),e("count-to",{staticClass:"card-panel-num",attrs:{duration:3200,"end-val":t.dashboardData.refundCount,"start-val":0}})],1)])])],1)],1)},j=[],R={props:{dashboardData:{type:Object,required:!0}},components:{CountTo:v.a},computed:Object(s["a"])({},Object(i["b"])(["userData"]))},z=R,P=(e("e6c8"),Object(D["a"])(z,A,j,!1,null,"4c5cff44",null)),T=P.exports,I=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{class:t.className,style:{height:t.height,width:t.width}})},N=[],W=(e("d81d"),e("313e")),B=e.n(W),F={data:function(){return{$_sidebarElm:null}},mounted:function(){this.$_initResizeEvent(),this.$_initSidebarResizeEvent()},beforeDestroy:function(){this.$_destroyResizeEvent(),this.$_destroySidebarResizeEvent()},activated:function(){this.$_initResizeEvent(),this.$_initSidebarResizeEvent()},deactivated:function(){this.$_destroyResizeEvent(),this.$_destroySidebarResizeEvent()},methods:{$_resizeHandler:function(){var t=this;return Object(_["b"])((function(){t.chart&&t.chart.resize()}),100)()},$_initResizeEvent:function(){window.addEventListener("resize",this.$_resizeHandler)},$_destroyResizeEvent:function(){window.removeEventListener("resize",this.$_resizeHandler)},$_sidebarResizeHandler:function(t){"width"===t.propertyName&&this.$_resizeHandler()},$_initSidebarResizeEvent:function(){this.$_sidebarElm=document.getElementsByClassName("sidebar-container")[0],this.$_sidebarElm&&this.$_sidebarElm.addEventListener("transitionend",this.$_sidebarResizeHandler)},$_destroySidebarResizeEvent:function(){this.$_sidebarElm&&this.$_sidebarElm.removeEventListener("transitionend",this.$_sidebarResizeHandler)}}};e("817d");var M={mixins:[F],props:{className:{type:String,default:"chart"},width:{type:String,default:"100%"},height:{type:String,default:"350px"},autoResize:{type:Boolean,default:!0},dashboardData:{type:Object,required:!0}},data:function(){return{chart:null}},watch:{chartData:{deep:!0,handler:function(t){this.setOptions(t)}}},mounted:function(){var t=this;this.$nextTick((function(){t.initChart()}))},beforeDestroy:function(){this.chart&&(this.chart.dispose(),this.chart=null)},methods:{initChart:function(){this.chart=B.a.init(this.$el,"macarons"),this.setOptions(this.dashboardData.perHourOrderCount)},setOptions:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},a=t.hours,e=t.preHourOrderCount;a=a.map((function(t){return t+"点"})),this.chart.setOption({xAxis:{data:a,boundaryGap:!1,axisTick:{show:!1}},grid:{left:10,right:10,bottom:20,top:30,containLabel:!0},tooltip:{trigger:"axis",axisPointer:{type:"cross"},padding:[5,10]},yAxis:{axisTick:{show:!1}},legend:{data:["时刻订单数"]},series:[{name:"时刻订单数",itemStyle:{normal:{color:"#FF005A",lineStyle:{color:"#FF005A",width:2}}},smooth:!0,type:"line",data:e,animationDuration:2800,animationEasing:"cubicInOut"}]})}}},H=M,V=Object(D["a"])(H,I,N,!1,null,null,null),q=V.exports,K=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{class:t.className,style:{height:t.height,width:t.width}})},G=[];e("817d");var U={mixins:[F],props:{className:{type:String,default:"chart"},width:{type:String,default:"100%"},height:{type:String,default:"300px"},dashboardData:{type:Object,required:!0}},data:function(){return{chart:null}},mounted:function(){var t=this;this.$nextTick((function(){t.initChart()}))},beforeDestroy:function(){this.chart&&(this.chart.dispose(),this.chart=null)},methods:{initChart:function(){this.chart=B.a.init(this.$el,"macarons"),this.chart.setOption({tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},legend:{left:"center",bottom:"10",data:["微信","支付宝","通帐转账","现金","刷卡"]},series:[{name:"今日支付占比",type:"pie",roseType:"radius",radius:[15,95],center:["50%","38%"],data:[{value:this.dashboardData.wechatOrderAllCount,name:"微信"},{value:this.dashboardData.alipayOrderAllCount,name:"支付宝"},{value:this.dashboardData.transferOrderAllCount,name:"通帐转账"},{value:this.dashboardData.cashOrderAllCount,name:"现金"},{value:this.dashboardData.creditOrderAllCount,name:"刷卡"}],animationEasing:"cubicInOut",animationDuration:2600}]})}}},Y=U,Z=Object(D["a"])(Y,K,G,!1,null,null,null),J=Z.exports,X=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{class:t.className,style:{height:t.height,width:t.width}})},Q=[];e("b64b");e("817d");var tt=6e3,at={mixins:[F],props:{className:{type:String,default:"chart"},width:{type:String,default:"100%"},height:{type:String,default:"300px"},dashboardData:{type:Object,required:!0}},data:function(){return{chart:null}},mounted:function(){var t=this;this.$nextTick((function(){var a=t.dashboardData.topSaleGoodsList.map((function(t){return Object.keys(t)[0]})),e=t.dashboardData.topSaleGoodsList.map((function(t){return t[Object.keys(t)[0]]}));t.initChart(a,e)}))},beforeDestroy:function(){this.chart&&(this.chart.dispose(),this.chart=null)},methods:{initChart:function(t,a){this.chart=B.a.init(this.$el,"macarons"),this.chart.setOption({tooltip:{trigger:"axis",axisPointer:{type:"line"}},grid:{top:10,left:"2%",right:"2%",bottom:"3%",containLabel:!0},xAxis:[{type:"category",data:t,axisTick:{alignWithLabel:!0}}],yAxis:[{type:"value",axisTick:{show:!1}}],series:[{name:"数量",type:"bar",stack:"vistors",barWidth:"60%",data:a,animationDuration:tt}]})}}},et=at,rt=Object(D["a"])(et,X,Q,!1,null,null,null),nt=rt.exports,st=e("555e"),it={name:"BaseDashboard",components:{BaseCard:p["a"],PanelGroup:L,PanelGroup2:T,LineChart:q,PieChart:J,BarChart:nt,OrderNaverMap:st["a"]},data:function(){return{dashboardData:null}},computed:Object(s["a"])({},Object(i["b"])(["userData"])),created:function(){var t=this;u.getDashboardData().then((function(a){t.dashboardData=a}))}},ot=it,lt=(e("1732"),Object(D["a"])(ot,o,l,!1,null,"08bd5e5e",null)),ct=lt.exports,dt=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"container-contain"},[e("el-button",{staticStyle:{"margin-bottom":"10px",float:"right"},attrs:{icon:"el-icon-switch-button",round:"",size:"small",type:"danger"},on:{click:t.logout}},[t._v(" 退出 ")]),e("base-card",{staticStyle:{clear:"both"}},[e("div",{staticClass:"dashboard-text"},[t._v("name: "+t._s(t.userData.userName))])])],1)},ut=[],pt={name:"DelivererDashboard",components:{BaseCard:p["a"]},data:function(){return{}},computed:Object(s["a"])({},Object(i["b"])(["userData"])),methods:{logout:function(){var t=this;return Object(y["a"])(regeneratorRuntime.mark((function a(){return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,t.$store.dispatch("admin/logout");case 2:t.$router.push("/login?redirect=".concat(t.$route.fullPath));case 3:case"end":return a.stop()}}),a)})))()}}},ht=pt,mt=Object(D["a"])(ht,dt,ut,!1,null,"a28189e6",null),ft=mt.exports,vt={name:"Dashboard",components:{BaseDashboard:ct,DelivererDashboard:ft},data:function(){return{currentRole:"BaseDashboard"}},computed:Object(s["a"])({},Object(i["b"])(["roles"])),created:function(){this.roles.includes("DELIVERER")&&(this.currentRole="DelivererDashboard")}},gt=vt,bt=Object(D["a"])(gt,r,n,!1,null,null,null);a["default"]=bt.exports},"96f3":function(t,a,e){},a145:function(t,a,e){"use strict";var r=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"base-card"},[t._t("default")],2)},n=[],s={name:"BaseCard"},i=s,o=(e("179c"),e("2877")),l=Object(o["a"])(i,r,n,!1,null,"9e9f1e78",null);a["a"]=l.exports},b6ae:function(t,a,e){"use strict";var r=e("e5fb"),n=e.n(r);n.a},b8d2:function(t,a,e){},e5fb:function(t,a,e){},e6c8:function(t,a,e){"use strict";var r=e("79e3"),n=e.n(r);n.a},eb41:function(t,a,e){"use strict";var r=e("2895"),n=e.n(r);n.a}}]);