import request from '@/utils/request'
import Qs from 'qs'

export function getAdminInfoById(adminId) {
    return request({
        url: '/getAdminInfoById',
        method: 'post',
        data: { adminId },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getSubAdminByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/getSubAdminByPage',
        method: 'post',
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function createNewSubAdmin(admin) {
    return request({
        url: '/createNewSubAdmin',
        method: 'post',
        data: admin,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function updateAdmin(admin) {
    return request({
        url: '/updateAdmin',
        method: 'post',
        data: admin,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function login(data) {
    return request({
        url: '/auth',
        method: 'post',
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function getInfo() {
    return request({
        url: '/getAdminInfo',
        method: 'get'
    })
}

export function refreshToken() {
    return request({
        url: '/auth/refresh',
        method: 'post'
    })
}

export function logout() {
    return request({
        url: '/logout',
        method: 'post'
    })
}

export default {
    getSubAdminByPage,
    getAdminInfoById,
    createNewSubAdmin,
    updateAdmin,
    logout
}
