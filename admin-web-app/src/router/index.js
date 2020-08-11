import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    noCache: true
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    hidden: false
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
   roles: ['admin','editor']    control the page roles (you can set multiple roles)
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
    {
        path: '/redirect',
        component: Layout,
        hidden: true,
        children: [
            {
                path: '/redirect/:path*',
                component: () => import('@/views/redirect/index')
            }
        ]
    },
    {
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },

    {
        path: '/404',
        component: () => import('@/views/404'),
        hidden: true
    }
]

export const asyncRoutes = [
    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () => import('@/views/dashboard/index'),
            meta: { title: 'Dashboard', icon: 'dashboard', affix: true }
        }]
    },
    {
        path: '/user',
        component: Layout,
        redirect: '/user/index',
        meta: {
            title: '用户管理',
            icon: 'example',
            affix: true,
            roles: ['SUPER_MANAGER']
        },
        children: [
            {
                path: 'index',
                name: 'UserManagement',
                // props: true,
                component: () => import('@/views/user/index'),
                meta: { title: '用户列表', icon: 'example' }
            },
            {
                path: 'address',
                name: 'AddressManagement',
                // props: true,
                component: () => import('@/views/user/address/index'),
                meta: { title: '地址管理', icon: 'example' }
            }
        ]
    },
    {
        path: '/order',
        component: Layout,
        redirect: '/order/index',
        meta: { title: '订单管理', icon: 'order' },
        children: [
            {
                path: 'index',
                name: 'NormalOrder',
                // props: true,
                component: () => import('@/views/order/index'),
                meta: { title: '一般订单', icon: 'order' }
            },
            {
                path: 'appointment',
                name: 'AppointmentOrder',
                // props: true,
                component: () => import('@/views/order/appointment'),
                meta: { title: '预约订单', icon: 'order' }
            },
            {
                path: 'map',
                name: 'OrderMap',
                // props: true,
                component: () => import('@/views/order/order-map'),
                meta: { title: '订单地图', icon: 'order' }
            },
            {
                path: 'comment',
                name: 'CommentManagement',
                // props: true,
                component: () => import('@/views/order/comment'),
                meta: { title: '评价管理', icon: 'goods' }
            }
        ]
    },
    {
        path: '/goods',
        component: Layout,
        redirect: '/goods/index',
        children: [{
            path: 'index',
            name: 'GoodsManagement',
            // props: true,
            component: () => import('@/views/goods/index'),
            meta: { title: '商品管理', icon: 'goods' }
        }]
    },
    {
        path: '/category',
        component: Layout,
        redirect: '/category/index',
        children: [{
            path: 'index',
            name: 'CategoryManagement',
            // props: true,
            component: () => import('@/views/category/index'),
            meta: { title: '分类管理', icon: 'tree' }
        }]
    },
    {
        path: '/coupon',
        component: Layout,
        redirect: '/coupon/index',
        meta: { title: '优惠券管理', icon: 'tree-table' },
        children: [
            {
                path: 'index',
                name: 'CouponManagement',
                // props: true,
                component: () => import('@/views/coupon/index'),
                meta: { title: '优惠券列表', icon: 'example' }
            },
            {
                path: 'unbind',
                name: 'UnBindCouponManagement',
                // props: true,
                component: () => import('@/views/coupon/unbind'),
                meta: { title: '未兑换的优惠券', icon: 'example' }
            },
            {
                path: 'template',
                name: 'CouponTemplateManagement',
                // props: true,
                component: () => import('@/views/coupon/template/index'),
                meta: { title: '模板管理', icon: 'example' }
            },
            {
                path: 'template/edit',
                name: 'CouponTemplateEditManagement',
                hidden: true,
                // props: true,
                component: () => import('@/views/coupon/template/edit'),
                meta: { title: '编辑模板', icon: 'example' }
            },
            {
                path: 'log',
                name: 'CouponLogManagement',
                // props: true,
                component: () => import('@/views/coupon/use-log/index'),
                meta: { title: '优惠券使用记录', icon: 'example' }
            }
        ]
    },
    {
        path: '/activity',
        component: Layout,
        redirect: '/activity/index',
        meta: { title: '活动管理', icon: 'example' },
        children: [
            {
                path: 'edit',
                name: 'EditActivity',
                // props: true,
                component: () => import('@/views/activity/edit'),
                meta: { title: '创建/编辑', icon: 'example' }
            },
            {
                path: 'index',
                name: 'ActivityManagement',
                // props: true,
                component: () => import('@/views/activity/index'),
                meta: { title: '活动列表', icon: 'example' }
            }
        ]
    },
    {
        path: '/banner',
        component: Layout,
        redirect: '/banner/index',
        meta: { title: '横幅管理', icon: 'example' },
        children: [
            {
                path: 'edit',
                name: 'EditBanner',
                // props: true,
                component: () => import('@/views/banner/edit'),
                meta: { title: '创建/编辑', icon: 'example' }
            },
            {
                path: 'index',
                name: 'BannerManagement',
                // props: true,
                component: () => import('@/views/banner/index'),
                meta: { title: '横幅列表', icon: 'example' }
            }
        ]
    },
    {
        path: '/sys',
        component: Layout,
        redirect: '/sys/index',
        children: [{
            path: 'index',
            name: 'SysManagement',
            // props: true,
            component: () => import('@/views/sys/index'),
            meta: { title: '系统设置', icon: 'setting' }
        }]
    },
    // 404 page must be placed at the end !!!
    { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
