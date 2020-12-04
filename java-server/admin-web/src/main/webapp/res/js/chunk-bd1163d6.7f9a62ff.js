(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-bd1163d6"],{"634a":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-form",{staticClass:"demo-form-inline",staticStyle:{"max-width":"1280px"},attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{attrs:{placeholder:"请输入昵称"},model:{value:e.formData.nickName,callback:function(t){e.$set(e.formData,"nickName",t)},expression:"formData.nickName"}})],1),a("el-form-item",{attrs:{label:"订单ID"}},[a("el-input",{attrs:{placeholder:"请输入订单ID"},model:{value:e.formData.orderId,callback:function(t){e.$set(e.formData,"orderId",t)},expression:"formData.orderId"}})],1),a("el-form-item",{attrs:{label:"单号"}},[a("el-input",{attrs:{placeholder:"请输入单号"},model:{value:e.formData.number,callback:function(t){e.$set(e.formData,"number",t)},expression:"formData.number"}})],1),a("el-form-item",{attrs:{label:"支付方式"}},[a("el-select",{attrs:{placeholder:"选择支付方式"},model:{value:e.formData.paymentWay,callback:function(t){e.$set(e.formData,"paymentWay",t)},expression:"formData.paymentWay"}},[a("el-option",{attrs:{value:null,label:"所有"}}),a("el-option",{attrs:{label:"账户余额",value:"BALANCE"}}),a("el-option",{attrs:{label:"通帐转帐",value:"TRANSFER"}}),a("el-option",{attrs:{label:"微信支付",value:"WEIXIN_PAY"}}),a("el-option",{attrs:{label:"支付宝支付",value:"ALI_PAY"}}),a("el-option",{attrs:{label:"刷卡支付",value:"CREDIT_CARD"}}),a("el-option",{attrs:{label:"现金支付",value:"CASH"}})],1)],1),a("el-form-item",{attrs:{label:"订单状态"}},[a("el-select",{attrs:{placeholder:"选择订单状态"},model:{value:e.formData.orderState,callback:function(t){e.$set(e.formData,"orderState",t)},expression:"formData.orderState"}},[a("el-option",{attrs:{value:null,label:"所有"}}),a("el-option",{attrs:{label:"等待接单",value:"WAITING_RECEIVE"}}),a("el-option",{attrs:{label:"生产中",value:"PRODUCING"}}),a("el-option",{attrs:{label:"配送中",value:"DELIVERING"}}),a("el-option",{attrs:{label:"已完成",value:"FINISHED"}}),a("el-option",{attrs:{label:"退款",value:"REFUND"}}),a("el-option",{attrs:{label:"过期",value:"EXPIRED"}})],1)],1),a("el-form-item",{attrs:{label:"显示删除"}},[a("el-select",{attrs:{placeholder:"是否显示删除的订单"},model:{value:e.formData.showDelete,callback:function(t){e.$set(e.formData,"showDelete",t)},expression:"formData.showDelete"}},[a("el-option",{attrs:{value:0,label:"不显示"}}),a("el-option",{attrs:{value:1,label:"显示"}})],1)],1),a("el-form-item",{attrs:{label:"日期"}},[a("el-date-picker",{attrs:{"end-placeholder":"end date",format:"yyyy-MM-dd","start-placeholder":"start date",type:"daterange","value-format":"yyyy-MM-dd"},model:{value:e.formData.formDate,callback:function(t){e.$set(e.formData,"formDate",t)},expression:"formData.formDate"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""},on:{"expand-change":e.getOrderDetail}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.detail.hasOwnProperty("address")?a("div",{staticClass:"order-expand"},[a("base-card",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.row.detail.orderItemList,"show-header":!1,stripe:""}},[a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.produceName)+" "),""!==t.row.goodsTitle?a("el-tag",{attrs:{effect:"dark",size:"mini"}},[e._v(e._s(t.row.goodsTitle)+" ")]):e._e()]}}],null,!0)}),a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[""!==t.row.goodsThumb?a("img",{staticStyle:{height:"30px",width:"auto"},attrs:{src:e.$VUE_APP_BASE_API+t.row.goodsThumb}}):e._e()]}}],null,!0)}),a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" ₩ "+e._s(t.row.goodsPrice.toLocaleString())+" ")]}}],null,!0)}),a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" x "+e._s(t.row.goodsCount)+" ")]}}],null,!0)})],1)],1),a("base-card",[a("el-form",{staticClass:"order-expand-form",attrs:{"label-position":"left"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"订单 ID"}},[a("span",[e._v(e._s(t.row.id))])]),a("el-form-item",{attrs:{label:"用户 ID"}},[a("span",[e._v(e._s(t.row.userId))])]),a("el-form-item",{attrs:{label:"总金额"}},[a("span",[e._v("₩ "+e._s(t.row.allPrice.toLocaleString()))]),e._v(" "),t.row.deliveryPrice>0?a("span",[e._v(" (配送费:₩ "+e._s(t.row.deliveryPrice.toLocaleString())+") ")]):e._e()]),a("el-form-item",{attrs:{label:"优惠"}},[a("span",[e._v("₩ "+e._s(t.row.discountedPrices.toLocaleString()))]),""!=t.row.discount?a("span",[e._v("("+e._s(t.row.discount)+"折)")]):e._e()]),""!==t.row.detail.orderDetail.ps?a("el-form-item",{attrs:{label:"备注"}},[a("span",[e._v(e._s(t.row.detail.orderDetail.ps))])]):e._e()],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"地址"}},[a("div",[a("div",[e._v(e._s(t.row.detail.address.address))]),a("div",[e._v(e._s(t.row.detail.address.detail))])])]),a("el-form-item",{attrs:{label:"联系方式"}},[a("span",[e._v(e._s(t.row.detail.address.phone))])])],1)],1)],1)],1)],1):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"单号",prop:"number",width:"60"}}),a("el-table-column",{attrs:{align:"center",label:"用户"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.userName))])]}}])}),a("el-table-column",{attrs:{align:"center",label:"商品数量",prop:"goodsCount",width:"80"}}),a("el-table-column",{attrs:{align:"center",label:"总金额",prop:"allPrice"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(" ₩ "+e._s(t.row.allPrice.toLocaleString())+" "),a("el-tag",{attrs:{size:"mini",type:"success"}},[e._v(" ￥ "+e._s((t.row.allPrice/1e3*6).toFixed(2))+" ")])],1)]}}])}),a("el-table-column",{attrs:{align:"center",label:"折扣/抵扣金额",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.discount>0?a("div",[e._v(e._s(t.row.discount)+"折")]):e._e(),t.row.discountedPrices>0?a("div",[e._v(" ₩ "+e._s(t.row.discountedPrices.toLocaleString())+" "),a("el-tag",{attrs:{size:"mini",type:"success"}},[e._v(" ￥ "+e._s((t.row.discountedPrices/1e3*6).toFixed(2))+" ")])],1):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"实际金额"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(" ₩ "+e._s(t.row.realPrice.toLocaleString())+" "),a("el-tag",{attrs:{size:"mini",type:"success"}},[e._v(" ￥ "+e._s((t.row.realPrice/1e3*6).toFixed(2))+" ")])],1)]}}])}),a("el-table-column",{attrs:{align:"center",label:"支付方式"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tag",[e._v(e._s(e._f("paymentWayFormat")(t.row.paymentWay)))])]}}])}),a("el-table-column",{attrs:{align:"center",label:"支付状态"},scopedSlots:e._u([{key:"default",fn:function(t){return["PAID"===t.row.payState?a("el-tag",{attrs:{type:"success"}},[e._v(" "+e._s(e._f("payStateFormat")(t.row.payState))+" ")]):e._e(),"PAY_LATER"===t.row.payState?a("el-tag",{attrs:{type:"warning"}},[e._v(" "+e._s(e._f("payStateFormat")(t.row.payState))+" ")]):e._e(),"UN_PAY"===t.row.payState?a("el-tag",{attrs:{type:"danger"}},[e._v(" "+e._s(e._f("payStateFormat")(t.row.payState))+" ")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"订单状态"},scopedSlots:e._u([{key:"default",fn:function(t){return["FINISHED"===t.row.orderState?a("el-tag",{attrs:{type:"success"}},[e._v(" "+e._s(e._f("orderStateFormat")(t.row.orderState))+" ")]):"REFUND"===t.row.orderState?a("el-tag",{attrs:{type:"danger"}},[e._v(" "+e._s(e._f("orderStateFormat")(t.row.orderState))+" ")]):"EXPIRED"===t.row.orderState?a("el-tag",{attrs:{type:"info"}},[e._v(" "+e._s(e._f("orderStateFormat")(t.row.orderState))+" ")]):a("el-tag",[e._v(" "+e._s(e._f("orderStateFormat")(t.row.orderState))+" ")]),1===t.row.deleted?a("el-tag",{attrs:{type:"info"}},[e._v(" 已删除 ")]):e._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"创建时间",prop:"createDate"}}),a("el-table-column",{attrs:{align:"center",label:"操作",width:"170"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",{staticClass:"action-btns"},["WAITING_RECEIVE"===t.row.orderState&&"PAID"!==t.row.payState?a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onEditOrder(t.row.id)}}},[e._v("编辑 ")]):e._e(),"WEIXIN_PAY"!==t.row.paymentWay&&"TRANSFER"!==t.row.paymentWay&&"ALI_PAY"!==t.row.paymentWay||"UN_PAY"!==t.row.payState||"WAITING_RECEIVE"!==t.row.orderState?e._e():a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return e.onConfirmPay(t.row)}}},[e._v(" 确认收款 ")]),"UN_PAY"===t.row.payState||"PRODUCING"!==t.row.orderState&&"DELIVERING"!==t.row.orderState?e._e():a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return e.onPrintOrder(t.row)}}},[e._v(" 打印 ")]),"WAITING_RECEIVE"===t.row.orderState&&"UN_PAY"!==t.row.payState?a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return e.onReceiveOrder(t.row)}}},[e._v("接单 ")]):e._e(),"PRODUCING"===t.row.orderState?a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return e.onDeliveryOrder(t.row)}}},[e._v("配送 ")]):e._e(),"DELIVERING"===t.row.orderState?a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return e.onFinishOrder(t.row)}}},[e._v("完成 ")]):e._e(),"EXPIRED"!==t.row.orderState&&"REFUND"!==t.row.orderState&&("PAID"===t.row.payState||"PAY_LATER"===t.row.payState&&"FINISHED"===t.row.orderState)?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onRefundOrder(t.row)}}},[e._v(" 退款 ")]):e._e(),"EXPIRED"!==t.row.orderState&&"REFUND"!==t.row.orderState||0!==t.row.deleted?e._e():a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDeleteOrder(t.row)}}},[e._v(" 删除 ")])],1)]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),a("edit-order-dialog",{ref:"edit-order-dialog"})],1)},o=[],n=(a("7db0"),a("4160"),a("d3b7"),a("159b"),a("96cf"),a("1da1")),l=a("a145"),i=a("da6b"),s=a("f8b7"),c=a("ed08"),d={name:"NormalOrder",filters:{orderStateFormat:function(e){return Object(c["e"])(e)},payStateFormat:function(e){return Object(c["f"])(e)},paymentWayFormat:function(e){return Object(c["g"])(e)}},components:{BaseCard:l["a"],EditOrderDialog:i["a"]},data:function(){return{page:{current:1,size:15,total:0},formData:{nickName:null,number:null,orderId:null,paymentWay:null,orderState:null,showDelete:0,orderType:"NORMAL",formDate:[new Date,new Date]},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{getList:function(){var e=this;this.listLoading=!0;var t=Object.assign({},this.formData);t.startDate=Object(c["k"])(t.formDate[0],"{y}-{m}-{d}"),t.endDate=Object(c["k"])(t.formDate[1],"{y}-{m}-{d}"),s["a"].getOrderListByPage(this.page,t).then((function(t){var a=t.records;a.forEach((function(e){e.detail={}})),e.tableData=a,e.page.total=parseInt(t.total)})).finally((function(){e.listLoading=!1}))},getOrderDetail:function(e,t){var a=this;return Object(n["a"])(regeneratorRuntime.mark((function r(){var o;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:if(o=t.find((function(t){return t.id===e.id})),void 0===o){r.next=4;break}return r.next=4,s["a"].getOrderDetail(e.id).then((function(e){a.$set(o,"detail",e)}));case 4:case"end":return r.stop()}}),r)})))()},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()},onSearch:function(){this.page.current=1,this.getList()},onEditOrder:function(e){this.$refs["edit-order-dialog"].openDialog(e)},onPrintOrder:function(e){var t=this,a=this.$loading({lock:!0,text:"打印中...",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});s["a"].printOrder(e).then((function(e){t.$message.success("打印成功")})).finally((function(){a.close()}))},onConfirmPay:function(e){var t=this;this.$confirm("确定此账户已付款?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s["a"].confirmPay(e.id).then((function(a){t.$message.success(a.message),e.payState="PAID"}))}))},onReceiveOrder:function(e){var t=this;s["a"].receiveOrder(e.id).then((function(a){t.$message.success(a.message),e.orderState="PRODUCING"}))},onDeliveryOrder:function(e){var t=this;s["a"].deliveryOrder(e.id).then((function(a){t.$message.success(a.message),e.orderState="DELIVERING"}))},onFinishOrder:function(e){var t=this;this.$confirm("确定当前订单已完成?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s["a"].finishOrder(e.id).then((function(a){t.$message.success(a.message),e.orderState="FINISHED"}))}))},onRefundOrder:function(e){var t=this;this.$confirm("确定要退款吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s["a"].refundOrder(e.id).then((function(a){t.$message.success(a.message),e.orderState="REFUND"}))}))},onDeleteOrder:function(e){var t=this;this.$confirm("确定要删除这个订单吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s["a"].deleteOrder(e.id).then((function(e){t.$message.success(e.message),t.getList()}))}))}}},u=d,f=(a("dac8"),a("fbc6"),a("2877")),m=Object(f["a"])(u,r,o,!1,null,"4c9a9008",null);t["default"]=m.exports},"7db0":function(e,t,a){"use strict";var r=a("23e7"),o=a("b727").find,n=a("44d2"),l=a("ae40"),i="find",s=!0,c=l(i);i in[]&&Array(1)[i]((function(){s=!1})),r({target:"Array",proto:!0,forced:s||!c},{find:function(e){return o(this,e,arguments.length>1?arguments[1]:void 0)}}),n(i)},"9c7b":function(e,t,a){},ca98:function(e,t,a){},da6b:function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{directives:[{name:"loading",rawName:"v-loading",value:null===e.formData,expression:"formData===null"}],attrs:{"close-on-click-modal":!1,visible:e.dialogFormVisible,size:"mini",title:"编辑订单",width:"400px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[null!==e.formData?a("el-form",{attrs:{model:e.formData}},[a("el-form-item",{attrs:{label:"订单ID","label-width":"80"}},[a("el-input",{attrs:{disabled:!0},model:{value:e.formData.id,callback:function(t){e.$set(e.formData,"id",t)},expression:"formData.id"}})],1),a("el-form-item",{attrs:{label:"折扣","label-width":"80"}},[a("el-input",{attrs:{type:"number"},model:{value:e.formData.discount,callback:function(t){e.$set(e.formData,"discount",e._n(t))},expression:"formData.discount"}})],1),a("el-form-item",{attrs:{label:"备注","label-width":"80"}},[a("el-input",{attrs:{rows:3,type:"textarea"},model:{value:e.formData.ps,callback:function(t){e.$set(e.formData,"ps",t)},expression:"formData.ps"}})],1)],1):e._e(),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.editOrder}},[e._v("编 辑")])],1)],1)},o=[],n=a("f8b7"),l={name:"EditOrderDialog",data:function(){return{dialogFormVisible:!1,formData:null}},methods:{init:function(e){var t=this;this.formData=null,n["a"].getOrderDetail(e).then((function(e){t.formData={id:e.id,discount:e.discount,ps:e.ps}}))},openDialog:function(e){this.init(e),this.dialogFormVisible=!0},editOrder:function(){var e=this;n["a"].editOrder(this.formData).then((function(t){e.$message.success(t.message)}))}}},i=l,s=a("2877"),c=Object(s["a"])(i,r,o,!1,null,"2708142c",null);t["a"]=c.exports},dac8:function(e,t,a){"use strict";var r=a("9c7b"),o=a.n(r);o.a},fbc6:function(e,t,a){"use strict";var r=a("ca98"),o=a.n(r);o.a}}]);