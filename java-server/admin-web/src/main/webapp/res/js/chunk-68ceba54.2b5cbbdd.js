(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68ceba54"],{2017:function(e,t,n){"use strict";var o=n("cafe"),a=n.n(o);a.a},6313:function(e,t,n){},"9ed6":function(e,t,n){"use strict";n.r(t);var o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"login-container"},[n("el-form",{staticClass:"login-form",attrs:{model:e.loginForm,"auto-complete":"on","label-position":"left"}},[n("div",{staticClass:"title-container"},[n("h3",{staticClass:"title"},[e._v("点餐管理系统")])]),n("el-form-item",[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"user"}})],1),n("el-input",{attrs:{placeholder:"UserName",name:"userName",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.userName,callback:function(t){e.$set(e.loginForm,"userName",t)},expression:"loginForm.userName"}})],1),n("el-form-item",[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"password"}})],1),n("el-input",{key:"password",attrs:{name:"password",type:"password",placeholder:"Password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleLogin(t)}},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}})],1),n("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleLogin(t)}}},[e._v(" Login ")])],1)],1)},a=[],i={name:"Login",data:function(){return{loginForm:{userName:"",password:""},loading:!1,redirect:void 0}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},methods:{handleLogin:function(){var e=this;this.loading=!0,this.$store.dispatch("user/login",this.loginForm).then((function(){e.$router.push({path:e.redirect||"/"}),e.loading=!1})).catch((function(){e.loading=!1}))}}},s=i,r=(n("2017"),n("c3c3"),n("2877")),l=Object(r["a"])(s,o,a,!1,null,"16130b62",null);t["default"]=l.exports},c3c3:function(e,t,n){"use strict";var o=n("6313"),a=n.n(o);a.a},cafe:function(e,t,n){}}]);