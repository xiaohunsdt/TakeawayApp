(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0e593311"],{"07ac":function(e,t,a){var n=a("23e7"),o=a("6f53").values;n({target:"Object",stat:!0},{values:function(e){return o(e)}})},"179c":function(e,t,a){"use strict";var n=a("96f3"),o=a.n(n);o.a},"260c":function(e,t,a){},"28ca":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-header"},[a("el-row",[a("el-col",{attrs:{span:18}},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.formData,size:"mini"}},[a("el-form-item",{attrs:{label:"名称"}},[a("el-input",{attrs:{placeholder:"商品名称"},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1),a("el-form-item",{attrs:{label:"分类"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.formData.categoryId,callback:function(t){e.$set(e.formData,"categoryId",t)},expression:"formData.categoryId"}},[a("el-option",{attrs:{label:" ",value:""}}),e._l(e.categoryList,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],2)],1),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{placeholder:"选择状态"},model:{value:e.formData.state,callback:function(t){e.$set(e.formData,"state",t)},expression:"formData.state"}},[a("el-option",{attrs:{label:"全部",value:null}}),a("el-option",{attrs:{label:"上架",value:"1"}}),a("el-option",{attrs:{label:"下架",value:"0"}})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSearch}},[e._v("查询")])],1)],1)],1),a("el-col",{staticStyle:{"text-align":"right"},attrs:{span:6}},[a("el-button",{attrs:{size:"mini",type:"success"},on:{click:e.onCreateNewGoods}},[e._v("添加新商品")])],1)],1)],1),a("base-card",{staticClass:"container-main"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"tb-edit",staticStyle:{width:"100%"},attrs:{data:e.tableData,"element-loading-text":"正在加载中...","highlight-current-row":"",stripe:""}},[a("el-table-column",{attrs:{align:"center",label:"ID",prop:"id"}}),a("el-table-column",{attrs:{align:"center",label:"名称",prop:"name"}}),a("el-table-column",{attrs:{align:"center",label:"主图",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[""===t.row.thumb?a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onUploadImg(t.row)}}},[e._v(" 上传 ")]):a("img",{staticStyle:{height:"30px",width:"auto"},attrs:{src:e.$VUE_APP_BASE_API+t.row.thumb},on:{click:function(a){return e.onUploadImg(t.row)}}})]}}])}),a("el-table-column",{attrs:{align:"center",label:"简介",prop:"desc",width:"250"}}),a("el-table-column",{attrs:{align:"center",label:"分类",prop:"category"}}),a("el-table-column",{attrs:{align:"center",label:"商品数量",prop:"goodsCount"}}),a("el-table-column",{attrs:{align:"center",label:"优先级",prop:"index"}}),a("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(e._f("produceStateFormat")(t.row.state))+" ")]}}])}),a("el-table-column",{attrs:{align:"center",label:"操作",width:"170"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.onEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.onDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"15px"},attrs:{"current-page":e.page.current,"page-size":e.page.size,"page-sizes":[15,50,100],total:e.page.total,background:"",layout:"total, sizes, prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),a("goods-dialog",{ref:"goods-dialog",on:{"event-success":e.onSearch}}),a("goods-image-dialog",{attrs:{dialogVisible:e.imageUploaderVisible,goodsData:e.currentGoods},on:{"update:dialogVisible":function(t){e.imageUploaderVisible=t},"update:dialog-visible":function(t){e.imageUploaderVisible=t},"update:goodsData":function(t){e.currentGoods=t},"update:goods-data":function(t){e.currentGoods=t}}})],1)},o=[],r=(a("d3b7"),a("a145")),i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{"close-on-click-modal":!1,"show-close":!1,visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-upload",{ref:"uploader",attrs:{action:e.$VUE_APP_BASE_API+"/api/admin/uploadImg",headers:e.authHeader,"on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove,"on-success":e.onSuccess,"list-type":"picture-card"}},[a("i",{staticClass:"el-icon-plus"})]),a("el-dialog",{attrs:{visible:e.imgDetailDialogVisible},on:{"update:visible":function(t){e.imgDetailDialogVisible=t}}},[a("img",{attrs:{src:e.imgDetailUrl,alt:"",width:"100%"}})]),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.closeWindow}},[e._v("关闭")])],1)],1)},s=[],l=a("5f87"),c=(a("4160"),a("d81d"),a("159b"),a("b775")),u=a("4328"),d=a.n(u);function p(e){return Object(c["a"])({url:"/produce/getById",params:{id:e}})}function f(e){return Object(c["a"])({url:"/produce/getDetailById",params:{id:e}})}function g(){return Object(c["a"])({url:"/produce/getAll",method:"get"})}function m(e,t){var a=Object.assign({},e,t);return Object(c["a"])({url:"/produce/getListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function h(e,t,a){return Object(c["a"])({url:"/produce/create",method:"post",headers:{"Content-Type":"application/json"},data:{produce:e,specs:w(t),goodsList:D(a)}})}function b(e,t,a){return Object(c["a"])({url:"/produce/update",method:"post",headers:{"Content-Type":"application/json"},data:{produce:e,specs:w(t),goodsList:D(a)}})}function v(e,t){return Object(c["a"])({url:"/produce/updateThumb",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e,imageUrl:t},transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function y(e){return Object(c["a"])({url:"/produce/delete",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=d.a.stringify(e),e}]})}function w(e){var t={},a={};return e.forEach((function(e){a[e.id]=e.key,t[e.id]=e.params.map((function(e){return e.value}))})),{selectedSpecs:JSON.stringify(a),options:JSON.stringify(t)}}function D(e){var t=[];return e.forEach((function(e){var a=Object.assign({},e);a.ownSpecs&&(a.ownSpecs=JSON.stringify(a.ownSpecs)),t.push(a)})),t}var S={getById:p,getDetailById:f,getAll:g,getListByPage:m,create:h,update:b,updateThumb:v,del:y,formatSpecs:w},k={name:"GoodsImageDialog",props:{dialogVisible:{type:Boolean,required:!0},goodsData:{type:Object,default:null,required:!1}},data:function(){return{imgDetailUrl:"",imgDetailDialogVisible:!1}},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(l["c"])())}}},watch:{dialogVisible:function(e,t){e||this.$refs.uploader.clearFiles()}},methods:{onSuccess:function(e,t,a){var n=this,o="/upload/images/".concat(e.message);S.updateThumb(this.goodsData.id,o).then((function(e){n.$message.success("上传成功")}))},handleRemove:function(e,t){console.log(e,t)},handlePictureCardPreview:function(e){this.imgDetailUrl=e.url,this.imgDetailDialogVisible=!0},closeWindow:function(){this.$emit("update:dialogVisible",!1),this.$emit("update:goodsData",null)}}},x=k,_=(a("5d69"),a("2877")),L=Object(_["a"])(x,i,s,!1,null,"5f193909",null),O=L.exports,C=a("c405"),j=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{staticStyle:{"text-align":"left"},attrs:{"close-on-click-modal":!1,"show-close":!1,visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}},scopedSlots:e._u([{key:"title",fn:function(){return[null===e.produce?a("h3",[e._v("添加商品")]):a("h3",[e._v("编辑商品")])]},proxy:!0}])},[a("el-steps",{attrs:{active:e.active,"align-center":"","finish-status":"success"}},[a("el-step",{attrs:{title:"基本信息"}}),a("el-step",{attrs:{title:"规格设置"}}),a("el-step",{attrs:{title:"SKU设置"}})],1),a("div",{staticStyle:{"margin-top":"50px"}},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:0===e.active,expression:"active===0"}],ref:"produce-form",attrs:{model:e.produceData,rules:e.rules,size:"mini","status-icon":""}},[a("base-card",[a("el-form-item",{attrs:{label:"商品名称",prop:"name"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.produceData.name,callback:function(t){e.$set(e.produceData,"name",t)},expression:"produceData.name"}})],1),a("el-form-item",{attrs:{label:"简介",prop:"desc"}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.produceData.desc,callback:function(t){e.$set(e.produceData,"desc",t)},expression:"produceData.desc"}})],1),a("el-form-item",{attrs:{label:"所属分类",prop:"categoryId"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.produceData.categoryId,callback:function(t){e.$set(e.produceData,"categoryId",t)},expression:"produceData.categoryId"}},e._l(e.categoryList,(function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1),a("el-form-item",{attrs:{label:"商品标记"}},[a("el-checkbox-group",{model:{value:e.flagSelected,callback:function(t){e.flagSelected=t},expression:"flagSelected"}},[a("el-checkbox",{attrs:{label:"新品",name:"flag"}}),a("el-checkbox",{attrs:{label:"热门",name:"flag"}}),a("el-checkbox",{attrs:{label:"今日推荐",name:"flag"}})],1)],1),a("el-form-item",{attrs:{label:"优先级",prop:"rate"}},[a("el-input",{attrs:{autocomplete:"off",type:"number"},model:{value:e.produceData.index,callback:function(t){e.$set(e.produceData,"index",e._n(t))},expression:"produceData.index"}})],1),a("el-form-item",{attrs:{label:"状态",prop:"state"}},[a("el-select",{attrs:{placeholder:"选择分类"},model:{value:e.produceData.state,callback:function(t){e.$set(e.produceData,"state",t)},expression:"produceData.state"}},[a("el-option",{attrs:{label:"下架",value:"OFF"}}),a("el-option",{attrs:{label:"上架",value:"ON"}})],1)],1)],1)],1),a("el-form",{directives:[{name:"show",rawName:"v-show",value:1===e.active,expression:"active===1"}],attrs:{model:e.specData,size:"mini"}},[a("base-card",[a("h2",[e._v("设置规格")]),a("el-form-item",{attrs:{label:"规格"}},[a("el-select",{attrs:{placeholder:"规格","value-key":"id"},model:{value:e.specData.currentSpec,callback:function(t){e.$set(e.specData,"currentSpec",t)},expression:"specData.currentSpec"}},e._l(e.specList,(function(e){return a("el-option",{key:e.id,attrs:{label:e.key,value:e}})})),1),a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary"},on:{click:function(t){return e.addSpec()}}},[e._v(" 添加 ")])],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.specData.selected}},[a("el-table-column",{attrs:{label:"规格"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.key)+" ")]}}])}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.deleteSpec(t.row)}}},[e._v(" 删除 ")])]}}])})],1)],1),e.specData.selected.length>0?a("base-card",{staticStyle:{"margin-top":"50px"}},[a("h2",[e._v("设置参数")]),a("el-row",{attrs:{gutter:20}},e._l(e.specData.selected,(function(e,t){return a("el-col",{key:e.id,attrs:{span:8}},[a("dynamic-input",{attrs:{label:e.key,"model-array":e.params,"rule-model-name":"selected."+t+".params"}})],1)})),1)],1):e._e()],1),2===e.active?a("div",[a("base-card",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.goodsList,"max-height":"600px"}},[e._l(e.specData.selected,(function(t){return a("el-table-column",{key:t.key,attrs:{label:t.key},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v(" "+e._s(a.row.ownSpecs[t.id])+" ")]}}],null,!0)})})),a("el-table-column",{attrs:{label:"价格"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:"请输入价格",size:"small"},model:{value:t.row.price,callback:function(a){e.$set(t.row,"price",e._n(a))},expression:"scope.row.price"}})]}}],null,!1,2182609019)}),a("el-table-column",{attrs:{label:"库存"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-tooltip",{attrs:{content:"-1表示无限库存",placement:"right"}},[a("el-input",{attrs:{placeholder:"请输入库存",size:"small"},model:{value:t.row.stock,callback:function(a){e.$set(t.row,"stock",e._n(a))},expression:"scope.row.stock"}})],1)]}}],null,!1,352864162)}),a("el-table-column",{attrs:{label:"可用"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{attrs:{placeholder:"选择状态"},model:{value:t.row.state,callback:function(a){e.$set(t.row,"state",a)},expression:"scope.row.state"}},[a("el-option",{attrs:{label:"可用",value:"ON"}}),a("el-option",{attrs:{label:"不可用",value:"OFF"}}),a("el-option",{attrs:{label:"缺货",value:"SHORTAGE"}})],1)]}}],null,!1,1285626475)})],2)],1)],1):e._e()],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.closeWindow}},[e._v("取 消")]),0!==e.active?a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.previous()}}},[e._v(" 上一步 ")]):e._e(),2!==e.active?a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.next()}}},[e._v(" 下一步 ")]):e._e(),2===e.active?a("el-button",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.sendLoading,expression:"sendLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{type:"primary"},on:{click:e.handleCreateNewGoods}},[null===e.produce?a("div",[e._v("创建")]):a("div",[e._v("修改")])]):e._e()],1)],1)},$=[],I=(a("99af"),a("c740"),a("a15b"),a("13d5"),a("a434"),a("07ac"),a("ac1f"),a("5377"),a("1276"),a("b85c")),A=a("2909"),V=(a("96cf"),a("1da1")),T=a("6618"),B=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"dynamic-input",staticStyle:{"max-width":"200px"}},[0===e.modelArray.length?a("el-form-item",{attrs:{label:e.label}},[a("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"mini",type:"success"},on:{click:function(t){return t.preventDefault(),e.addModel(t)}}})],1):e._e(),e._l(e.modelArray,(function(t,n){return a("el-form-item",{key:t.key,attrs:{label:e.label+"("+n+")",prop:e.ruleModelName+"."+n+".value",rules:{required:!0,message:"不能为空",trigger:"blur"}}},[a("el-input",{on:{change:e.onValueChanged},model:{value:t.value,callback:function(a){e.$set(t,"value",a)},expression:"model.value"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-delete"},on:{click:function(a){return a.preventDefault(),e.removeModel(t)}},slot:"append"})],1)],1)})),a("div",{staticStyle:{"text-align":"center"}},[e.modelArray.length>0?a("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"mini",type:"success"},on:{click:function(t){return t.preventDefault(),e.addModel(t)}}}):e._e()],1)],2)},z=[],q=(a("c975"),{name:"DynamicInput",props:{modelArray:{type:Array,required:!0},label:{type:String,required:!0},ruleModelName:{type:String,required:!0}},methods:{onValueChanged:function(e){this.$emit("update:modelArray",this.modelArray)},addModel:function(){this.modelArray.push({key:(new Date).getTime(),value:""})},removeModel:function(e){var t=this.modelArray.indexOf(e);-1!==t&&this.modelArray.splice(t,1)}}}),G=q,E=(a("5222"),a("41dc"),Object(_["a"])(G,B,z,!1,null,"13fde318",null)),N=E.exports,P=a("ed08"),R={name:"GoodsDialog",components:{BaseCard:r["a"],DynamicInput:N},watch:{active:function(e){2===e&&this.formatGoodsList()}},data:function(){return{dialogVisible:!1,sendLoading:!1,active:0,produce:null,categoryList:[],specList:[],produceData:{},specData:{currentSpec:null,selected:[]},goodsList:[],flagSelected:[],rules:{name:[{required:!0,message:"请输入商品名称",trigger:"blur"},{max:16,message:"最多16个字符",trigger:"blur"}],desc:[{max:50,message:"最多50个字符",trigger:"blur"}],categoryId:[{required:!0,message:"请选择分类",trigger:"change"}],price:[{type:"number",required:!0,message:"请输入价格",trigger:"blur"}],rate:[{type:"number",min:0,max:10,message:"请输入0-10的数字",trigger:"blur"}],state:[{required:!0,message:"请选择状态",trigger:"change"}]}}},methods:{handleCreateNewGoods:function(){null===this.produce?this.createGoods():this.updateGoods()},createGoods:function(){var e=this;this.produceData.flags=this.flagSelected.join(),this.sendLoading=!0,S.create(this.produceData,this.specData.selected,this.goodsList).then((function(t){e.$message.success(t.message),e.$emit("event-success"),e.closeWindow()})).finally((function(){e.sendLoading=!1}))},updateGoods:function(){var e=this;this.produceData.flags=this.flagSelected.join(),this.sendLoading=!0,S.update(this.produceData,this.specData.selected,this.goodsList).then((function(t){e.$message.success(t.message),e.$emit("event-success"),e.closeWindow()})).finally((function(){e.sendLoading=!1}))},openWindow:function(e,t){var a=this;return Object(V["a"])(regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return a.categoryList=t,a.dialogVisible=!0,n.next=4,T["a"].getAll().then((function(e){e.forEach((function(e){e.params=[]})),a.specList=e}));case 4:e&&(a.sendLoading=!0,S.getDetailById(e.id).then((function(e){e.goodsList.forEach((function(e){""!==e.ownSpecs&&(e.ownSpecs=JSON.parse(e.ownSpecs))})),a.produce=e,a.produceData=e.produce,a.flagSelected=e.produce.flags.split(",");var t=JSON.parse(e.specs.options),n=function(e){var n=a.specList.findIndex((function(t){return t.id===e}));if(n>=0){var o,r=t[e].map((function(e){return{key:(new Date).getTime()+Math.floor(100*Math.random()),value:e}}));(o=a.specList[n].params).push.apply(o,Object(A["a"])(r)),a.specData.selected.push(a.specList[n])}};for(var o in t)n(o)})).finally((function(){a.sendLoading=!1})));case 5:case"end":return n.stop()}}),n)})))()},closeWindow:function(){this.dialogVisible=!1,this.active=0,this.produce=null,this.produceData={},this.specData={currentSpec:null,selected:[]},this.flagSelected=[],this.$emit("update:dialogVisible",!1),this.$emit("update:produce",null)},addSpec:function(){if(this.specData.currentSpec){var e=this.specData.currentSpec,t=this.specData.selected.findIndex((function(t){return t.id===e.id}));t>=0?this.$message.error("已经添加此规格"):this.specData.selected.push(e)}else this.$message.error("请先选择规格")},deleteSpec:function(e){var t=this.specData.selected.findIndex((function(t){return t.id===e.id}));console.log(t),t>=0&&this.specData.selected.splice(t,1)},previous:function(){this.active--},next:function(){var e=this;if(0===this.active)this.$refs["produce-form"].validate((function(t){t&&e.active++}));else if(1===this.active){if(this.specData.selected.length>0){var t,a=Object(I["a"])(this.specData.selected);try{for(a.s();!(t=a.n()).done;){var n=t.value;if(0===n.params.length)return void this.$message.warning("请将规格参数填写完整!")}}catch(o){a.e(o)}finally{a.f()}}this.produce&&S.formatSpecs(this.specData.selected).options!==this.produce.specs.options?this.$confirm("此产品的规格参数发生改变,之前设置的sku将全部作废,是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){e.active++})):this.active++}else this.active++},formatGoodsList:function(){var e=this;this.goodsList=[];var t,a=[],n=this.specData.selected.map((function(e){return e.params.map((function(t){var a={};return a[e.id]=t.value,a}))}));if(a=n.length<2?n[0]||[]:n.reduce((function(e,t){var a=[];return e.forEach((function(e){t.forEach((function(t){e instanceof Array?a.push([].concat(Object(A["a"])(e),[t])):a.push([e,t])}))})),a})),a.length>0)for(var o=function(t){var n=a[t];n instanceof Array&&(n=n.reduce((function(e,t){return Object.assign(e,t)}),{}));var o=void 0;if(e.produce){var r=e.produce.goodsList.findIndex((function(e){return Object(P["j"])(e.ownSpecs,n)}));r>=0&&(o=Object.assign({},e.produce.goodsList[r]))}o||(o={},o.ownSpecs=n,o.price=0,o.stock=-1,o.state="OFF"),o.title=Object.values(n).join(" "),e.goodsList.push(o)},r=0;r<a.length;r++)o(r);else this.produce&&1===this.produce.goodsList.length?t=Object.assign({},this.produce.goodsList[0]):(t={},t.price=0,t.stock=-1,t.state="OFF"),t.title="",t.ownSpecs=null,this.goodsList.push(t)}}},U=R,F=Object(_["a"])(U,j,$,!1,null,"5b321590",null),M=F.exports,W={name:"GoodsManagement",components:{BaseCard:r["a"],GoodsImageDialog:O,GoodsDialog:M},filters:{produceStateFormat:function(e){return Object(P["h"])(e)}},data:function(){return{page:{current:1,size:15,total:0},formData:{name:null,categoryId:null,state:null},imageUploaderVisible:!1,dialogVisible:!1,listLoading:!1,tableData:[],categoryList:[],currentGoods:null}},created:function(){var e=this;this.onSearch(),C["a"].getAllCategory().then((function(t){e.categoryList=t}))},methods:{getList:function(){var e=this;this.listLoading=!0,S.getListByPage(this.page,this.formData).then((function(t){e.tableData=t.records,e.page.total=parseInt(t.total)})).finally((function(){e.listLoading=!1}))},onSearch:function(){this.page.current=1,this.getList()},onEdit:function(e){this.$refs["goods-dialog"].openWindow(e,this.categoryList)},onDelete:function(e){var t=this;this.$confirm("是否确定删除此商品?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){S.del(e).then((function(){t.getList()}))}))},onCreateNewGoods:function(){this.$refs["goods-dialog"].openWindow(null,this.categoryList)},onUploadImg:function(e){this.currentGoods=e,this.imageUploaderVisible=!0},handleSizeChange:function(e){this.page.size=e,this.getList()},handleCurrentChange:function(e){this.page.current=e,this.getList()}}},J=W,H=(a("60c8"),a("73e4"),Object(_["a"])(J,n,o,!1,null,"2aaeee4e",null));t["default"]=H.exports},"41dc":function(e,t,a){"use strict";var n=a("b852"),o=a.n(n);o.a},5222:function(e,t,a){"use strict";var n=a("260c"),o=a.n(n);o.a},5377:function(e,t,a){var n=a("83ab"),o=a("9bf2"),r=a("ad6d"),i=a("9f7f").UNSUPPORTED_Y;n&&("g"!=/./g.flags||i)&&o.f(RegExp.prototype,"flags",{configurable:!0,get:r})},"5d69":function(e,t,a){"use strict";var n=a("94fe"),o=a.n(n);o.a},"60c8":function(e,t,a){"use strict";var n=a("8add"),o=a.n(n);o.a},6618:function(e,t,a){"use strict";var n=a("b775"),o=a("4328"),r=a.n(o);function i(){return Object(n["a"])({url:"/spec/getAll",method:"get"})}function s(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/spec/getListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function l(e){return Object(n["a"])({url:"/spec/create",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{key:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/spec/update",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/spec/delete",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getAll:i,getListByPage:s,create:l,update:c,del:u}},"6f53":function(e,t,a){var n=a("83ab"),o=a("df75"),r=a("fc6a"),i=a("d1e7").f,s=function(e){return function(t){var a,s=r(t),l=o(s),c=l.length,u=0,d=[];while(c>u)a=l[u++],n&&!i.call(s,a)||d.push(e?[a,s[a]]:s[a]);return d}};e.exports={entries:s(!0),values:s(!1)}},"73e4":function(e,t,a){"use strict";var n=a("7887"),o=a.n(n);o.a},7887:function(e,t,a){},"8add":function(e,t,a){},"94fe":function(e,t,a){},"96f3":function(e,t,a){},a145:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},o=[],r={name:"BaseCard"},i=r,s=(a("179c"),a("2877")),l=Object(s["a"])(i,n,o,!1,null,"9e9f1e78",null);t["a"]=l.exports},b852:function(e,t,a){},c405:function(e,t,a){"use strict";var n=a("b775"),o=a("4328"),r=a.n(o);function i(){return Object(n["a"])({url:"/category/getAllCategory",method:"get"})}function s(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/category/getCategoryListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function l(e){return Object(n["a"])({url:"/category/createNewCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/category/updateCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/category/deleteCategory",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getAllCategory:i,getCategoryListByPage:s,createNewCategory:l,updateCategory:c,deleteCategory:u}}}]);