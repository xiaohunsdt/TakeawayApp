(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6627dcb4"],{"179c":function(e,t,a){"use strict";var n=a("198f"),r=a.n(n);r.a},"198f":function(e,t,a){},"709b":function(e,t,a){"use strict";var n=a("b775"),r=a("4328"),i=a.n(r);function o(e){return Object(n["a"])({url:"/banner/getBannerById",params:{id:e}})}function s(e,t){var a=Object.assign({},e,t);return Object(n["a"])({url:"/banner/getBannerListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=i.a.stringify(e),e}]})}function c(e){return Object(n["a"])({url:"/banner/createNewBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=i.a.stringify(e),e}]})}function u(e){return Object(n["a"])({url:"/banner/updateBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=i.a.stringify(e),e}]})}function l(e,t){var a={id:e,isShow:t};return Object(n["a"])({url:"/banner/changeIsShow",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=i.a.stringify(e),e}]})}function d(e){return Object(n["a"])({url:"/banner/deleteBanner",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=i.a.stringify(e),e}]})}t["a"]={getBannerById:o,getBannerListByPage:s,createNewBanner:c,updateBanner:u,changeIsShow:l,deleteBanner:d}},"7b0cd":function(e,t,a){"use strict";var n=a("9306"),r=a.n(n);r.a},9306:function(e,t,a){},"97af":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-main"},[a("div",[a("el-form",{ref:"form",staticStyle:{width:"460px"},attrs:{model:e.formData,rules:e.rules,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"横幅名称",prop:"title"}},[a("el-input",{model:{value:e.formData.title,callback:function(t){e.$set(e.formData,"title",t)},expression:"formData.title"}})],1),a("el-form-item",{attrs:{label:"跳转地址"}},[a("el-input",{model:{value:e.formData.pagePath,callback:function(t){e.$set(e.formData,"pagePath",t)},expression:"formData.pagePath"}})],1),a("el-form-item",{attrs:{label:"Index"}},[a("el-input",{model:{value:e.formData.index,callback:function(t){e.$set(e.formData,"index",e._n(t))},expression:"formData.index"}})],1),a("el-form-item",{attrs:{label:"横幅主图"}},[a("el-upload",{attrs:{action:e.$VUE_APP_BASE_API+"/api/admin/banner/uploadImg",headers:e.authHeader,"on-success":e.onUploadImgSuccess}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")])],1),""!==e.formData.img?a("img",{staticStyle:{height:"120px"},attrs:{src:e.$VUE_APP_BASE_API+e.formData.img}}):e._e()],1)],1)],1),a("div",{staticStyle:{"text-align":"center","margin-top":"15px"}},[a("el-button",{attrs:{round:"",size:"medium",type:"success"},on:{click:e.saveBanner}},[e._v("保存")])],1)])],1)},r=[],i=a("5f87"),o=a("ed08"),s=a("709b"),c=a("a145"),u={name:"EditBanner",components:{BaseCard:c["a"]},computed:{authHeader:function(){return{Authorization:"Bearer ".concat(Object(i["b"])())}}},data:function(){return{formData:{title:"",pagePath:"",img:"",index:0},rules:{title:[{required:!0,message:"请输入标题",trigger:"blur"}]}}},created:function(){this.init()},activated:function(){this.init()},methods:{init:function(){var e=this,t=Object(o["h"])();t.bannerId&&s["a"].getBannerById(t.bannerId).then((function(t){e.formData=t}))},onUploadImgSuccess:function(e,t,a){this.formData.img="/upload/images/banner/".concat(e.message)},saveBanner:function(){var e=this;this.$refs.form.validate((function(t){if(t){var a=Object.assign({},e.formData);s["a"].createNewBanner(a).then((function(t){e.$message({message:t.message,type:"success"})}))}}))}}},l=u,d=(a("7b0cd"),a("2877")),f=Object(d["a"])(l,n,r,!1,null,"665dfe10",null);t["default"]=f.exports},a145:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},r=[],i={name:"BaseCard"},o=i,s=(a("179c"),a("2877")),c=Object(s["a"])(o,n,r,!1,null,"9e9f1e78",null);t["a"]=c.exports}}]);