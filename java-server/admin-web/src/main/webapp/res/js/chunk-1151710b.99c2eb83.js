(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1151710b"],{"179c":function(e,t,s){"use strict";var n=s("4ac9"),a=s.n(n);a.a},"4ac9":function(e,t,s){},6239:function(e,t,s){"use strict";var n=s("a28e"),a=s.n(n);a.a},a145:function(e,t,s){"use strict";var n=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"base-card"},[e._t("default")],2)},a=[],i={name:"BaseCard"},o=i,r=(s("179c"),s("2877")),l=Object(r["a"])(o,n,a,!1,null,"9e9f1e78",null);t["a"]=l.exports},a28e:function(e,t,s){},b7a2:function(e,t,s){"use strict";s.r(t);var n=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"container"},[s("base-card",[s("el-tabs",{attrs:{type:"card"}},[s("el-tab-pane",{attrs:{label:"系统设置"}},[s("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.systemSetting,"label-width":"120px"}},[s("el-form-item",{attrs:{label:"接单服务"}},[s("el-tooltip",{attrs:{content:"当前是否正常结单",placement:"right"}},[s("el-switch",{model:{value:e.systemSetting.service_running,callback:function(t){e.$set(e.systemSetting,"service_running",t)},expression:"systemSetting.service_running"}})],1)],1),e._v(" "),e.systemSetting.service_running?e._e():s("el-form-item",{attrs:{label:"服务关闭提送"}},[s("el-tooltip",{attrs:{content:"当接单服务关闭时给用户看的提示信息",placement:"right"}},[s("el-input",{model:{value:e.systemSetting.service_close_notice,callback:function(t){e.$set(e.systemSetting,"service_close_notice",t)},expression:"systemSetting.service_close_notice"}})],1)],1),e._v(" "),s("el-form-item",{attrs:{label:"商品页公告"}},[s("el-input",{model:{value:e.systemSetting.goods_page_notice,callback:function(t){e.$set(e.systemSetting,"goods_page_notice",t)},expression:"systemSetting.goods_page_notice"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"商品页标签"}},[s("el-tooltip",{attrs:{content:"多个标签用,分隔!例如:免费配送,快速送达,满2w送饮料",placement:"right"}},[s("el-input",{model:{value:e.systemSetting.goods_page_tags,callback:function(t){e.$set(e.systemSetting,"goods_page_tags",t)},expression:"systemSetting.goods_page_tags"}})],1)],1),e._v(" "),s("el-form-item",[s("el-button",{attrs:{loading:e.saveLoading,type:"primary"},on:{click:function(t){return e.saveSetting("SYSTEM")}}},[e._v("保存设置")])],1)],1)],1),e._v(" "),s("el-tab-pane",{attrs:{label:"店铺设置"}},[s("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.storeSetting,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"运营周期",size:"small"}},[s("el-checkbox-group",{model:{value:e.storeSetting.store_open_date,callback:function(t){e.$set(e.storeSetting,"store_open_date",t)},expression:"storeSetting.store_open_date"}},[s("el-checkbox-button",{attrs:{label:"1"}},[e._v("星期一")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"2"}},[e._v("星期二")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"3"}},[e._v("星期三")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"4"}},[e._v("星期四")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"5"}},[e._v("星期五")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"6"}},[e._v("星期六")]),e._v(" "),s("el-checkbox-button",{attrs:{label:"7"}},[e._v("星期日")])],1)],1),e._v(" "),s("el-form-item",{attrs:{label:"运营时间"}},[s("el-time-picker",{attrs:{"end-placeholder":"关门时间","is-range":"",placeholder:"选择运营时间范围","range-separator":"至","start-placeholder":"开门时间"},model:{value:e.timePickValue,callback:function(t){e.timePickValue=t},expression:"timePickValue"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"店铺地址"}},[s("el-input",{model:{value:e.storeSetting.store_address,callback:function(t){e.$set(e.storeSetting,"store_address",t)},expression:"storeSetting.store_address"}})],1),e._v(" "),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("STORE")}}},[e._v("保存设置")])],1)],1)],1),e._v(" "),s("el-tab-pane",{attrs:{label:"配送设置"}},[s("el-form",{ref:"form",staticStyle:{"max-width":"660px"},attrs:{model:e.expressSetting,"label-width":"120px"}},[s("el-form-item",{attrs:{label:"配送基本时间"}},[s("el-tooltip",{attrs:{content:"在最快的情况下配送所花的时间",placement:"right"}},[s("el-input",{model:{value:e.expressSetting.base_express_time,callback:function(t){e.$set(e.expressSetting,"base_express_time",t)},expression:"expressSetting.base_express_time"}})],1)],1),e._v(" "),s("el-form-item",{attrs:{label:"配送平均时间"}},[s("el-tooltip",{attrs:{content:"一般情况下，平均每单的配送时间",placement:"right"}},[s("el-input",{model:{value:e.expressSetting.average_express_time,callback:function(t){e.$set(e.expressSetting,"average_express_time",t)},expression:"expressSetting.average_express_time"}})],1)],1),e._v(" "),s("el-form-item",{attrs:{label:"外卖人员"}},[s("el-input",{model:{value:e.expressSetting.courier_count,callback:function(t){e.$set(e.expressSetting,"courier_count",t)},expression:"expressSetting.courier_count"}})],1),e._v(" "),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.saveSetting("EXPRESS")}}},[e._v("保存设置")])],1)],1)],1)],1)],1)],1)},a=[],i=(s("28a5"),s("ac6a"),s("a145")),o=s("b775"),r=s("4328"),l=s.n(r);function c(){return Object(o["a"])({url:"/setting/getAllSetting",method:"get"})}function u(e,t){return Object(o["a"])({url:"/setting/getSettingByName",method:"post",data:{name:e,scope:t},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=l.a.stringify(e),e}]})}function g(e){return Object(o["a"])({url:"/setting/getSettingsByScope",method:"post",data:{scope:e},headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return e=l.a.stringify(e),e}]})}function m(e,t){e.scope=t;var s=e;return Object(o["a"])({url:"/setting/updateSetting",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:s,transformRequest:[function(e){return e=l.a.stringify(e),e}]})}var p={getAllSetting:c,getSettingByName:u,getSettingsByScope:g,updateSetting:m},_={name:"SysManagement",components:{BaseCard:i["a"]},created:function(){this.init(0)},data:function(){return{saveLoading:!1,timePickValue:[new Date,new Date],systemSetting:{service_running:!0,service_close_notice:"",goods_page_notice:"",goods_page_tags:""},storeSetting:{store_open_date:[],store_open_time:[],store_close_time:[],store_address:""},expressSetting:{base_express_time:0,average_express_time:0,courier_count:0}}},methods:{init:function(){var e=this;p.getSettingsByScope("SYSTEM").then(function(t){t.forEach(function(t){"service_running"===t.key?e.$set(e.systemSetting,t.key,"true"===t.value):e.$set(e.systemSetting,t.key,t.value)})}),p.getSettingsByScope("STORE").then(function(t){t.forEach(function(t){"store_open_date"===t.key?e.$set(e.storeSetting,t.key,t.value.split(",")):("store_open_time"===t.key&&e.$set(e.timePickValue,0,new Date(t.value)),"store_close_time"===t.key&&e.$set(e.timePickValue,1,new Date(t.value)),e.$set(e.storeSetting,t.key,t.value))})}),p.getSettingsByScope("EXPRESS").then(function(t){t.forEach(function(t){e.$set(e.expressSetting,t.key,t.value)})})},saveSetting:function(e){var t,s=this;switch(e){case"SYSTEM":t=this.systemSetting;break;case"STORE":t=Object.assign({},this.storeSetting),t.store_open_date=t.store_open_date.join(),t.store_open_time=this.timePickValue[0],t.store_close_time=this.timePickValue[1];break;case"EXPRESS":t=this.expressSetting;break}this.saveLoading=!0,p.updateSetting(t,e).then(function(e){s.saveLoading=!1,s.$message({message:e.message,type:"success"})}).catch(function(e){s.saveLoading=!1})}}},d=_,S=(s("6239"),s("2877")),v=Object(S["a"])(d,n,a,!1,null,"2e7bd3c7",null);t["default"]=v.exports}}]);