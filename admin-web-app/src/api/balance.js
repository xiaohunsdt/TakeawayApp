import request from '@/utils/request'
import Qs from 'qs'

export function getListByPage(page, args) {
    const data = Object.assign({}, page, args)
    return request({
        url: '/balance/log/getListByPage',
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
    getListByPage
}
