(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ee94051a"],{"0bb6":function(e,t,a){},"0cba":function(e,t,a){"use strict";var o=a("82a9"),n=a.n(o);n.a},"179c":function(e,t,a){"use strict";var o=a("1cfa"),n=a.n(o);n.a},"1cfa":function(e,t,a){},"4e5c":function(e,t,a){"use strict";var o=a("c7ea"),n=a.n(o);n.a},"82a9":function(e,t,a){},"8b0a":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("base-card",{staticClass:"container-main"},[a("div",{staticStyle:{display:"flex","justify-content":"center"}},[a("el-form",{ref:"form",staticStyle:{display:"flex","justify-content":"center",width:"920px"},attrs:{model:e.formData,"label-width":"120px"}},[a("div",{staticStyle:{width:"460px"}},[a("el-form-item",{attrs:{rules:{required:!0,message:"请输入标题",trigger:"blur"},label:"优惠卷名称",prop:"couponName"}},[a("el-input",{model:{value:e.formData.couponName,callback:function(t){e.$set(e.formData,"couponName",t)},expression:"formData.couponName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"优惠卷类型"}},[a("el-select",{attrs:{placeholder:"请选择优惠卷类型"},model:{value:e.formData.couponType,callback:function(t){e.$set(e.formData,"couponType",t)},expression:"formData.couponType"}},[a("el-option",{attrs:{label:"现金卷",value:"MONEY"}}),e._v(" "),a("el-option",{attrs:{label:"折扣卷",value:"DISCOUNT"}})],1)],1),e._v(" "),"MONEY"===e.formData.couponType?a("el-form-item",{attrs:{label:"优惠卷面值"}},[a("el-input",{model:{value:e.formData.couponMoney,callback:function(t){e.$set(e.formData,"couponMoney",e._n(t))},expression:"formData.couponMoney"}})],1):e._e(),e._v(" "),"DISCOUNT"===e.formData.couponType?a("el-form-item",{attrs:{label:"优惠卷折扣"}},[a("el-input",{model:{value:e.formData.couponDiscount,callback:function(t){e.$set(e.formData,"couponDiscount",e._n(t))},expression:"formData.couponDiscount"}})],1):e._e(),e._v(" "),a("el-form-item",{attrs:{label:"最低消费"}},[a("el-input",{model:{value:e.formData.minimumMoney,callback:function(t){e.$set(e.formData,"minimumMoney",e._n(t))},expression:"formData.minimumMoney"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"过期天数"}},[a("el-input",{model:{value:e.formData.expireDays,callback:function(t){e.$set(e.formData,"expireDays",e._n(t))},expression:"formData.expireDays"}})],1)],1),e._v(" "),a("el-divider",{attrs:{direction:"vertical"}}),e._v(" "),a("div",{staticStyle:{width:"460px"}},[a("dynamic-input",{attrs:{"model-array":e.formData.allowCategory,label:"允许的分类","rule-model-name":"allowCategory"},on:{"update:modelArray":function(t){return e.$set(e.formData,"allowCategory",t)},"update:model-array":function(t){return e.$set(e.formData,"allowCategory",t)}}}),e._v(" "),a("dynamic-input",{attrs:{"model-array":e.formData.limitCategory,label:"禁止的分类","rule-model-name":"limitCategory"},on:{"update:modelArray":function(t){return e.$set(e.formData,"limitCategory",t)},"update:model-array":function(t){return e.$set(e.formData,"limitCategory",t)}}}),e._v(" "),a("dynamic-input",{attrs:{"model-array":e.formData.allowGoods,label:"禁止的商品","rule-model-name":"allowGoods"},on:{"update:modelArray":function(t){return e.$set(e.formData,"allowGoods",t)},"update:model-array":function(t){return e.$set(e.formData,"allowGoods",t)}}}),e._v(" "),a("dynamic-input",{attrs:{"model-array":e.formData.limitGoods,label:"允许的商品","rule-model-name":"limitGoods"},on:{"update:modelArray":function(t){return e.$set(e.formData,"limitGoods",t)},"update:model-array":function(t){return e.$set(e.formData,"limitGoods",t)}}})],1)],1)],1),e._v(" "),a("div",{staticStyle:{"text-align":"center","margin-top":"15px"}},[a("el-button",{attrs:{round:"",size:"medium",type:"success"},on:{click:e.saveTemplate}},[e._v("保存")])],1)])],1)},n=[],r=(a("ac6a"),a("ed08")),l=a("c5bb"),i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"dynamic-input"},[0===e.modelArray.length?a("el-form-item",{attrs:{label:e.label}},[a("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"mini",type:"success"},on:{click:function(t){return t.preventDefault(),e.addModel(t)}}})],1):e._e(),e._v(" "),e._l(e.modelArray,(function(t,o){return a("el-form-item",{key:t.key,attrs:{label:e.label+"("+o+")",prop:e.ruleModelName+"."+o+".value",rules:{required:!0,message:"不能为空",trigger:"blur"}}},[a("el-input",{on:{change:e.onValueChanged},model:{value:t.value,callback:function(a){e.$set(t,"value",a)},expression:"model.value"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-delete"},on:{click:function(a){return a.preventDefault(),e.removeModel(t)}},slot:"append"})],1)],1)})),e._v(" "),a("div",{staticStyle:{"text-align":"center"}},[e.modelArray.length>0?a("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"mini",type:"success"},on:{click:function(t){return t.preventDefault(),e.addModel(t)}}}):e._e()],1)],2)},u=[],s={name:"DynamicInput",props:{modelArray:{type:Array,required:!0},label:{type:String,required:!0},ruleModelName:{type:String,required:!0}},methods:{onValueChanged:function(e){this.$emit("update:modelArray",this.modelArray)},addModel:function(){this.modelArray.push({key:(new Date).getTime(),value:""})},removeModel:function(e){var t=this.modelArray.indexOf(e);-1!==t&&this.modelArray.splice(t,1)}}},m=s,c=(a("a7e0"),a("0cba"),a("2877")),d=Object(c["a"])(m,i,u,!1,null,"49096707",null),p=d.exports,f=a("a145"),y={name:"CouponTemplateEditManagement",components:{BaseCard:f["a"],DynamicInput:p},activated:function(){var e=this,t=Object(r["g"])();this.init(),t.templateId&&l["a"].getTemplateById(t.templateId).then((function(t){var a=Object.assign({},t);a.allowCategory=[],a.limitCategory=[],a.allowGoods=[],a.limitGoods=[],t.allowCategory instanceof Array&&t.allowCategory.forEach((function(e){a.allowCategory.push({key:(new Date).getTime()+Math.round(1e4*Math.random()),value:e})})),t.limitCategory instanceof Array&&t.limitCategory.forEach((function(e){a.limitCategory.push({key:(new Date).getTime()+Math.round(1e4*Math.random()),value:e})})),t.allowGoods instanceof Array&&t.allowGoods.forEach((function(e){a.allowGoods.push({key:(new Date).getTime()+Math.round(1e4*Math.random()),value:e})})),t.limitGoods instanceof Array&&t.limitGoods.forEach((function(e){a.limitGoods.push({key:(new Date).getTime()+Math.round(1e4*Math.random()),value:e})})),e.formData=a}))},data:function(){return{formData:{id:null,couponName:"",couponType:"MONEY",couponMoney:0,couponDiscount:0,minimumMoney:0,expireDays:0,allowCategory:[],limitCategory:[],allowGoods:[],limitGoods:[]}}},methods:{init:function(){this.formData.couponName="",this.formData.couponType="MONEY",this.formData.couponMoney=0,this.formData.couponDiscount=0,this.formData.minimumMoney=0,this.formData.expireDays=0,this.formData.allowCategory=[],this.formData.limitCategory=[],this.formData.allowGoods=[],this.formData.limitGoods=[]},saveTemplate:function(){var e=this;this.$refs.form.validate((function(t){t&&l["a"].createNewTemplate(e.formData).then((function(t){e.formData.id||e.init(),e.$message({message:t.message,type:"success"})}))}))}}},v=y,D=(a("4e5c"),Object(c["a"])(v,o,n,!1,null,"6712cf3a",null));t["default"]=D.exports},a145:function(e,t,a){"use strict";var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"base-card"},[e._t("default")],2)},n=[],r={name:"BaseCard"},l=r,i=(a("179c"),a("2877")),u=Object(i["a"])(l,o,n,!1,null,"9e9f1e78",null);t["a"]=u.exports},a7e0:function(e,t,a){"use strict";var o=a("0bb6"),n=a.n(o);n.a},c5bb:function(e,t,a){"use strict";var o=a("b775"),n=a("4328"),r=a.n(n);function l(e){return Object(o["a"])({url:"/coupon/template/getTemplateById",params:{id:e}})}function i(e,t){var a=Object.assign({},e,t);return Object(o["a"])({url:"/coupon/template/getTemplateListByPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:a,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function u(){return Object(o["a"])({url:"/coupon/template/getAllTemplateList",method:"get",headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function s(e){var t=Object.assign({},e);return t.allowCategory=t.allowCategory.map((function(e){return e.value})).join(),t.limitCategory=t.limitCategory.map((function(e){return e.value})).join(),t.allowGoods=t.allowGoods.map((function(e){return e.value})).join(),t.limitGoods=t.limitGoods.map((function(e){return e.value})).join(),Object(o["a"])({url:"/coupon/template/createNewTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:t,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function m(e){return Object(o["a"])({url:"/coupon/template/updateTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:e,transformRequest:[function(e){return e=r.a.stringify(e),e}]})}function c(e){return Object(o["a"])({url:"/coupon/template/deleteTemplate",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},data:{id:e},transformRequest:[function(e){return e=r.a.stringify(e),e}]})}t["a"]={getTemplateById:l,getTemplateListByPage:i,getAllTemplateList:u,createNewTemplate:s,updateTemplate:m,deleteTemplate:c}},c7ea:function(e,t,a){}}]);