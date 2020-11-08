import request from '@/utils/request'
import Qs from 'qs'

export function getMyBalance() {
    return request({
        url: '/store/balance/getMyBalance',
        method: 'get',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

export function getBalanceLogListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/store/balance/log/getListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function applyWithdraw(money) {
    return request({
        url: '/store/balance/withdraw/apply',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { money },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getWithdrawLogListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/store/balance/withdraw/log/getListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function cancelWithdraw(id) {
    return request({
        url: '/store/balance/withdraw/cancel',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { id },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function deleteWithdraw(id) {
    return request({
        url: '/store/balance/withdraw/delete',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { id },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function handleWithdraw(id) {
    return request({
        url: '/store/balance/withdraw/handle',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { id },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function rejectWithdraw(id, ps) {
    return request({
        url: '/store/balance/withdraw/reject',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { id, ps },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export default {
    getMyBalance,
    getBalanceLogListByPage,
    applyWithdraw,
    getWithdrawLogListByPage,
    cancelWithdraw,
    deleteWithdraw,
    handleWithdraw,
    rejectWithdraw
}
