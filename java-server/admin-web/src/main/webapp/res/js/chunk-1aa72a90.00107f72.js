(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1aa72a90"],{"179c":function(e,t,a){"use strict";var o=a("96f3"),n=a.n(o);n.a},"1b55":function(e,t,a){"use strict";var o=a("6af3"),n=a.n(o);n.a},"3b75":function(e,t,a){},5377:function(e,t,a){var o=a("83ab"),n=a("9bf2"),r=a("ad6d"),i=a("9f7f").UNSUPPORTED_Y;o&&("g"!=/./g.flags||i)&&n.f(RegExp.prototype,"flags",{configurable:!0,get:r})},"6af3":function(e,t,a){},"6d32":function(e,t,a){},7732:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"名称"}},[a("el-input",{attrs:{placeholder:"商品名称"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",{attrs:{label:"分类"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.formData.categoryId,callback:function(t){e.$set(e.formData,"categoryId",t)},expression:"formData.categoryId"}},[a("el-option",{attrs:{label:" ",value:""}}),e._l(e.categoryList,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],2)],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{placeholder:"选择状态"},model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{label:" ",value:""}}),a("el-option",{attrs:{label:"上架",value:"1"}}),a("el-option",{attrs:{label:"下架",value:"0"}}),a("el-option",{attrs:{label:"缺货",value:"-1"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("el-col",{staticStyle:{"text-align":"right"},attrs:{span:6}},[a("el-button",{attrs:{size:"mini",type:"success"},on:{click:e.onCreateNewGoods}},[e._v("添加新商品")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{label:"ID",prop:"id",align:"center"}}),a("el-table-column",{attrs:{label:"名称",prop:"name",align:"center"}}),a("el-table-column",{attrs:{label:"缩略图",width:"100",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[""===t.row.thumb?a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onUploadImg(t.row)}}},[e._v(" 上传 ")]):a("img",{staticStyle:{height:"30px",width:"auto"},attrs:{src:e.$VUE_APP_BASE_API+t.row.thumb},on:{click:function(a){return e.onUploadImg(t.row)}}})]}}])}),a("el-table-column",{attrs:{label:"简介",prop:"desc",width:"250",align:"center"}}),a("el-table-column",{attrs:{label:"分类",prop:"category",align:"center"}}),a("el-table-column",{attrs:{label:"价格",prop:"price",align:"center"}}),a("el-table-column",{attrs:{label:"月销",prop:"monthSale",align:"center"}}),a("el-table-column",{attrs:{label:"评分",prop:"rate",align:"center"}}),a("el-table-column",{attrs:{label:"状态",prop:"state",align:"center"}}),a("el-table-column",{attrs:{label:"操作",width:"170",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),a("goods-dialog",{attrs:{"category-list":e.categoryList,"dialog-visible":e.dialogVisible,"goods-data":e.currentGoods},on:{"update:dialogVisible":function(t){e.dialogVisible=t},"update:dialog-visible":function(t){e.dialogVisible=t},"update:goodsData":function(t){e.currentGoods=t},"update:goods-data":function(t){e.currentGoods=t},"event-success":e.onSearch}}),a("goods-image-dialog",{attrs:{dialogVisible:e.imageUploaderVisible,goodsData:e.currentGoods},on:{"update:dialogVisible":function(t){e.imageUploaderVisible=t},"update:dialog-visible":function(t){e.imageUploaderVisible=t},"update:goodsData":function(t){e.currentGoods=t},"update:goods-data":function(t){e.currentGoods=t}}})],1)},n=[],r=a("a145"),i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{"close-on-click-modal":!1,"show-close":!1,visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-upload",{ref:"uploader",attrs:{action:e.$VUE_APP_BASE_API+"/api/admin/uploadImg",headers:e.authHeader,"on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove,"on-success":e.onSuccess,"list-type":"picture-card"}},[a("i",{staticClass:"el-icon-plus"})]),a("el-dialog",{attrs:{visible:e.imgDetailDialogVisible},on:{"update:visible":function(t){e.imgDetailDialogVisible=t}}},[a("img",{attrs:{src:e.imgDetailUrl,alt:"",width:"100%"}})]),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.closeWindow}},[e._v("关闭")])],1)],1)},l=[],s=a("5f87"),c=a("b775"),u=a("4328"),d=a.n(u);function g(e){return Object(c["a"])({url:"/goods/getByGoodsId",params:{id:e}})}function f(){return Object(c["a"])({url:"/goods/getAllGoods",method:"get"})}function m(e,t){var a=Object.assign({},e,t);return Object(c["a"])({url:"/goods/getGoodsListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function p(e){return Object(c["a"])({url:"/goods/getStockByGoodsId",params:{id:e}})}function b(e){return Object(c["a"])({url:"/goods/createNewGoods",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function h(e){return Object(c["a"])({url:"/goods/updateGoods",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function y(e,t){return Object(c["a"])({url:"/goods/updateStock",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{goodsId:e,stock:t},transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function v(e,t){return Object(c["a"])({url:"/goods/updateGoodsThumb",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e,imageUrl:t},transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function D(e){return Object(c["a"])({url:"/goods/delteGoods",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=d.a.stringify(e),e}]})}var w={getByGoodsId:g,getAllGoods:f,getGoodsListByPage:m,getStockByGoodsId:p,createNewGoods:b,updateGoods:h,updateStock:y,updateGoodsThumb:v,deleteGoods:D},k={name:"GoodsImageDialog",props:{dialogVisible:{type:Boolean,required:!0},goodsData:{type:Object,default:null,required:!1}},data:function(){return{imgDetailUrl:"",imgDetailDialogVisible:!1}},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(s["b"])())}}},watch:{dialogVisible:function(e,t){e||this.$refs.uploader.clearFiles()}},methods:{onSuccess:function(e,t,a){var o=this,n="/upload/images/".concat(e.message);w.updateGoodsThumb(this.goodsData.id,n).then((function(e){o.$message({message:"上传成功",type:"success"})}))},handleRemove:function(e,t){console.log(e,t)},handlePictureCardPreview:function(e){this.imgDetailUrl=e.url,this.imgDetailDialogVisible=!0},closeWindow:function(){this.$emit("update:dialogVisible",!1),this.$emit("update:goodsData",null)}}},x=k,G=(a("a5cd"),a("2877")),C=Object(G["a"])(x,i,l,!1,null,"3bfbf2c9",null),S=C.exports,_=a("c405"),L=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{staticStyle:{"text-align":"left"},attrs:{"close-on-click-modal":!1,"show-close":!1,visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}},scopedSlots:e._u([{key:"title",fn:function(){return[null===e.goodsData?a("h3",[e._v("添加商品")]):a("h3",[e._v("编辑商品")])]},proxy:!0}])},[a("el-form",{ref:"formRef",attrs:{model:e.formData,rules:e.rules,size:"mini"}},[a("el-form-item",{attrs:{label:"商品名称",prop:"name"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",{attrs:{label:"简介",prop:"desc"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.formData.desc,callback:function(t){e.$set(e.formData,"desc",t)},expression:"formData.desc"}})],1),a("el-form-item",{attrs:{label:"所属分类",prop:"categoryId"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.formData.categoryId,callback:function(t){e.$set(e.formData,"categoryId",t)},expression:"formData.categoryId"}},e._l(e.categoryList,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"商品标记"}},[a("el-checkbox-group",{model:{value:e.flagSelected,callback:function(t){e.flagSelected=t},expression:"flagSelected"}},[a("el-checkbox",{attrs:{label:"新品",name:"flag"}}),a("el-checkbox",{attrs:{label:"热门",name:"flag"}}),a("el-checkbox",{attrs:{label:"今日推荐",name:"flag"}})],1)],1),a("el-form-item",{attrs:{label:"价格",prop:"price"}},[a("el-input",{attrs:{autocomplete:"off",type:"number"},model:{value:e.formData.price,callback:function(t){e.$set(e.formData,"price",e._n(t))},expression:"formData.price"}})],1),a("el-form-item",{attrs:{label:"库存"}},[a("el-input",{attrs:{autocomplete:"off",type:"stock"},model:{value:e.formData.stock,callback:function(t){e.$set(e.formData,"stock",e._n(t))},expression:"formData.stock"}})],1),a("el-form-item",{attrs:{label:"月销"}},[a("el-input",{attrs:{autocomplete:"off",type:"number"},model:{value:e.formData.monthSale,callback:function(t){e.$set(e.formData,"monthSale",e._n(t))},expression:"formData.monthSale"}})],1),a("el-form-item",{attrs:{label:"评分",prop:"rate"}},[a("el-input",{attrs:{autocomplete:"off",type:"number"},model:{value:e.formData.rate,callback:function(t){e.$set(e.formData,"rate",e._n(t))},expression:"formData.rate"}})],1),a("el-form-item",{attrs:{label:"状态",prop:"state"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{label:"下架",value:"OFF"}}),a("el-option",{attrs:{label:"上架",value:"ON"}}),a("el-option",{attrs:{label:"缺货",value:"SHORTAGE"}})],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.closeWindow}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.sendLoading,expression:"sendLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{type:"primary"},on:{click:e.handleCreateNewGoods}},[e._v("确 定 ")])],1)],1)},V=[],$=(a("a15b"),a("ac1f"),a("5377"),a("1276"),{name:"GoodsDialog",props:{dialogVisible:{type:Boolean,required:!0},categoryList:{type:Array,required:!0},goodsData:{type:Object,default:null,required:!1}},data:function(){return{sendLoading:!1,formData:{},flagSelected:[],rules:{name:[{required:!0,message:"请输入商品名称",trigger:"blur"},{max:16,message:"最多16个字符",trigger:"blur"}],desc:[{max:50,message:"最多50个字符",trigger:"blur"}],categoryId:[{required:!0,message:"请选择分类",trigger:"blur"}],price:[{type:"number",required:!0,message:"请输入价格",trigger:"blur"}],rate:[{type:"number",min:0,max:10,message:"请输入0-10的数字",trigger:"blur"}],state:[{required:!0,message:"请选择状态",trigger:"blur"}]}}},watch:{dialogVisible:function(e,t){var a=this;e&&null!=this.goodsData&&(this.sendLoading=!0,w.getByGoodsId(this.goodsData.id).then((function(e){a.sendLoading=!1,a.formData=e,a.flagSelected=a.formData.flags.split(",")})).catch((function(){a.sendLoading=!1})))}},methods:{handleCreateNewGoods:function(){var e=this;this.$refs.formRef.validate((function(t){if(!t)return console.log("error submit!!"),!1;null===e.goodsData?e.createGoods():e.updateGoods()}))},createGoods:function(){var e=this;this.formData.flags=this.flagSelected.join(),this.sendLoading=!0,w.createNewGoods(this.formData).then((function(t){e.sendLoading=!1,e.$message({message:t.message,type:"success"}),e.$emit("event-success"),e.closeWindow()})).catch((function(){e.sendLoading=!1}))},updateGoods:function(){var e=this;this.formData.flags=this.flagSelected.join(),this.sendLoading=!0,w.updateGoods(this.formData).then((function(t){e.sendLoading=!1,e.$message({message:t.message,type:"success"}),e.$emit("event-success"),e.closeWindow()})).catch((function(){e.sendLoading=!1}))},closeWindow:function(){this.formData={},this.flagSelected=[],this.$emit("update:dialogVisible",!1),this.$emit("update:goodsData",null)}}}),O=$,j=Object(G["a"])(O,L,V,!1,null,"04a2f653",null),I=j.exports,q={name:"GoodsManagement",components:{BaseCard:r["a"],GoodsImageDialog:S,GoodsDialog:I},data:function(){return{page:{current:1,size:15,total:0},formData:{name:null,categoryId:null,state:null},imageUploaderVisible:!1,dialogVisible:!1,listLoading:!1,tableData:[],categoryList:[],currentGoods:null}},created:function(){var e=this;this.onSearch(),_["a"].getAllCategory().then((function(t){e.categoryList=t}))},methods:{getList:function(){var e=this;this.listLoading=!0,w.getGoodsListByPage(this.page,this.formData).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total),e.listLoading=!1})).catch((function(){e.listLoading=!1}))},onSearch:function(){this.page.current=1,this.getList()},onEdit:function(e){this.currentGoods=e,this.dialogVisible=!0},onDelete:function(e){var t=this;this.$confirm("是否确定删除此商品?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){w.deleteGoods(e).then((function(){t.getList()}))}))},onCreateNewGoods:function(){this.dialogVisible=!0},onUploadImg:function(e){this.currentGoods=e,this.imageUploaderVisible=!0},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()}}},B=q,P=(a("cc07"),a("1b55"),Object(G["a"])(B,o,n,!1,null,"34346a64",null));t["default"]=P.exports},"96f3":function(e,t,a){},a145:function(e,t,a){"use strict";var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},n=[],r={name:"BaseCard"},i=r,l=(a("179c"),a("2877")),s=Object(l["a"])(i,o,n,!1,null,"9e9f1e78",null);t["a"]=s.exports},a5cd:function(e,t,a){"use strict";var o=a("3b75"),n=a.n(o);n.a},c405:function(e,t,a){"use strict";var o=a("b775"),n=a("4328"),r=a.n(n);function i(){return Object(o["a"])({url:"/category/getAllCategory",method:"get"})}function l(e,t){var a=Object.assign({},e,t);return Object(o["a"])({url:"/category/getCategoryListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function s(e){return Object(o["a"])({url:"/category/createNewCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(o["a"])({url:"/category/updateCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(o["a"])({url:"/category/deleteCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getAllCategory:i,getCategoryListByPage:l,createNewCategory:s,updateCategory:c,deleteCategory:u}},cc07:function(e,t,a){"use strict";var o=a("6d32"),n=a.n(o);n.a}}]);