<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <order-card
        v-for="order in orderList"
        :key="order.orderId"
        :order="order"/>
    </div>
  </div>
</template>

<script>
    import OrderCard from './components/OrderCard'

    export default {
        components: {
            OrderCard
        },
        data () {
            return {
                orderList: [
                    {
                        orderId: '20190915gfhfghfbcvsdf',
                        orderNumber: 2,
                        orderItems: [
                            {
                                orderId: null,
                                goodsId: 1,
                                name: '鸭血粉丝汤1',
                                thumb: '/static/images/food/food.jpg',
                                count: 2,
                                price: 2000
                            },
                            {
                                orderId: null,
                                goodsId: 1,
                                name: '鸭血粉丝汤2',
                                thumb: '/static/images/food/food.jpg',
                                count: 1,
                                price: 2000
                            }
                        ],
                        orderAmount: 67,
                        orderPayWay: 1,
                        orderState: 1,
                        createDate: '2019-09-15 12:23:11'
                    },
                    {
                        orderId: '20190915cvbsdmgsma',
                        orderNumber: 1,
                        orderItems: [
                            {
                                orderId: null,
                                goodsId: 1,
                                name: '鸭血粉丝汤1',
                                thumb: '/static/images/food/food.jpg',
                                count: 2,
                                price: 2000
                            },
                            {
                                orderId: null,
                                goodsId: 1,
                                name: '鸭血粉丝汤2',
                                thumb: '/static/images/food/food.jpg',
                                count: 1,
                                price: 2000
                            }
                        ],
                        orderAmount: 67,
                        orderPayWay: 1,
                        orderState: 1,
                        createDate: '2019-09-15 12:23:11'
                    }
                ]
            }
        },
        methods: {}
    }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }
</style>
