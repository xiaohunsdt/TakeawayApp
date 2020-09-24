import request from '@/utils/request'
import Qs from 'qs'

export function getDeliveryListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/delivery/getDeliveryListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getMyDeliveryListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/delivery/getMyDeliveryListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export default {
    getDeliveryListByPage,
    getMyDeliveryListByPage
}
