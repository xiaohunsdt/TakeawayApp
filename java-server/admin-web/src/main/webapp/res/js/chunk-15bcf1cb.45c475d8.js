(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-15bcf1cb"],{2595:function(t,e,a){"use strict";var n=a("a23d"),r=a.n(n);r.a},6618:function(t,e,a){"use strict";var n=a("b775"),r=a("4328"),i=a.n(r);function o(){return Object(n["a"])({url:"/spec/getAll",method:"get"})}function s(t,e){var a=Object.assign({},t,e);return Object(n["a"])({url:"/spec/getListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(t){return t=i.a.stringify(t),t}]})}function c(t){return Object(n["a"])({url:"/spec/create",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{key:t},transformRequest:[function(t){return t=i.a.stringify(t),t}]})}function l(t){return Object(n["a"])({url:"/spec/update",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(t){return t=i.a.stringify(t),t}]})}function u(t){return Object(n["a"])({url:"/spec/delete",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:t},transformRequest:[function(t){return t=i.a.stringify(t),t}]})}e["a"]={getAll:o,getListByPage:s,create:c,update:l,del:u}},8276:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:t.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"规格"}},[a("el-input",{attrs:{placeholder:"规格名称"},model:{value:t.formData.key,callback:function(e){t.$set(t.formData,"key",e)},expression:"formData.key"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.onSearch}},[t._v("查询")])],1)],1)],1),a("el-col",{staticStyle:{"text-align":"right"},attrs:{span:6}},[a("el-button",{attrs:{size:"mini",type:"success"},on:{click:t.onCreate}},[t._v("添加新规格")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:t.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{label:"ID",prop:"id",width:"350"}}),a("el-table-column",{attrs:{label:"名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-input",{attrs:{placeholder:"请输入内容",size:"small"},on:{change:function(a){return t.onEdit(e.$index,e.row)}},model:{value:e.row.key,callback:function(a){t.$set(e.row,"key",a)},expression:"scope.row.key"}}),a("span",[t._v(t._s(e.row.key))])]}}])}),a("el-table-column",{attrs:{label:"操作",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.storeId===t.userData.storeId?a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.onDelete(e.row.id)}}},[t._v("删除")]):t._e()]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":t.page.current,"page-size":t.page.size,"page-sizes":[15,50,100],total:t.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":t.handleCurrentChange,"size-change":t.handleSizeChange}})],1)],1)},r=[],i=(a("d3b7"),a("5530")),o=a("a145"),s=a("6618"),c=a("2f62"),l={name:"CategoryManagement",components:{BaseCard:o["a"]},computed:Object(i["a"])({},Object(c["b"])(["userData"])),data:function(){return{page:{current:1,size:15,total:0},formData:{key:null},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{getList:function(){var t=this;this.listLoading=!0,s["a"].getListByPage(this.page,this.formData).then((function(e){t.tableData=e.records,t.page.total=parseInt(e.total)})).finally((function(){t.listLoading=!1}))},onSearch:function(){this.page.current=1,this.getList()},onCreate:function(){var t=this;this.$prompt("请输入规格","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(e){var a=e.value;s["a"].create(a).then((function(e){t.$message.success(e.message),t.onSearch()}))}))},onEdit:function(t,e){var a=this;s["a"].update(e).then((function(t){a.$message.success(t.message)}))},onDelete:function(t){var e=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){s["a"].del(t).then((function(){e.getList()}))}))},handleSizeChange:function(t){this.page.size=t,this.getList()},handleCurrentChange:function(t){this.page.current=t,this.getList()}}},u=l,d=(a("2595"),a("2877")),p=Object(d["a"])(u,n,r,!1,null,"9ccc43aa",null);e["default"]=p.exports},a23d:function(t,e,a){}}]);