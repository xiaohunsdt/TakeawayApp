(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1771e0af"],{2017:function(t,e,n){"use strict";var o=n("c961"),a=n.n(o);a.a},6242:function(t,e,n){},"9ed6":function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"login-container"},[n("el-form",{staticClass:"login-form",attrs:{model:t.loginForm,"auto-complete":"on","label-position":"left"}},[n("div",{staticClass:"title-container"},[n("h3",{staticClass:"title"},[t._v("点餐管理系统")])]),n("el-form-item",[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"user"}})],1),n("el-input",{attrs:{placeholder:"UserName",name:"userName",type:"text",tabindex:"1","auto-complete":"on"},model:{value:t.loginForm.userName,callback:function(e){t.$set(t.loginForm,"userName",e)},expression:"loginForm.userName"}})],1),n("el-form-item",[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"password"}})],1),n("el-input",{key:"password",attrs:{name:"password",type:"password",placeholder:"Password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.handleLogin(e)}},model:{value:t.loginForm.password,callback:function(e){t.$set(t.loginForm,"password",e)},expression:"loginForm.password"}})],1),n("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:t.loading,type:"primary"},nativeOn:{click:function(e){return e.preventDefault(),t.handleLogin(e)}}},[t._v(" Login ")])],1)],1)},a=[],i={name:"Login",data:function(){return{loginForm:{userName:"",password:""},loading:!1,redirect:void 0}},watch:{$route:{handler:function(t){this.redirect=t.query&&t.query.redirect},immediate:!0}},methods:{handleLogin:function(){var t=this;this.loading=!0,this.$store.dispatch("user/login",this.loginForm).then((function(){t.$router.push({path:t.redirect||"/"}),t.loading=!1})).catch((function(){t.loading=!1}))}}},s=i,r=(n("2017"),n("c3c3"),n("2877")),l=Object(r["a"])(s,o,a,!1,null,"16130b62",null);e["default"]=l.exports},c3c3:function(t,e,n){"use strict";var o=n("6242"),a=n.n(o);a.a},c961:function(t,e,n){}}]);