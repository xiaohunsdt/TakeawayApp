import api from '../utils/api'

export function getActivityById (id) {
  return api.getActivityById(id)
}

export function getAllActivityList () {
  return api.getAllActivityList()
}

export default {
  getActivityById,
  getAllActivityList
}
