import request from '@/utils/request'
import Qs from 'qs'

export function getById(id) {
    return request({
        url: '/produce/getById',
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
        data: { produce, specs: formatSpecs(specs), goodsList }
    })
}

export function update(data) {
    return request({
        url: '/produce/update',
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        data,
        transformRequest: [function(data) {
            data = Qs.stringify(data)
            return data
        }]
    })
}

// export function updateStock(goodsId, stock) {
//   return request({
//     url: '/produce/updateStock',
//     method: 'post',
//     headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
//     data: { goodsId, stock },
//     transformRequest: [function(data) {
//       data = Qs.stringify(data)
//       return data
//     }]
//   })
// }

export function updateGoodsThumb(id, imageUrl) {
    return request({
        url: '/produce/updateGoodsThumb',
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

function formatSpecs(specs) {
    const obj = {}
    specs.forEach(item => {
        obj[item.id] = item.params.map(item => item.value).join(',')
    })
    return { options: JSON.stringify(obj) }
}

export default {
    getById,
    getAll,
    getListByPage,
    create,
    updateGoodsThumb,
    del
}
