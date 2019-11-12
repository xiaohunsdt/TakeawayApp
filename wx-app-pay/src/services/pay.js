/**
 * 支付相关服务
 */
import api from '@/utils/api'

function payOrder (orderId) {
  return new Promise(function (resolve, reject) {
    api.createPayInfo(orderId)
      .then((res) => {
        resolve(res)
      })
      .catch(err => {
        reject(err)
      })
  })
}

function callWxPayApi (res) {
  return new Promise(function (resolve, reject) {
    // console.log('请求接口pay/prepay后的结果', res)
    const payParam = res
    // console.log('payParam这些应该是后台
    // 计算好的签名等', payParam.timeStamp)
    const timeStampParam = payParam.timeStamp ? payParam.timeStamp : ''
    const nonceStrParam = payParam.nonceStr ? payParam.nonceStr : ''
    const packageParam = payParam.packageValue ? payParam.packageValue : ''
    const signTypeParam = payParam.signType ? payParam.signType : ''
    const paySignParam = payParam.paySign ? payParam.paySign : ''
    mpvue.requestPayment({
      'timeStamp': timeStampParam,
      'nonceStr': nonceStrParam,
      'package': packageParam,
      'signType': signTypeParam,
      'paySign': paySignParam,
      'success': function (res) {
        resolve(res)
      },
      'fail': function (res) {
        reject(res)
      },
      'complete': function (res) {
        reject(res)
      }
    })
  })
}

function confirmOrder (orderId) {
  return api.confirmOrder(orderId)
}

export default {
  payOrder,
  callWxPayApi,
  confirmOrder
}
