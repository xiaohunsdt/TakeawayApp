(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-14f0fda6"],{6233:function(t,e,i){},"67ec":function(t,e,i){},"734e":function(t,e,i){"use strict";var n=i("c939"),a=i.n(n);a.a},"8ed7":function(t,e,i){"use strict";var n=i("67ec"),a=i.n(n);a.a},a139:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"container"},[i("base-card",{staticClass:"container-main"},[i("div",[i("el-form",{ref:"form",staticStyle:{width:"460px"},attrs:{model:t.formData,rules:t.rules,"label-width":"80px"}},[i("el-form-item",{attrs:{label:"活动名称",prop:"title"}},[i("el-input",{model:{value:t.formData.title,callback:function(e){t.$set(t.formData,"title",e)},expression:"formData.title"}})],1),i("el-form-item",{attrs:{label:"活动主图"}},[i("el-upload",{attrs:{action:t.$VUE_APP_BASE_API+"/api/admin/activity/uploadImg",headers:t.authHeader,"on-success":t.onUploadImgSuccess}},[i("el-button",{attrs:{size:"small",type:"primary"}},[t._v("点击上传")])],1),""!==t.formData.mainImg?i("img",{staticStyle:{height:"120px"},attrs:{src:t.$VUE_APP_BASE_API+t.formData.mainImg}}):t._e()],1),i("el-form-item",{attrs:{label:"活动时间"}},[i("el-date-picker",{attrs:{"end-placeholder":"结束日期",format:"yyyy-MM-dd","start-placeholder":"开始日期",type:"daterange","value-format":"yyyy-MM-dd"},model:{value:t.formData.formDate,callback:function(e){t.$set(t.formData,"formDate",e)},expression:"formData.formDate"}})],1)],1)],1),i("div",[i("tinymce",{attrs:{height:300},model:{value:t.formData.content,callback:function(e){t.$set(t.formData,"content",e)},expression:"formData.content"}})],1),i("div",{staticStyle:{"text-align":"center","margin-top":"15px"}},[i("el-button",{attrs:{round:"",size:"medium",type:"success"},on:{click:t.saveActivity}},[t._v("保存")])],1)])],1)},a=[],o=i("5f87"),r=i("ed08"),c=i("ca41"),s=i("a145"),l=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"tinymce-container",class:{fullscreen:t.fullscreen},style:{width:t.containerWidth}},[i("textarea",{staticClass:"tinymce-textarea",attrs:{id:t.tinymceId}}),t._v(" "),i("div",{staticClass:"editor-custom-btn-container"},[i("editorImage",{staticClass:"editor-upload-btn",attrs:{color:"#1890ff"},on:{successCBK:t.imageSuccessCBK}})],1)])},u=[],d=(i("4160"),i("a9e3"),i("b680"),i("159b"),function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"upload-container"},[i("el-button",{style:{background:t.color,borderColor:t.color},attrs:{icon:"el-icon-upload",size:"mini",type:"primary"},on:{click:function(e){t.dialogVisible=!0}}},[t._v(" upload ")]),i("el-dialog",{attrs:{visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e}}},[i("el-upload",{staticClass:"editor-slide-upload",attrs:{multiple:!0,"file-list":t.fileList,"show-file-list":!0,"on-remove":t.handleRemove,"on-success":t.handleSuccess,"before-upload":t.beforeUpload,action:t.$VUE_APP_BASE_API+"/api/admin/activity/uploadImg",headers:t.authHeader,"list-type":"picture-card"}},[i("el-button",{attrs:{size:"small",type:"primary"}},[t._v(" Click upload ")])],1),i("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v(" Cancel ")]),i("el-button",{attrs:{type:"primary"},on:{click:t.handleSubmit}},[t._v(" Confirm ")])],1)],1)}),m=[],f=(i("a623"),i("d81d"),i("b64b"),i("d3b7"),i("3ca3"),i("ddb0"),i("2b3d"),{name:"EditorSlideUpload",props:{color:{type:String,default:"#1890ff"}},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(o["b"])())}}},data:function(){return{dialogVisible:!1,listObj:{},fileList:[]}},methods:{checkAllSuccess:function(){var t=this;return Object.keys(this.listObj).every((function(e){return t.listObj[e].hasSuccess}))},handleSubmit:function(){var t=this,e=Object.keys(this.listObj).map((function(e){return t.listObj[e]}));this.checkAllSuccess()?(this.$emit("successCBK",e),this.listObj={},this.fileList=[],this.dialogVisible=!1):this.$message("Please wait for all images to be uploaded successfully. If there is a network problem, please refresh the page and upload again!")},handleSuccess:function(t,e){for(var i=e.uid,n=Object.keys(this.listObj),a=0,o=n.length;a<o;a++)if(this.listObj[n[a]].uid===i)return this.listObj[n[a]].url=this.$VUE_APP_BASE_API+"/upload/images/activity/"+t.message,void(this.listObj[n[a]].hasSuccess=!0)},handleRemove:function(t){for(var e=t.uid,i=Object.keys(this.listObj),n=0,a=i.length;n<a;n++)if(this.listObj[i[n]].uid===e)return void delete this.listObj[i[n]]},beforeUpload:function(t){var e=this,i=window.URL||window.webkitURL,n=t.uid;return this.listObj[n]={},new Promise((function(a,o){var r=new Image;r.src=i.createObjectURL(t),r.onload=function(){e.listObj[n]={hasSuccess:!1,uid:t.uid,width:this.width,height:this.height}},a(!0)}))}}}),h=f,p=(i("8ed7"),i("2877")),y=Object(p["a"])(h,d,m,!1,null,"498990d2",null),b=y.exports,g=["advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount"],v=g,w=["searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample","hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen"],_=w,j=i("b85c"),k=[];function C(){return window.tinymce}var O=function(t,e){var i=document.getElementById(t),n=e||function(){};if(!i){var a=document.createElement("script");a.src=t,a.id=t,document.body.appendChild(a),k.push(n);var o="onload"in a?r:c;o(a)}function r(e){e.onload=function(){this.onerror=this.onload=null;var t,i=Object(j["a"])(k);try{for(i.s();!(t=i.n()).done;){var n=t.value;n(null,e)}}catch(a){i.e(a)}finally{i.f()}k=null},e.onerror=function(){this.onerror=this.onload=null,n(new Error("Failed to load "+t),e)}}function c(t){t.onreadystatechange=function(){if("complete"===this.readyState||"loaded"===this.readyState){this.onreadystatechange=null;var e,i=Object(j["a"])(k);try{for(i.s();!(e=i.n()).done;){var n=e.value;n(null,t)}}catch(a){i.e(a)}finally{i.f()}k=null}}}i&&n&&(C()?n(null,i):k.push(n))},A=O,D="https://cdn.jsdelivr.net/npm/tinymce-all-in-one@4.9.3/tinymce.min.js",I={name:"Tinymce",components:{editorImage:b},props:{id:{type:String,default:function(){return"vue-tinymce-"+ +new Date+(1e3*Math.random()).toFixed(0)}},value:{type:String,default:""},toolbar:{type:Array,required:!1,default:function(){return[]}},menubar:{type:String,default:"file edit insert view format table"},height:{type:[Number,String],required:!1,default:360},width:{type:[Number,String],required:!1,default:"auto"}},data:function(){return{hasChange:!1,hasInit:!1,tinymceId:this.id,fullscreen:!1,languageTypeList:{en:"en",zh:"zh_CN",es:"es_MX",ja:"ja"}}},computed:{containerWidth:function(){var t=this.width;return/^[\d]+(\.[\d]+)?$/.test(t)?"".concat(t,"px"):t}},watch:{value:function(t){var e=this;!this.hasChange&&this.hasInit&&this.$nextTick((function(){return window.tinymce.get(e.tinymceId).setContent(t||"")}))}},mounted:function(){this.init()},activated:function(){window.tinymce&&this.initTinymce()},deactivated:function(){this.destroyTinymce()},destroyed:function(){this.destroyTinymce()},methods:{init:function(){var t=this;A(D,(function(e){e?t.$message.error(e.message):t.initTinymce()}))},initTinymce:function(){var t=this,e=this;window.tinymce.init({selector:"#".concat(this.tinymceId),language:this.languageTypeList["en"],height:this.height,body_class:"panel-body ",object_resizing:!1,toolbar:this.toolbar.length>0?this.toolbar:_,menubar:this.menubar,plugins:v,end_container_on_empty_block:!0,powerpaste_word_import:"clean",code_dialog_height:450,code_dialog_width:1e3,advlist_bullet_styles:"square",advlist_number_styles:"default",imagetools_cors_hosts:["www.tinymce.com","codepen.io"],default_link_target:"_blank",link_title:!1,convert_urls:!1,nonbreaking_force_tab:!0,init_instance_callback:function(i){e.value&&i.setContent(e.value),e.hasInit=!0,i.on("NodeChange Change KeyUp SetContent",(function(){t.hasChange=!0,t.$emit("input",i.getContent())}))},setup:function(t){t.on("FullscreenStateChanged",(function(t){e.fullscreen=t.state}))}})},destroyTinymce:function(){var t=window.tinymce.get(this.tinymceId);this.fullscreen&&t.execCommand("mceFullScreen"),t&&t.destroy()},setContent:function(t){window.tinymce.get(this.tinymceId).setContent(t)},getContent:function(){window.tinymce.get(this.tinymceId).getContent()},imageSuccessCBK:function(t){var e=this;t.forEach((function(t){window.tinymce.get(e.tinymceId).insertContent('<img class="wscnph" src="'.concat(t.url,'" >'))}))}}},S=I,x=(i("734e"),Object(p["a"])(S,l,u,!1,null,"00fbc244",null)),T=x.exports,$={name:"EditActivity",components:{BaseCard:s["a"],Tinymce:T},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(o["b"])())}}},data:function(){return{formData:{title:"",content:"",mainImg:"",formDate:[new Date,new Date((new Date).getTime()+864e5)]},rules:{title:[{required:!0,message:"请输入标题",trigger:"blur"}]}}},created:function(){this.init()},activated:function(){this.init()},methods:{init:function(){var t=this,e=Object(r["h"])();e.activityId&&c["a"].getActivityById(e.activityId).then((function(e){t.formData=e,t.formData.formDate=[new Date(t.formData.startDate),new Date(t.formData.endDate)]}))},onUploadImgSuccess:function(t,e,i){this.formData.mainImg="/upload/images/activity/".concat(t.message)},saveActivity:function(){var t=this;this.$refs.form.validate((function(e){if(e){var i=Object.assign({},t.formData);i.startDate=Object(r["i"])(i.formDate[0],"{y}-{m}-{d}"),i.endDate=Object(r["i"])(i.formDate[1],"{y}-{m}-{d}"),c["a"].createNewActivity(i).then((function(e){t.$message({message:e.message,type:"success"})}))}}))}}},B=$,E=(i("b779"),Object(p["a"])(B,n,a,!1,null,"596045ab",null));e["default"]=E.exports},b779:function(t,e,i){"use strict";var n=i("6233"),a=i.n(n);a.a},c939:function(t,e,i){},ca41:function(t,e,i){"use strict";var n=i("b775"),a=(i("ed08"),i("4328")),o=i.n(a);function r(t){return Object(n["a"])({url:"/activity/getActivityById",params:{id:t}})}function c(t,e){var i=Object.assign({},t,e);return Object(n["a"])({url:"/activity/getActivityListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:i,transformRequest:[function(t){return t=o.a.stringify(t),t}]})}function s(){return Object(n["a"])({url:"/activity/getAllActivityList",method:"get",headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function l(t){return Object(n["a"])({url:"/activity/createNewActivity",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(t){return t=o.a.stringify(t),t}]})}function u(t){return Object(n["a"])({url:"/activity/updateActivity",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(t){return t=o.a.stringify(t),t}]})}function d(t,e){var i={id:t,isShow:e};return Object(n["a"])({url:"/activity/changeIsShow",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:i,transformRequest:[function(t){return t=o.a.stringify(t),t}]})}function m(t){return Object(n["a"])({url:"/activity/deleteActivity",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:t},transformRequest:[function(t){return t=o.a.stringify(t),t}]})}e["a"]={getActivityById:r,getActivityListByPage:c,getAllActivityList:s,createNewActivity:l,updateActivity:u,changeIsShow:d,deleteActivity:m}}}]);