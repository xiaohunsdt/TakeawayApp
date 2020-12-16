import request from '@/utils/request'
import Qs from 'qs'

export function getLogListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/refund/log/getListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getAllRefundMoneyByOrderId(orderId) {
    return request({
        url: '/refund/log/getAllRefundMoneyByOrderId',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { orderId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getLogCountByOrderId(orderId) {
    return request({
        url: '/refund/log/getLogCountByOrderId',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { orderId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function doneRefund(refundId) {
    return request({
        url: '/refund/log/doneRefund',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { refundId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function rejectRefund(refundId) {
    return request({
        url: '/refund/log/rejectRefund',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { refundId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export default {
    getLogListByPage,
    getAllRefundMoneyByOrderId,
    getLogCountByOrderId,
    doneRefund,
    rejectRefund
}
