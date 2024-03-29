import request from '@/utils/request'
import Qs from 'qs'

export function getById(id) {
    return request({
        url: '/produce/getById',
        params: { id }
    })
}

export function getDetailById(id) {
    return request({
        url: '/produce/getDetailById',
        params: { id }
    })
}

export function getAll() {
    return request({
        url: '/produce/getAll',
        method: 'get'
    })
}

export function getListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/produce/getListByPage',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function create(produce, specs, goodsList) {
    return request({
        url: '/produce/create',
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        data: { produce, specs: formatSpecs(specs), goodsList: formatGoodsList(goodsList) }
    })
}

export function update(produce, specs, goodsList) {
    return request({
        url: '/produce/update',
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        data: { produce, specs: formatSpecs(specs), goodsList: formatGoodsList(goodsList) }
    })
}

export function updateThumb(id, imageUrl) {
    return request({
        url: '/produce/updateThumb',
        method: 'post',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: {
            id,
            imageUrl
        },
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

export function del(id) {
    return request({
        url: '/produce/delete',
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

export function formatSpecs(specs) {
    const options = {}
    const selectedSpecs = {}
    specs.forEach(item => {
        selectedSpecs[item.id] = item.key
        options[item.id] = item.params.map(item => item.value)
    })
    return {
        selectedSpecs: JSON.stringify(selectedSpecs),
        options: JSON.stringify(options)
    }
}

function formatGoodsList(goodsList) {
    const target = []
    goodsList.forEach(item => {
        const obj = Object.assign({}, item)
        if (obj.ownSpecs) {
            obj.ownSpecs = JSON.stringify(obj.ownSpecs)
        }
        target.push(obj)
    })
    return target
}

export default {
    getById,
    getDetailById,
    getAll,
    getListByPage,
    create,
    update,
    updateThumb,
    del,
    formatSpecs
}
