(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-db440226"],{"179c":function(t,e,a){"use strict";var n=a("96f3"),o=a.n(n);o.a},"4f2f":function(t,e,a){"use strict";var n=a("b296"),o=a.n(n);o.a},"7db0":function(t,e,a){"use strict";var n=a("23e7"),o=a("b727").find,r=a("44d2"),l=a("ae40"),i="find",s=!0,c=l(i);i in[]&&Array(1)[i]((function(){s=!1})),n({target:"Array",proto:!0,forced:s||!c},{find:function(t){return o(this,t,arguments.length>1?arguments[1]:void 0)}}),r(i)},9490:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{"close-on-click-modal":!1,visible:t.dialogFormVisible,size:"mini",title:"生成优惠卷",width:"600px"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{attrs:{model:t.formData}},[a("el-form-item",{attrs:{label:"优惠卷模板","label-width":"120"}},[a("el-select",{attrs:{placeholder:"请选择优惠卷模板"},on:{change:t.onTemplateChange},model:{value:t.formData.templateId,callback:function(e){t.$set(t.formData,"templateId",e)},expression:"formData.templateId"}},t._l(t.templateArr,(function(t){return a("el-option",{key:t.id,attrs:{label:t.couponName,value:t.id}})})),1)],1),a("el-form-item",{attrs:{label:"用户ID","label-width":"120"}},[a("el-input",{attrs:{autosize:{minRows:5,maxRows:15},type:"textarea"},model:{value:t.formData.userIds,callback:function(e){t.$set(t.formData,"userIds",e)},expression:"formData.userIds"}})],1),a("el-form-item",{attrs:{label:"到期天数","label-width":"120"}},[a("el-input",{model:{value:t.formData.expireDays,callback:function(e){t.$set(t.formData,"expireDays",t._n(e))},expression:"formData.expireDays"}})],1),a("el-form-item",{attrs:{label:"优惠卷数量","label-width":"120"}},[a("el-input",{model:{value:t.formData.count,callback:function(e){t.$set(t.formData,"count",t._n(e))},expression:"formData.count"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:t.generateCoupon}},[t._v("生 成")])],1)],1)},o=[],r=(a("7db0"),a("a15b"),a("cbfe")),l=a("c5bb"),i={name:"GenerateCoupon",props:{userIds:Array},watch:{dialogFormVisible:function(){this.dialogFormVisible&&this.init()}},data:function(){return{dialogFormVisible:!1,templateArr:[],formData:{templateId:"",userIds:"",expireDays:0,count:1}}},methods:{init:function(){var t=this;this.userIds?this.formData.userIds=this.userIds.join("\r\n"):this.formData.userIds="",l["a"].getAllTemplateList().then((function(e){t.templateArr=e}))},openDialog:function(){this.dialogFormVisible=!0},generateCoupon:function(){var t=this;r["a"].generateCoupon(this.formData).then((function(e){t.$message({message:e.message,type:"success"})}))},onTemplateChange:function(t){this.formData.expireDays=this.templateArr.find((function(e){return e.id===t})).expireDays}}},s=i,c=a("2877"),u=Object(c["a"])(s,n,o,!1,null,"4a22a962",null);e["a"]=u.exports},"96f3":function(t,e,a){},a145:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"base-card"},[t._t("default")],2)},o=[],r={name:"BaseCard"},l=r,i=(a("179c"),a("2877")),s=Object(i["a"])(l,n,o,!1,null,"9e9f1e78",null);e["a"]=s.exports},a77f:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:t.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{attrs:{placeholder:"请输入昵称"},model:{value:t.formData.nickName,callback:function(e){t.$set(t.formData,"nickName",e)},expression:"formData.nickName"}})],1),a("el-form-item",{attrs:{label:"类型"}},[a("el-select",{attrs:{placeholder:"请选择优惠卷类型"},model:{value:t.formData.couponType,callback:function(e){t.$set(t.formData,"couponType",e)},expression:"formData.couponType"}},[a("el-option",{attrs:{label:"所有",value:""}}),a("el-option",{attrs:{label:"现金卷",value:"1"}}),a("el-option",{attrs:{label:"折扣卷",value:"2"}})],1)],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{placeholder:"请选择优惠卷状态"},model:{value:t.formData.couponState,callback:function(e){t.$set(t.formData,"couponState",e)},expression:"formData.couponState"}},[a("el-option",{attrs:{label:"所有",value:""}}),a("el-option",{attrs:{label:"未使用",value:"0"}}),a("el-option",{attrs:{label:"已使用",value:"1"}}),a("el-option",{attrs:{label:"已过期",value:"2"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.onSearch}},[t._v("查询")])],1)],1),a("div",{staticClass:"action-bar"},[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:t.onSendCoupon}},[t._v("发放优惠卷")])],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:t.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("div",{staticClass:"template-expand"},[a("el-form",{staticClass:"template-expand-form",attrs:{"label-position":"left"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"用户ID:"}},[t._v(" "+t._s(e.row.userId)+" ")])],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"允许的分类:"}},t._l(e.row.allowCategory,(function(e){return a("el-tag",{key:e,staticClass:"rule-tag",attrs:{effect:"dark",type:"success"}},[t._v(" "+t._s(e)+" ")])})),1),a("el-form-item",{attrs:{label:"限制的分类:"}},t._l(e.row.limitCategory,(function(e){return a("el-tag",{key:e,staticClass:"rule-tag",attrs:{effect:"dark",type:"danger"}},[t._v(" "+t._s(e)+" ")])})),1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"允许的商品:"}},t._l(e.row.allowGoods,(function(e){return a("el-tag",{key:e,staticClass:"rule-tag",attrs:{effect:"dark",type:"success"}},[t._v(" "+t._s(e)+" ")])})),1),a("el-form-item",{attrs:{label:"限制的商品:"}},t._l(e.row.limitGoods,(function(e){return a("el-tag",{key:e,staticClass:"rule-tag",attrs:{effect:"dark",type:"danger"}},[t._v(" "+t._s(e)+" ")])})),1)],1)],1)],1)],1)]}}])}),a("el-table-column",{attrs:{align:"center",label:"Id",prop:"id",width:"160"}}),a("el-table-column",{attrs:{align:"center",label:"昵称",prop:"nickName"}}),a("el-table-column",{attrs:{align:"center",label:"优惠卷名称",prop:"couponName"}}),a("el-table-column",{attrs:{align:"center",label:"优惠卷类型"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(t._f("couponTypeFormat")(e.row.couponType))+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"优惠卷面值"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.couponMoney.toLocaleString())+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"优惠卷折扣",prop:"couponDiscount"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.couponDiscount)+"折 ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"最低消费"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.minimumMoney.toLocaleString())+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"过期日期",prop:"expireDate",width:"100"}}),a("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:t._u([{key:"default",fn:function(e){return["UN_USE"===e.row.state?a("el-tag",{attrs:{type:"success"}},[t._v(" "+t._s(t._f("couponStateFormat")(e.row.state))+" ")]):"USED"===e.row.state?a("el-tag",{attrs:{type:"danger"}},[t._v(" "+t._s(t._f("couponStateFormat")(e.row.state))+" ")]):"EXPIRED"===e.row.state?a("el-tag",{attrs:{type:"info"}},[t._v(" "+t._s(t._f("couponStateFormat")(e.row.state))+" ")]):t._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"创建时间",prop:"createDate",width:"100"}}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.onDelete(e.row.id)}}},[t._v(" 删除 ")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":t.page.current,"page-size":t.page.size,"page-sizes":[15,50,100],total:t.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":t.handleCurrentChange,"size-change":t.handleSizeChange}})],1),a("generate-coupon-dialog",{ref:"generate-coupon-dialog"})],1)},o=[],r=a("a145"),l=a("9490"),i=a("cbfe"),s=a("ed08"),c={name:"CouponManagement",components:{BaseCard:r["a"],GenerateCouponDialog:l["a"]},filters:{couponTypeFormat:function(t){return Object(s["c"])(t)},couponStateFormat:function(t){return Object(s["b"])(t)}},data:function(){return{page:{current:1,size:15,total:0},formData:{nickName:"",couponType:"",couponState:"",bindState:1},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{getList:function(){var t=this;this.listLoading=!0,i["a"].getCouponListByPage(this.page,this.formData).then((function(e){t.tableData=e.records,t.page.total=parseInt(e.total),t.listLoading=!1})).catch((function(){t.listLoading=!1}))},handleSizeChange:function(t){this.page.size=t,this.getList()},handleCurrentChange:function(t){this.page.current=t,this.getList()},onSearch:function(){this.page.current=1,this.getList()},onDelete:function(t){var e=this;this.$confirm("是否确定删除此优惠卷?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){i["a"].deleteCoupon(t).then((function(){e.getList()}))}))},onSendCoupon:function(){this.$refs["generate-coupon-dialog"].openDialog()}}},u=c,p=(a("4f2f"),a("2877")),f=Object(p["a"])(u,n,o,!1,null,"97007cc6",null);e["default"]=f.exports},b296:function(t,e,a){},c5bb:function(t,e,a){"use strict";a("a15b"),a("d81d");var n=a("b775"),o=a("4328"),r=a.n(o);function l(t){return Object(n["a"])({url:"/coupon/template/getTemplateById",params:{id:t}})}function i(t,e){var a=Object.assign({},t,e);return Object(n["a"])({url:"/coupon/template/getTemplateListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function s(){return Object(n["a"])({url:"/coupon/template/getAllTemplateList",method:"get",headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function c(t){var e=Object.assign({},t);return e.allowCategory=e.allowCategory.map((function(t){return t.value})).join(),e.limitCategory=e.limitCategory.map((function(t){return t.value})).join(),e.allowGoods=e.allowGoods.map((function(t){return t.value})).join(),e.limitGoods=e.limitGoods.map((function(t){return t.value})).join(),Object(n["a"])({url:"/coupon/template/createNewTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function u(t){return Object(n["a"])({url:"/coupon/template/updateTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function p(t){return Object(n["a"])({url:"/coupon/template/deleteTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:t},transformRequest:[function(t){return t=r.a.stringify(t),t}]})}e["a"]={getTemplateById:l,getTemplateListByPage:i,getAllTemplateList:s,createNewTemplate:c,updateTemplate:u,deleteTemplate:p}},cbfe:function(t,e,a){"use strict";var n=a("b775"),o=a("4328"),r=a.n(o);function l(t){return Object(n["a"])({url:"/coupon/getCouponById",params:{id:t}})}function i(t,e){var a=Object.assign({},t,e);return Object(n["a"])({url:"/coupon/getCouponListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function s(t,e){var a=Object.assign({},t,e);return Object(n["a"])({url:"/coupon/log/getCouponLogListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function c(t){return Object(n["a"])({url:"/coupon/generateCoupon",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(t){return t=r.a.stringify(t),t}]})}function u(t){return Object(n["a"])({url:"/coupon/deleteCoupon",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:t},transformRequest:[function(t){return t=r.a.stringify(t),t}]})}e["a"]={getCouponById:l,getCouponListByPage:i,getCouponLogListByPage:s,generateCoupon:c,deleteCoupon:u}}}]);