(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-88c0f7d0"],{"182d2":function(e,t,a){"use strict";var n=a("9b9d"),i=a.n(n);i.a},2953:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"级别"}},[a("el-select",{attrs:{placeholder:"选择级别"},model:{value:e.formData.type,callback:function(t){e.$set(e.formData,"type",t)},expression:"formData.type"}},[a("el-option",{attrs:{value:null,label:"所有"}}),a("el-option",{attrs:{value:2,label:"店长"}}),a("el-option",{attrs:{value:3,label:"接单员"}}),a("el-option",{attrs:{value:4,label:"外卖员"}})],1)],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{placeholder:"选择状态"},model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{value:null,label:"所有"}}),a("el-option",{attrs:{value:1,label:"正常"}}),a("el-option",{attrs:{value:0,label:"冻结"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("el-col",{staticStyle:{"text-align":"right"},attrs:{span:6}},[a("el-button",{attrs:{size:"mini",type:"success"},on:{click:e.onCreateNewAdmin}},[e._v("添加新管理员")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{align:"center",label:"ID",prop:"id",width:"200"}}),a("el-table-column",{attrs:{align:"center",label:"账号",prop:"userName",width:"200"}}),a("el-table-column",{attrs:{align:"center",label:"级别",prop:"level",width:"200"}}),a("el-table-column",{attrs:{align:"center",label:"状态",prop:"state",width:"200"}}),a("el-table-column",{attrs:{align:"center",label:"最后登陆时间",prop:"loginDate"}}),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onEdit(t.row.id)}}},[e._v("编辑")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),a("admin-dialog",{ref:"admin-dialog",on:{"updated-admin":e.onUpdatedAdmin}})],1)},i=[],o=a("a145"),l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{"close-on-click-modal":!1,title:e.adminId?"管理员详情":"生成新管理员",visible:e.dialogFormVisible,size:"mini",width:"400px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.formData}},[e.adminId?a("el-form-item",{attrs:{label:"管理员ID","label-width":"120"}},[a("el-input",{attrs:{disabled:""},model:{value:e.formData.id,callback:function(t){e.$set(e.formData,"id",t)},expression:"formData.id"}})],1):e._e(),a("el-form-item",{attrs:{label:"用户名","label-width":"120"}},[a("el-input",{attrs:{disabled:null!==e.adminId&&void 0!==e.adminId},model:{value:e.formData.userName,callback:function(t){e.$set(e.formData,"userName",t)},expression:"formData.userName"}})],1),a("el-form-item",{attrs:{label:"密码","label-width":"120"}},[a("el-input",{model:{value:e.formData.password,callback:function(t){e.$set(e.formData,"password",t)},expression:"formData.password"}})],1),a("el-form-item",{attrs:{label:"级别","label-width":"120"}},[a("el-select",{attrs:{placeholder:"请输入关键词"},model:{value:e.formData.level,callback:function(t){e.$set(e.formData,"level",t)},expression:"formData.level"}},[a("el-option",{directives:[{name:"permission",rawName:"v-permission",value:["SUPER_MANAGER"],expression:"['SUPER_MANAGER']"}],attrs:{label:"店管理员",value:"SHOP_MANAGER"}}),a("el-option",{directives:[{name:"permission",rawName:"v-permission",value:["SUPER_MANAGER","SHOP_MANAGER"],expression:"['SUPER_MANAGER','SHOP_MANAGER']"}],attrs:{label:"接单员",value:"RECEIVER"}}),a("el-option",{directives:[{name:"permission",rawName:"v-permission",value:["SUPER_MANAGER","SHOP_MANAGER"],expression:"['SUPER_MANAGER','SHOP_MANAGER']"}],attrs:{label:"外卖员",value:"DELIVERER"}})],1)],1),a("el-form-item",{attrs:{label:"状态","label-width":"120"}},[a("el-select",{attrs:{placeholder:"请输入关键词"},model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{label:"冻结",value:"STOP"}}),a("el-option",{attrs:{label:"正常",value:"NORMAL"}})],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),e.adminId?a("el-button",{attrs:{type:"primary"},on:{click:e.onEdit}},[e._v("修 改")]):a("el-button",{attrs:{type:"success"},on:{click:e.onSave}},[e._v("生 成")])],1)],1)},r=[],s=a("50fc"),c=(a("caad"),a("45fc"),a("2532"),a("4360")),d={inserted:function(e,t,a){var n=t.value,i=c["a"].getters&&c["a"].getters.roles;if(!(n&&n instanceof Array&&n.length>0))throw new Error("need roles! Like v-permission=\"['admin','editor']\"");var o=n,l=i.some((function(e){return o.includes(e)}));l||e.parentNode&&e.parentNode.removeChild(e)}},u=function(e){e.directive("permission",d)};window.Vue&&(window["permission"]=d,Vue.use(u)),d.install=u;var m=d,p={name:"AdminDialog",directives:{permission:m},data:function(){return{dialogFormVisible:!1,adminId:null,formData:{id:null,userName:null,password:null,level:null,state:null},loading:!1}},watch:{dialogFormVisible:function(){this.dialogFormVisible?this.init():this.formData=this.$options.data().formData}},methods:{init:function(){var e=this;this.adminId&&(this.loading=!0,s["a"].getAdminInfoById(this.adminId).then((function(t){e.formData=t,e.loading=!1})).catch((function(t){e.loading=!1})))},openDialog:function(e){this.adminId=e,this.dialogFormVisible=!0},onEdit:function(){var e=this;s["a"].updateAdmin(this.formData).then((function(t){e.$message({message:t.message,type:"success"}),e.$emit("updated-admin",e.formData)}))},onSave:function(){var e=this;s["a"].createNewSubAdmin(this.formData).then((function(t){e.$message({message:t.message,type:"success"}),e.$emit("updated-admin",e.formData)}))}}},f=p,g=a("2877"),h=Object(g["a"])(f,l,r,!1,null,"62e414b8",null),b=h.exports,v=a("709b"),w={name:"AdminManagement",components:{BaseCard:o["a"],AdminDialog:b},data:function(){return{page:{current:1,size:15,total:0},formData:{type:null,state:null},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{getList:function(){var e=this;this.listLoading=!0,s["a"].getSubAdminByPage(this.page,this.formData).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total),e.listLoading=!1})).catch((function(){e.listLoading=!1}))},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()},onSearch:function(){this.page.current=1,this.getList()},onChange:function(e){var t=this;v["a"].changeIsShow(e.id,e.isShow).then((function(e){t.$message({message:e.message,type:"success"})}))},onCreateNewAdmin:function(){this.$refs["admin-dialog"].openDialog()},onEdit:function(e){this.$refs["admin-dialog"].openDialog(e)},onDelete:function(e){var t=this;this.$confirm("是否确定删除此横幅?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){v["a"].deleteBanner(e).then((function(){t.getList()}))}))},onUpdatedAdmin:function(){this.onSearch()}}},D=w,y=(a("182d2"),Object(g["a"])(D,n,i,!1,null,"39e2940c",null));t["default"]=y.exports},"709b":function(e,t,a){"use strict";var n=a("b775"),i=a("4328"),o=a.n(i);function l(e){return Object(n["a"])({url:"/banner/getBannerById",params:{id:e}})}function r(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/banner/getBannerListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=o.a.stringify(e),e}]})}function s(e){return Object(n["a"])({url:"/banner/createNewBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=o.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/banner/updateBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=o.a.stringify(e),e}]})}function d(e,t){var a={id:e,isShow:t};return Object(n["a"])({url:"/banner/changeIsShow",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=o.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/banner/deleteBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=o.a.stringify(e),e}]})}t["a"]={getBannerById:l,getBannerListByPage:r,createNewBanner:s,updateBanner:c,changeIsShow:d,deleteBanner:u}},"9b9d":function(e,t,a){}}]);