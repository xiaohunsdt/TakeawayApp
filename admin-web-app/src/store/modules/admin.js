import { getInfo, login, logout, refreshToken } from '@/api/admin'
import authUtil from '@/utils/auth'
import { resetRouter } from '@/router'

const state = {
    token: authUtil.getToken(),
    randomKey: null,
    userData: null
}

const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    SET_RANDOM_KEY: (state, randomKey) => {
        state.randomKey = randomKey
    },
    SET_USER_DATA: (state, userData) => {
        state.userData = userData
    }
}

const actions = {
    // user login
    login({ commit }, userInfo) {
        const { userName, password } = userInfo
        return new Promise((resolve, reject) => {
            login({ userName: userName.trim(), password: password }).then(response => {
                const { randomKey, token } = response
                authUtil.setToken(token)
                authUtil.setRandomKey(randomKey)
                commit('SET_TOKEN', token)
                commit('SET_RANDOM_KEY', randomKey)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },

    // get user info
    getInfo({ commit, state }) {
        return new Promise((resolve, reject) => {
            getInfo().then(response => {
                const data = response
                if (!data) {
                    reject('Verification failed, please Login again.')
                }
                commit('SET_USER_DATA', data)
                resolve(data)
            }).catch(error => {
                reject(error)
            })
        })
    },

    refreshToken({ commit, state }) {
        return new Promise((resolve, reject) => {
            refreshToken().then(response => {
                const { token } = response
                authUtil.setToken(token)
                commit('SET_TOKEN', token)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },

    // user logout
    logout({ commit, state }) {
        return new Promise((resolve, reject) => {
            logout(state.token).then(() => {
                commit('SET_TOKEN', '')
                authUtil.removeToken()
                authUtil.removeRandomKey()
                resetRouter()
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },

    // remove token
    resetToken({ commit }) {
        return new Promise(resolve => {
            commit('SET_TOKEN', '')
            authUtil.removeToken()
            authUtil.removeRandomKey()
            resolve()
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}

