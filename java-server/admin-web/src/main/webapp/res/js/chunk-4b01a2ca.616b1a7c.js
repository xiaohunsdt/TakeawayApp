(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4b01a2ca"],{"179c":function(e,t,a){"use strict";var n=a("96f3"),o=a.n(n);o.a},3002:function(e,t,a){"use strict";var n=a("4c0f"),o=a.n(n);o.a},"4c0f":function(e,t,a){},"7db0":function(e,t,a){"use strict";var n=a("23e7"),o=a("b727").find,r=a("44d2"),i=a("ae40"),l="find",s=!0,u=i(l);l in[]&&Array(1)[l]((function(){s=!1})),n({target:"Array",proto:!0,forced:s||!u},{find:function(e){return o(this,e,arguments.length>1?arguments[1]:void 0)}}),r(l)},9490:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{"close-on-click-modal":!1,visible:e.dialogFormVisible,size:"mini",title:"生成优惠卷",width:"600px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.formData}},[a("el-form-item",{attrs:{label:"优惠卷模板","label-width":"120"}},[a("el-select",{attrs:{placeholder:"请选择优惠卷模板"},on:{change:e.onTemplateChange},model:{value:e.formData.templateId,callback:function(t){e.$set(e.formData,"templateId",t)},expression:"formData.templateId"}},e._l(e.templateArr,(function(e){return a("el-option",{key:e.id,attrs:{label:e.couponName,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"用户ID","label-width":"120"}},[a("el-input",{attrs:{autosize:{minRows:5,maxRows:15},type:"textarea"},model:{value:e.formData.userIds,callback:function(t){e.$set(e.formData,"userIds",t)},expression:"formData.userIds"}})],1),a("el-form-item",{attrs:{label:"到期天数","label-width":"120"}},[a("el-input",{model:{value:e.formData.expireDays,callback:function(t){e.$set(e.formData,"expireDays",e._n(t))},expression:"formData.expireDays"}})],1),a("el-form-item",{attrs:{label:"优惠卷数量","label-width":"120"}},[a("el-input",{model:{value:e.formData.count,callback:function(t){e.$set(e.formData,"count",e._n(t))},expression:"formData.count"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.generateCoupon}},[e._v("生 成")])],1)],1)},o=[],r=(a("7db0"),a("a15b"),a("cbfe")),i=a("c5bb"),l={name:"GenerateCoupon",props:{userIds:Array},watch:{dialogFormVisible:function(){this.dialogFormVisible&&this.init()}},data:function(){return{dialogFormVisible:!1,templateArr:[],formData:{templateId:"",userIds:"",expireDays:0,count:1}}},methods:{init:function(){var e=this;this.userIds?this.formData.userIds=this.userIds.join("\r\n"):this.formData.userIds="",i["a"].getAllTemplateList().then((function(t){e.templateArr=t}))},openDialog:function(){this.dialogFormVisible=!0},generateCoupon:function(){var e=this;r["a"].generateCoupon(this.formData).then((function(t){e.$message.success(t.message)}))},onTemplateChange:function(e){this.formData.expireDays=this.templateArr.find((function(t){return t.id===e})).expireDays}}},s=l,u=a("2877"),c=Object(u["a"])(s,n,o,!1,null,"d0d4c38a",null);t["a"]=c.exports},"96f3":function(e,t,a){},a145:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},o=[],r={name:"BaseCard"},i=r,l=(a("179c"),a("2877")),s=Object(l["a"])(i,n,o,!1,null,"9e9f1e78",null);t["a"]=s.exports},c5bb:function(e,t,a){"use strict";a("a15b"),a("d81d");var n=a("b775"),o=a("4328"),r=a.n(o);function i(e){return Object(n["a"])({url:"/coupon/template/getTemplateById",params:{id:e}})}function l(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/coupon/template/getTemplateListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function s(){return Object(n["a"])({url:"/coupon/template/getAllTemplateList",method:"get",headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function u(e){var t=Object.assign({},e);return t.allowCategory=t.allowCategory.map((function(e){return e.value})).join(),t.limitCategory=t.limitCategory.map((function(e){return e.value})).join(),t.allowGoods=t.allowGoods.map((function(e){return e.value})).join(),t.limitGoods=t.limitGoods.map((function(e){return e.value})).join(),Object(n["a"])({url:"/coupon/template/createNewTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/coupon/template/updateTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function d(e){return Object(n["a"])({url:"/coupon/template/deleteTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getTemplateById:i,getTemplateListByPage:l,getAllTemplateList:s,createNewTemplate:u,updateTemplate:c,deleteTemplate:d}},cbfe:function(e,t,a){"use strict";var n=a("b775"),o=a("4328"),r=a.n(o);function i(e){return Object(n["a"])({url:"/coupon/getCouponById",params:{id:e}})}function l(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/coupon/getCouponListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function s(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/coupon/log/getCouponLogListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/coupon/generateCoupon",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/coupon/deleteCoupon",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getCouponById:i,getCouponListByPage:l,getCouponLogListByPage:s,generateCoupon:u,deleteCoupon:c}},e382:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"昵称"}},[a("el-input",{attrs:{placeholder:"昵称"},model:{value:e.formData.nickName,callback:function(t){e.$set(e.formData,"nickName",t)},expression:"formData.nickName"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1),a("div",{staticClass:"action-bar"},[a("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.onSendCoupon}},[e._v("发放优惠卷")])],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{align:"center",label:"ID",prop:"id",width:"160"}}),a("el-table-column",{attrs:{align:"center",label:"昵称"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.nickName))])]}}])}),a("el-table-column",{attrs:{align:"center",label:"等级",prop:"level"}}),a("el-table-column",{attrs:{align:"center",label:"余额",prop:"money"}}),a("el-table-column",{attrs:{align:"center",label:"性别",prop:"gender"}}),a("el-table-column",{attrs:{align:"center",label:"最后登录时间",prop:"lastLoginDate"}}),a("el-table-column",{attrs:{align:"center",label:"加入日期",prop:"createDate"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),a("generate-coupon-dialog",{ref:"generate-coupon-dialog",attrs:{"user-ids":e.selectedUserIds}})],1)},o=[],r=(a("d81d"),a("d3b7"),a("a145")),i=a("9490"),l=a("b775"),s=a("4328"),u=a.n(s);function c(e,t){var a=Object.assign({},e,t);return Object(l["a"])({url:"/user/getUserListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=u.a.stringify(e),e}]})}function d(e){return Object(l["a"])({url:"/user/updateUser",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=u.a.stringify(e),e}]})}function p(e){return Object(l["a"])({url:"/user/deleteUser",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=u.a.stringify(e),e}]})}var m={getUserListByPage:c,updateUser:d,deleteUser:p},f={name:"UserManagement",components:{BaseCard:r["a"],GenerateCouponDialog:i["a"]},computed:{selectedUserIds:function(){return this.multipleSelection.map((function(e){return e.id}))}},data:function(){return{page:{current:1,size:15,total:0},formData:{nickName:null,categoryId:null},listLoading:!1,tableData:[],multipleSelection:[]}},created:function(){this.onSearch()},methods:{getList:function(){var e=this;this.listLoading=!0,m.getUserListByPage(this.page,this.formData).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total)})).finally((function(){e.listLoading=!1}))},onSearch:function(){this.page.current=1,this.getList()},onDelete:function(e){var t=this;this.$confirm("是否确定删除此用户?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){m.deleteUser(e).then((function(){t.getList()}))}))},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()},handleSelectionChange:function(e){this.multipleSelection=e},onSendCoupon:function(){this.$refs["generate-coupon-dialog"].openDialog()}}},g=f,h=(a("3002"),a("2877")),b=Object(h["a"])(g,n,o,!1,null,"090dc2c8",null);t["default"]=b.exports}}]);