(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2c4d14a3"],{"178e":function(e,t,n){"use strict";var i=n("b775"),a=n("4328"),r=n.n(a);function s(){return Object(i["a"])({url:"/setting/getAllSetting",method:"get"})}function o(e,t){return Object(i["a"])({url:"/setting/getSettingByKey",method:"post",data:{key:e,scope:t},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function l(e){return Object(i["a"])({url:"/setting/getSettingsByScope",method:"post",data:{scope:e},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e,t){e.scope=t;var n=e;return Object(i["a"])({url:"/setting/updateSetting",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:n,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getAllSetting:s,getSettingByKey:o,getSettingsByScope:l,updateSetting:c}},"73f5":function(e,t,n){"use strict";var i=n("b775"),a=n("4328"),r=n.n(a);function s(e){return Object(i["a"])({url:"/store/getStoreById",method:"post",data:{storeId:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function o(){return Object(i["a"])({url:"/store/getStoreQRcode",method:"get"})}function l(e,t){var n=Object.assign({},e,t);return Object(i["a"])({url:"/store/getListByPage",method:"post",data:n,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(i["a"])({url:"/store/create",method:"post",data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(i["a"])({url:"/store/update",method:"post",data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getStoreById:s,getStoreQRcode:o,getListByPage:l,create:c,update:u}},"955b":function(e,t,n){"use strict";var i=n("db3d"),a=n.n(i);a.a},b7a2:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"container"},[n("base-card",[n("el-tabs",{attrs:{type:"card"}},[n("el-tab-pane",{attrs:{label:"系统设置"}},[n("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.systemSetting,"label-width":"120px",size:"mini"}},[n("el-form-item",{attrs:{label:"接单服务"}},[n("el-tooltip",{attrs:{content:"是否允许下单",placement:"right"}},[n("el-switch",{model:{value:e.systemSetting.service_running,callback:function(t){e.$set(e.systemSetting,"service_running",t)},expression:"systemSetting.service_running"}})],1)],1),e.systemSetting.service_running?e._e():n("el-form-item",{attrs:{label:"服务关闭提送"}},[n("el-tooltip",{attrs:{content:"当接单服务关闭时给用户看的提示信息",placement:"right"}},[n("el-input",{model:{value:e.systemSetting.service_close_notice,callback:function(t){e.$set(e.systemSetting,"service_close_notice",t)},expression:"systemSetting.service_close_notice"}})],1)],1),n("el-form-item",{attrs:{label:"自动接单"}},[n("el-tooltip",{attrs:{content:"是否允许系统自动接单",placement:"right"}},[n("el-switch",{model:{value:e.systemSetting.auto_receive_order,callback:function(t){e.$set(e.systemSetting,"auto_receive_order",t)},expression:"systemSetting.auto_receive_order"}})],1)],1),n("el-form-item",{attrs:{label:"商品页公告"}},[n("el-input",{model:{value:e.systemSetting.goods_page_notice,callback:function(t){e.$set(e.systemSetting,"goods_page_notice",t)},expression:"systemSetting.goods_page_notice"}})],1),n("el-form-item",{attrs:{label:"商品页标签"}},[n("el-tooltip",{attrs:{content:"多个标签用,分隔!例如:免费配送,快速送达,满2w送饮料",placement:"right"}},[n("el-input",{model:{value:e.systemSetting.goods_page_tags,callback:function(t){e.$set(e.systemSetting,"goods_page_tags",t)},expression:"systemSetting.goods_page_tags"}})],1)],1),n("el-form-item",[n("el-button",{attrs:{loading:e.saveLoading,type:"primary"},on:{click:function(t){return e.saveSetting("SYSTEM")}}},[e._v("保存设置")])],1)],1)],1),n("el-tab-pane",{attrs:{label:"店铺设置"}},[n("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.storeSetting,"label-width":"130px",size:"mini"}},[n("el-form-item",{attrs:{label:"店铺微信"}},[n("el-tooltip",{attrs:{content:"店铺的联系微信,方便顾客联系商家",placement:"right"}},[n("el-input",{model:{value:e.storeSetting.store_wx_account,callback:function(t){e.$set(e.storeSetting,"store_wx_account",t)},expression:"storeSetting.store_wx_account"}})],1)],1),n("el-form-item",{attrs:{label:"店铺Logo"}},[n("el-upload",{ref:"upload",attrs:{action:e.$VUE_APP_BASE_API+"/api/admin/uploadStoreLogo","before-upload":e.beforeUpload,headers:e.authHeader,multiple:!1,"on-success":e.handleUploadSuccess,"show-file-list":!1}},[""!==e.storeSetting.store_logo?n("img",{staticStyle:{height:"150px",width:"auto"},attrs:{src:e.$VUE_APP_BASE_API+e.storeSetting.store_logo}}):n("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),n("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过5M！建议上传长宽相等的正方形图片")])],1)],1),n("el-form-item",{attrs:{label:"店铺二维码",size:"small"}},[n("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.onDownloadQrCode}},[e._v("下载店铺二维码")])],1),n("el-form-item",{attrs:{label:"运营周期",size:"small"}},[n("el-checkbox-group",{attrs:{size:"mini"},model:{value:e.storeSetting.store_open_date,callback:function(t){e.$set(e.storeSetting,"store_open_date",t)},expression:"storeSetting.store_open_date"}},[n("el-checkbox-button",{attrs:{label:"2"}},[e._v("星期一")]),n("el-checkbox-button",{attrs:{label:"3"}},[e._v("星期二")]),n("el-checkbox-button",{attrs:{label:"4"}},[e._v("星期三")]),n("el-checkbox-button",{attrs:{label:"5"}},[e._v("星期四")]),n("el-checkbox-button",{attrs:{label:"6"}},[e._v("星期五")]),n("el-checkbox-button",{attrs:{label:"7"}},[e._v("星期六")]),n("el-checkbox-button",{attrs:{label:"1"}},[e._v("星期日")])],1)],1),n("el-form-item",{attrs:{label:"运营时间"}},[n("el-time-picker",{attrs:{placeholder:"选择开门时间"},model:{value:e.storeSetting.store_open_time,callback:function(t){e.$set(e.storeSetting,"store_open_time",t)},expression:"storeSetting.store_open_time"}}),e._v(" - "),n("el-time-picker",{attrs:{placeholder:"选择开门时间"},model:{value:e.storeSetting.store_close_time,callback:function(t){e.$set(e.storeSetting,"store_close_time",t)},expression:"storeSetting.store_close_time"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("STORE")}}},[e._v("保存设置")])],1)],1)],1),n("el-tab-pane",{attrs:{label:"配送设置"}},[n("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.deliverySetting,"label-width":"120px",size:"mini"}},[n("el-form-item",{attrs:{label:"最低起送价格"}},[n("el-tooltip",{attrs:{content:"最低起送价格",placement:"right"}},[n("el-input",{model:{value:e.deliverySetting.lowest_order_price,callback:function(t){e.$set(e.deliverySetting,"lowest_order_price",e._n(t))},expression:"deliverySetting.lowest_order_price"}})],1)],1),n("el-form-item",{attrs:{label:"最远配送距离"}},[n("el-tooltip",{attrs:{content:"最远配送距离，单位为米",placement:"right"}},[n("el-input",{model:{value:e.deliverySetting.max_delivery_distance,callback:function(t){e.$set(e.deliverySetting,"max_delivery_distance",e._n(t))},expression:"deliverySetting.max_delivery_distance"}})],1)],1),n("el-form-item",{attrs:{label:"配送费"}},[n("el-tooltip",{attrs:{content:"每单收取的配送费",placement:"right"}},[n("el-input",{model:{value:e.deliverySetting.delivery_price,callback:function(t){e.$set(e.deliverySetting,"delivery_price",e._n(t))},expression:"deliverySetting.delivery_price"}})],1)],1),n("el-form-item",{attrs:{label:"起送价范围设置"}},[n("el-tooltip",{attrs:{content:"距离不同,起送价格不同",placement:"right"}},[n("dynamic-input",{model:{value:e.deliverySetting.distance_price_arr,callback:function(t){e.$set(e.deliverySetting,"distance_price_arr",t)},expression:"deliverySetting.distance_price_arr"}})],1)],1),n("el-form-item",{attrs:{label:"基本配送时间"}},[n("el-tooltip",{attrs:{content:"只有一单的情况下,配送需要多长时间",placement:"right"}},[n("el-input",{model:{value:e.deliverySetting.base_express_time,callback:function(t){e.$set(e.deliverySetting,"base_express_time",e._n(t))},expression:"deliverySetting.base_express_time"}})],1)],1),n("el-form-item",{attrs:{label:"配送平均时间"}},[n("el-tooltip",{attrs:{content:"一般情况下，平均每单的配送时间",placement:"right"}},[n("el-input",{model:{value:e.deliverySetting.average_express_time,callback:function(t){e.$set(e.deliverySetting,"average_express_time",e._n(t))},expression:"deliverySetting.average_express_time"}})],1)],1),n("el-form-item",{attrs:{label:"外卖人员"}},[n("el-input",{model:{value:e.deliverySetting.deliverier_count,callback:function(t){e.$set(e.deliverySetting,"deliverier_count",e._n(t))},expression:"deliverySetting.deliverier_count"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("DELIVERY")}}},[e._v("保存设置")])],1)],1)],1),n("el-tab-pane",{attrs:{label:"支付设置"}},[n("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.paymentSetting,"label-width":"120px",size:"mini"}},[n("el-form-item",{attrs:{label:"通帐银行"}},[n("el-tooltip",{attrs:{content:"商家的收款通帐",placement:"right"}},[n("el-select",{attrs:{placeholder:"请选择银行"},model:{value:e.paymentSetting.bank,callback:function(t){e.$set(e.paymentSetting,"bank",t)},expression:"paymentSetting.bank"}},[n("el-option",{attrs:{value:"농협"}},[e._v("농협")]),n("el-option",{attrs:{value:"SC제일은행"}},[e._v("SC제일은행")]),n("el-option",{attrs:{value:"국민은행"}},[e._v("국민은행")]),n("el-option",{attrs:{value:"신한은행"}},[e._v("신한은행")]),n("el-option",{attrs:{value:"우리은행"}},[e._v("우리은행")]),n("el-option",{attrs:{value:"기업은행"}},[e._v("기업은행")]),n("el-option",{attrs:{value:"하나은행"}},[e._v("하나은행")]),n("el-option",{attrs:{value:"하나외환은행"}},[e._v("하나외환은행")]),n("el-option",{attrs:{value:"우체국"}},[e._v("우체국")]),n("el-option",{attrs:{value:"대구은행"}},[e._v("대구은행")]),n("el-option",{attrs:{value:"부산은행"}},[e._v("부산은행")]),n("el-option",{attrs:{value:"광주은행"}},[e._v("광주은행")]),n("el-option",{attrs:{value:"전북은행"}},[e._v("전북은행")]),n("el-option",{attrs:{value:"제주은행"}},[e._v("제주은행")]),n("el-option",{attrs:{value:"경남은행"}},[e._v("경남은행")]),n("el-option",{attrs:{value:"새마을금고"}},[e._v("새마을금고")]),n("el-option",{attrs:{value:"한국시티은행"}},[e._v("한국시티은행")]),n("el-option",{attrs:{value:"수협"}},[e._v("수협")]),n("el-option",{attrs:{value:"신협"}},[e._v("신협")]),n("el-option",{attrs:{value:"NH농협증권"}},[e._v("NH농협증권")]),n("el-option",{attrs:{value:"대우증권"}},[e._v("대우증권")]),n("el-option",{attrs:{value:"상호저축은행"}},[e._v("상호저축은행")]),n("el-option",{attrs:{value:"유안타증권(구 동양)"}},[e._v("유안타증권(구 동양)")]),n("el-option",{attrs:{value:"케이뱅크"}},[e._v("케이뱅크")]),n("el-option",{attrs:{value:"카카오뱅크"}},[e._v("카카오뱅크")]),n("el-option",{attrs:{value:"삼성증권"}},[e._v("삼성증권")])],1)],1)],1),n("el-form-item",{attrs:{label:"通帐账号"}},[n("el-input",{model:{value:e.paymentSetting.account,callback:function(t){e.$set(e.paymentSetting,"account",t)},expression:"paymentSetting.account"}})],1),n("el-form-item",{attrs:{label:"账号户名"}},[n("el-input",{model:{value:e.paymentSetting.accountName,callback:function(t){e.$set(e.paymentSetting,"accountName",t)},expression:"paymentSetting.accountName"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("PAYMENT")}}},[e._v("保存设置")])],1)],1)],1),n("el-tab-pane",{attrs:{label:"打印设置"}},[n("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.printerSetting,"label-width":"120px",size:"mini"}},[n("el-form-item",{attrs:{label:"设备号"}},[n("el-input",{model:{value:e.printerSetting.sn,callback:function(t){e.$set(e.printerSetting,"sn",t)},expression:"printerSetting.sn"}})],1),n("el-form-item",{attrs:{label:"语音类型"}},[n("el-select",{attrs:{placeholder:"请选择语言类型"},model:{value:e.printerSetting.voiceType,callback:function(t){e.$set(e.printerSetting,"voiceType",t)},expression:"printerSetting.voiceType"}},[n("el-option",{attrs:{label:"真人语音 (大)",value:"0"}}),n("el-option",{attrs:{label:"真人语音 (中)",value:"1"}}),n("el-option",{attrs:{label:"真人语音 (小)",value:"2"}}),n("el-option",{attrs:{label:"嘀嘀声",value:"3"}}),n("el-option",{attrs:{label:"静音",value:"4"}})],1)],1),n("el-form-item",{attrs:{label:"厨师体温"}},[n("el-input",{model:{value:e.printerSetting.temperature1,callback:function(t){e.$set(e.printerSetting,"temperature1",t)},expression:"printerSetting.temperature1"}})],1),n("el-form-item",{attrs:{label:"外卖员体温"}},[n("el-input",{model:{value:e.printerSetting.temperature2,callback:function(t){e.$set(e.printerSetting,"temperature2",t)},expression:"printerSetting.temperature2"}})],1),n("el-form-item",{attrs:{label:"老板体温"}},[n("el-input",{model:{value:e.printerSetting.temperature3,callback:function(t){e.$set(e.printerSetting,"temperature3",t)},expression:"printerSetting.temperature3"}})],1),n("el-form-item",{attrs:{label:"打包员体温"}},[n("el-input",{model:{value:e.printerSetting.temperature4,callback:function(t){e.$set(e.printerSetting,"temperature4",t)},expression:"printerSetting.temperature4"}})],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("PRINTER")}}},[e._v("保存设置")])],1)],1)],1)],1)],1)],1)},a=[],r=(n("4160"),n("a15b"),n("ace4"),n("d3b7"),n("ac1f"),n("1276"),n("5cc6"),n("9a8c"),n("a975"),n("735e"),n("c1ac"),n("d139"),n("3a7b"),n("d5d6"),n("82f8"),n("e91f"),n("60bd"),n("5f96"),n("3280"),n("3fcc"),n("ca91"),n("25a1"),n("cd26"),n("3c5d"),n("2954"),n("649e"),n("219c"),n("170b"),n("b39a"),n("72f7"),n("159b"),n("21a6")),s=n.n(r),o=n("a145"),l=n("178e"),c=n("73f5"),u=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{staticClass:"tag-view"},[e._l(e.distancePriceArr,(function(t){return n("el-tag",{key:t.key,attrs:{"disable-transitions":!1,closable:""},on:{close:function(n){return e.handleClose(t)}}},[e._v(" "+e._s(t.key)+"米以上, 起送价: ₩ "+e._s(t.value)+" ")])})),e.inputVisible?e._e():n("el-button",{staticClass:"button-new-tag",attrs:{size:"small"},on:{click:e.showInput}},[e._v("+ 添加")])],2),e.inputVisible?n("div",{staticClass:"dynamic-input"},[n("div",{staticClass:"add-operation"},[n("el-input",{staticClass:"input-with-select",staticStyle:{width:"120px","margin-right":"8px"},attrs:{placeholder:"请输入距离下限"},model:{value:e.key,callback:function(t){e.key=e._n(t)},expression:"key"}}),e._v(" : "),n("el-input",{staticClass:"input-with-select",staticStyle:{width:"120px",margin:"0px 8px"},attrs:{placeholder:"请输入起送价格"},model:{value:e.value,callback:function(t){e.value=e._n(t)},expression:"value"}}),n("el-button",{attrs:{type:"success"},on:{click:e.handleInputConfirm}},[e._v("添加")])],1)]):e._e()])},p=[],m=(n("c975"),n("a434"),n("5c96")),d={name:"DynamicInput",model:{prop:"distancePriceArr",event:"change"},props:{distancePriceArr:{type:Array,required:!0}},data:function(){return{inputVisible:!1,key:null,value:null}},methods:{handleClose:function(e){this.distancePriceArr.splice(this.distancePriceArr.indexOf(e),1)},showInput:function(){this.inputVisible=!0},handleInputConfirm:function(){if(this.key&&this.value){this.distancePriceArr.forEach((function(e){}));for(var e=0;e<this.distancePriceArr.length;e++){var t=this.distancePriceArr[e];if(t.key===this.key)return void Object(m["Message"])({message:"存在相同距离的设置! 请确认!",type:"error",duration:3e3})}this.distancePriceArr.push({key:this.key,value:this.value})}this.inputVisible=!1,this.key="",this.value="",this.distancePriceArr.sort((function(e,t){return t.key-e.key})),this.$emit("change",this.distancePriceArr)}}},g=d,v=(n("955b"),n("2877")),_=Object(v["a"])(g,u,p,!1,null,"c0d8c8a2",null),f=_.exports,b=n("5f87"),y=n("ed08"),S={name:"SysManagement",components:{BaseCard:o["a"],DynamicInput:f},data:function(){return{saveLoading:!1,systemSetting:{service_running:!0,auto_receive_order:!1,service_close_notice:"",goods_page_notice:"",goods_page_tags:""},storeSetting:{store_wx_account:null,store_logo:"",store_open_date:[],store_open_time:null,store_close_time:null},deliverySetting:{lowest_order_price:0,delivery_price:0,distance_price_arr:[],base_express_time:0,average_express_time:0,deliverier_count:0,max_delivery_distance:0},paymentSetting:{bank:null,account:null,accountName:null},printerSetting:{sn:"",voiceType:"0",temperature1:"",temperature2:"",temperature3:"",temperature4:""}}},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(b["c"])())}}},created:function(){this.init(0)},methods:{init:function(){var e=this;l["a"].getSettingsByScope("SYSTEM").then((function(t){t.forEach((function(t){"service_running"!==t.key&&"auto_receive_order"!==t.key?e.$set(e.systemSetting,t.key,t.value):e.$set(e.systemSetting,t.key,"true"===t.value)}))})),l["a"].getSettingsByScope("STORE").then((function(t){t.forEach((function(t){"store_open_date"===t.key?e.$set(e.storeSetting,t.key,t.value.split(",")):"store_open_time"===t.key||"store_close_time"===t.key?e.$set(e.storeSetting,t.key,new Date(t.value)):e.$set(e.storeSetting,t.key,t.value)}))})),l["a"].getSettingsByScope("DELIVERY").then((function(t){t.forEach((function(t){"distance_price_arr"!==t.key?e.$set(e.deliverySetting,t.key,t.value):e.$set(e.deliverySetting,t.key,JSON.parse(t.value))}))})),l["a"].getSettingsByScope("PAYMENT").then((function(t){t.forEach((function(t){e.$set(e.paymentSetting,t.key,t.value)}))})),l["a"].getSettingsByScope("PRINTER").then((function(t){t.forEach((function(t){e.$set(e.printerSetting,t.key,t.value)}))}))},saveSetting:function(e){var t,n=this;switch(e){case"SYSTEM":t=Object.assign({},this.systemSetting);break;case"STORE":t=Object.assign({},this.storeSetting),t.store_open_date=t.store_open_date.join(),t.store_open_time=Object(y["k"])(t.store_open_time,"{y}-{m}-{d} {h}:{i}:{s}"),t.store_close_time=Object(y["k"])(t.store_close_time,"{y}-{m}-{d} {h}:{i}:{s}");break;case"DELIVERY":t=Object.assign({},this.deliverySetting),t.distance_price_arr=JSON.stringify(t.distance_price_arr);break;case"PAYMENT":t=Object.assign({},this.paymentSetting);break;case"PRINTER":if(""===this.printerSetting.sn)return void this.$message.warning("请输入设备号");t=Object.assign({},this.printerSetting);break}this.saveLoading=!0,l["a"].updateSetting(t,e).then((function(e){n.$message.success(e.message)})).finally((function(){n.saveLoading=!1}))},onDownloadQrCode:function(){var e=this;c["a"].getStoreQRcode().then((function(t){s.a.saveAs(e.base64ToBlob("data:image/jpg;base64,".concat(t)),"QRcode.jpg")}))},base64ToBlob:function(e){for(var t=e.split(";base64,"),n=t[0].split(":")[1],i=window.atob(t[1]),a=i.length,r=new Uint8Array(a),s=0;s<a;++s)r[s]=i.charCodeAt(s);return new Blob([r],{type:n})},beforeUpload:function(e){var t="image/jpeg"===e.type||"image/png"===e.type,n=e.size/1024/1024<5;return t||this.$message.error("只能上传图片"),n||this.$message.error("上传头像图片大小不能超过 5MB!"),t&&n},handleUploadSuccess:function(e,t,n){var i="/upload/images/store/".concat(e.message);this.storeSetting.store_logo=i}}},h=S,k=(n("edc4"),Object(v["a"])(h,i,a,!1,null,"479bd2a0",null));t["default"]=k.exports},cb19:function(e,t,n){},db3d:function(e,t,n){},edc4:function(e,t,n){"use strict";var i=n("cb19"),a=n.n(i);a.a}}]);