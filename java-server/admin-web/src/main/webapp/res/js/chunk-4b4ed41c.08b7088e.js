(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4b4ed41c"],{"119c":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"名称"}},[a("el-input",{attrs:{placeholder:"分类名称"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("el-col",{staticStyle:{"text-align":"right"},attrs:{span:6}},[a("add-category-dialog",{on:{createSuccess:e.onSearch}})],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{label:"ID",prop:"id",width:"350"}}),a("el-table-column",{attrs:{label:"名称"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:"请输入内容",size:"small"},on:{change:function(a){return e.onEdit(t.$index,t.row)}},model:{value:t.row.name,callback:function(a){e.$set(t.row,"name",a)},expression:"scope.row.name"}}),a("span",[e._v(e._s(t.row.name))])]}}])}),a("el-table-column",{attrs:{label:"优先级"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:"请输入优先级",size:"small"},on:{change:function(a){return e.onEdit(t.$index,t.row)}},model:{value:t.row.index,callback:function(a){e.$set(t.row,"index",a)},expression:"scope.row.index"}}),a("span",[e._v(e._s(t.row.index))])]}}])}),a("el-table-column",{attrs:{label:"操作",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1)],1)},o=[],r=(a("d3b7"),a("a145")),i=a("c405"),s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-button",{attrs:{size:"mini",type:"success"},on:{click:e.onCreateNewCategory}},[e._v("创建新分类")]),a("el-dialog",{staticStyle:{"text-align":"left"},attrs:{"destroy-on-close":!0,visible:e.dialogVisible,title:"添加分类"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{attrs:{model:e.formData}},[a("el-form-item",{attrs:{label:"分类名称"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.createLoading,expression:"createLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{type:"primary"},on:{click:e.handleCreateNewCategory}},[e._v("确 定 ")])],1)],1)],1)},l=[],c=(a("b0c0"),{name:"AddCategoryDialog",data:function(){return{dialogVisible:!1,createLoading:!1,formData:{name:null}}},watch:{dialogVisible:function(e){e&&(this.formData={})}},methods:{onCreateNewCategory:function(){this.dialogVisible=!0},handleCreateNewCategory:function(){var e=this;null!==this.formData.name&&""!==this.formData.name?(this.createLoading=!0,i["a"].createNewCategory(this.formData).then((function(t){e.dialogVisible=!1,e.$message.success(t.message),e.$emit("createSuccess")})).finally((function(){e.createLoading=!1}))):this.$message.error("请输入分类名称")}}}),u=c,d=a("2877"),f=Object(d["a"])(u,s,l,!1,null,"5058190c",null),g=f.exports,m={name:"CategoryManagement",components:{BaseCard:r["a"],AddCategoryDialog:g},data:function(){return{page:{current:1,size:15,total:0},formData:{name:null},listLoading:!1,tableData:[]}},created:function(){this.onSearch()},methods:{getList:function(){var e=this;this.listLoading=!0,i["a"].getCategoryListByPage(this.page,this.formData).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total)})).finally((function(){e.listLoading=!1}))},onSearch:function(){this.page.current=1,this.getList()},onEdit:function(e,t){var a=this;i["a"].updateCategory(t).then((function(e){a.$message.success(e.message)}))},onDelete:function(e){var t=this;this.$confirm("是否确定删除此分类?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){i["a"].deleteCategory(e).then((function(){t.getList()}))}))},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()}}},p=m,h=(a("5a9d"),Object(d["a"])(p,n,o,!1,null,"809805d0",null));t["default"]=h.exports},"179c":function(e,t,a){"use strict";var n=a("96f3"),o=a.n(n);o.a},2916:function(e,t,a){},"5a9d":function(e,t,a){"use strict";var n=a("2916"),o=a.n(n);o.a},"96f3":function(e,t,a){},a145:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},o=[],r={name:"BaseCard"},i=r,s=(a("179c"),a("2877")),l=Object(s["a"])(i,n,o,!1,null,"9e9f1e78",null);t["a"]=l.exports},c405:function(e,t,a){"use strict";var n=a("b775"),o=a("4328"),r=a.n(o);function i(){return Object(n["a"])({url:"/category/getAllCategory",method:"get"})}function s(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/category/getCategoryListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function l(e){return Object(n["a"])({url:"/category/createNewCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/category/updateCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/category/deleteCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getAllCategory:i,getCategoryListByPage:s,createNewCategory:l,updateCategory:c,deleteCategory:u}}}]);