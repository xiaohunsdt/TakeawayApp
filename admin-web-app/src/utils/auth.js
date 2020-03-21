import Cookies from 'js-cookie'
// import { getRebate, getRecentCalculateHistory } from '../api/dashboard'

const TokenKey = 'admin_token'
const RandomKey = 'admin_randomKey'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token, { expires: 90 })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getRandomKey() {
  return Cookies.get(RandomKey)
}

export function setRandomKey(randomKey) {
  return Cookies.set(RandomKey, randomKey, { expires: 90 })
}

export function removeRandomKey() {
  return Cookies.remove(RandomKey)
}

export default {
  getToken,
  setToken,
  removeToken,
  getRandomKey,
  setRandomKey,
  removeRandomKey
}
