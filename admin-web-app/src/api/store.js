import request from '@/utils/request'
import Qs from 'qs'

export function getStoreById(storeId) {
    return request({
        url: '/store/getStoreById',
        method: 'post',
        data: { storeId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/store/getListByPage',
        method: 'post',
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function create(admin) {
    return request({
        url: '/store/create',
        method: 'post',
        data: admin,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function update(admin) {
    return request({
        url: '/store/update',
        method: 'post',
        data: admin,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export default {
    getStoreById,
    getListByPage,
    create,
    update
}
