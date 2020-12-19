import Vue from 'vue'
import Dialog from './index.vue'

let dialog

function createToast() {
    // 这里使用了 VUE 来构建一个 vnode
    // 值得注意的是， $mount() 函数没有填写任何的 dom 节点
    // 这样就变成了一个 未挂载 的 vnode
    const vnode = new Vue({
        render: h => h(Dialog)
    }).$mount()
    // 手动 将 生成的对应 dom 插进 body 里面
    document.body.appendChild(vnode.$el)
    // 返回当前实例  的 vue 对象
    // 没错，就是 $children[0]
    return vnode.$children[0]
}

export function showDialog(args, callback) {
    return new Promise(function(resolve, reject) {
        if (!dialog) {
            dialog = createToast()
        }
        dialog.show(args, resolve, reject)
        callback && callback()
    })
}

export function hide() {
    if (!dialog) return
    dialog.hide()
    return dialog
}

export function destoryToast() {
    if (!dialog) return
    dialog.destory()
}

export default showDialog
