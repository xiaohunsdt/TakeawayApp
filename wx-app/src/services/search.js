import api from '@/utils/api'

export function searchGoods (keyword) {
  return api.searchGoods(keyword)
}

export default {
  searchGoods
}
