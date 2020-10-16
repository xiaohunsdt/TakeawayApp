(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2560e566"],{"05e2":function(e,t,a){"use strict";var n=a("b775"),r=a("4328"),l=a.n(r);function i(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/delivery/getDeliveryListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=l.a.stringify(e),e}]})}function o(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/delivery/getMyDeliveryListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=l.a.stringify(e),e}]})}t["a"]={getDeliveryListByPage:i,getMyDeliveryListByPage:o}},"179c":function(e,t,a){"use strict";var n=a("96f3"),r=a.n(n);r.a},"96f3":function(e,t,a){},a145:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},r=[],l={name:"BaseCard"},i=l,o=(a("179c"),a("2877")),s=Object(o["a"])(i,n,r,!1,null,"9e9f1e78",null);t["a"]=s.exports},a1f2:function(e,t,a){"use strict";var n=a("eee0"),r=a.n(n);r.a},b0f1:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"配送员"}},[a("el-input",{attrs:{placeholder:"配送员的账号"},model:{value:e.formData.adminName,callback:function(t){e.$set(e.formData,"adminName",t)},expression:"formData.adminName"}})],1),a("el-form-item",{attrs:{label:"支付方式"}},[a("el-select",{attrs:{placeholder:"选择订单支付方式"},model:{value:e.formData.paymentWay,callback:function(t){e.$set(e.formData,"paymentWay",t)},expression:"formData.paymentWay"}},[a("el-option",{attrs:{value:null,label:"所有"}}),a("el-option",{attrs:{label:"账户余额",value:"BALANCE"}}),a("el-option",{attrs:{label:"通帐转帐",value:"TRANSFER"}}),a("el-option",{attrs:{label:"微信支付",value:"WEIXIN_PAY"}}),a("el-option",{attrs:{label:"支付宝支付",value:"ALI_PAY"}}),a("el-option",{attrs:{label:"刷卡支付",value:"CREDIT_CARD"}}),a("el-option",{attrs:{label:"现金支付",value:"CASH"}})],1)],1),a("el-form-item",{attrs:{label:"日期"}},[a("el-date-picker",{attrs:{"end-placeholder":"end date",format:"yyyy-MM-dd","start-placeholder":"start date",type:"daterange","value-format":"yyyy-MM-dd"},model:{value:e.formData.formDate,callback:function(t){e.$set(e.formData,"formDate",t)},expression:"formData.formDate"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{align:"center",label:"管理员",prop:"adminName"}}),a("el-table-column",{attrs:{align:"center",label:"订单编号",prop:"number"}}),a("el-table-column",{attrs:{align:"center",label:"支付方式"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",[e._v(e._s(e._f("paymentWayFormat")(t.row.paymentWay)))])]}}])}),a("el-table-column",{attrs:{align:"center",label:"金额"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.money&&t.row.money.toLocaleString())+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"订单日期",prop:"orderCreateDate",width:"100"}}),a("el-table-column",{attrs:{align:"center",label:"配送日期",prop:"createDate",width:"100"}}),a("el-table-column",{attrs:{align:"center",label:"订单完成时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"未完成"!==t.row.orderFinishMinute?"success":"warning"}},[e._v(e._s(t.row.orderFinishMinute)+" "+e._s("未完成"!==t.row.orderFinishMinute?"分钟":"")+" ")])]}}])}),a("el-table-column",{attrs:{align:"center",label:"配送完成时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",{attrs:{type:"未完成"!==t.row.deliveryFinishMinute?"success":"warning"}},[e._v(" "+e._s(t.row.deliveryFinishMinute)+" "+e._s("未完成"!==t.row.deliveryFinishMinute?" 分钟":"")+" ")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1)],1)},r=[],l=(a("d3b7"),a("a145")),i=a("05e2"),o=a("ed08"),s={name:"CommentManagement",components:{BaseCard:l["a"]},filters:{paymentWayFormat:function(e){return Object(o["g"])(e)}},data:function(){return{page:{current:1,size:15,total:0},formData:{adminName:null,paymentWay:null,formDate:[new Date,new Date]},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{onSearch:function(){this.page.current=1,this.getList()},getList:function(){var e=this;this.listLoading=!0;var t=Object.assign({},this.formData);t.startDate=Object(o["j"])(t.formDate[0],"{y}-{m}-{d}"),t.endDate=Object(o["j"])(t.formDate[1],"{y}-{m}-{d}"),i["a"].getDeliveryListByPage(this.page,t).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total)})).finally((function(){e.listLoading=!1}))},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()}}},c=s,u=(a("a1f2"),a("2877")),d=Object(u["a"])(c,n,r,!1,null,"35df50af",null);t["default"]=d.exports},eee0:function(e,t,a){}}]);