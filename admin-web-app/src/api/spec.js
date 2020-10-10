import request from '@/utils/request'
import Qs from 'qs'

export function getAll() {
    return request({
        url: '/spec/getAll',
        method: 'get'
    })
}

export function getListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/spec/getListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function create(data) {
    return request({
        url: '/spec/create',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: { key: data },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function update(data) {
    return request({
        url: '/spec/update',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function del(id) {
    return request({
        url: '/spec/delete',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: {
            id
        },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export default {
    getAll,
    getListByPage,
    create,
    update,
    del
}
