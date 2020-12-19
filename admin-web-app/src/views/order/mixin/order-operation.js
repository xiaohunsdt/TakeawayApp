import { formatOrderState, formatPaymentWay, formatPayState, parseTime } from '@u/index'
import orderApi from '@a/order'

export default {
    filters: {
        orderStateFormat: function(value) {
            return formatOrderState(value)
        },
        payStateFormat: function(value) {
            return formatPayState(value)
        },
        paymentWayFormat: function(value) {
            return formatPaymentWay(value)
        }
    },
    methods: {
        getList() {
            this.listLoading = true

            const params = Object.assign({}, this.formData)
            params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
            params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

            orderApi.getOrderListByPage(this.page, params)
              .then(response => {
                  const datas = response.records
                  datas.forEach(item => {
                      item.detail = {}
                  })
                  this.tableData = datas
                  this.page.total = parseInt(response.total)
              })
              .finally(() => {
                  this.listLoading = false
              })
        },
        async getOrderDetail(row, expandedRows) {
            const currentRow = expandedRows.find(item => item.id === row.id)
            if (currentRow !== undefined) {
                await orderApi.getOrderDetail(row.id)
                  .then(response => {
                      this.$set(currentRow, 'detail', response)
                  })
            }
        },
        handleSizeChange(val) {
            this.page.size = val
            this.getList()
        },
        handleCurrentChange(val) {
            this.page.current = val
            this.getList()
        },
        onSearch() {
            this.page.current = 1
            this.getList()
        },
        onEditOrder(orderId) {
            this.$refs['edit-order-dialog'].openDialog(orderId)
        },
        onPrintOrder(order) {
            const loading = this.$loading({
                lock: true,
                text: '打印中...',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.7)'
            })
            orderApi.printOrder(order)
              .then(res => {
                  this.$message.success('打印成功')
                  // order.payState = 'PAID'
              })
              .finally(() => {
                  loading.close()
              })
        },
        onConfirmPay(order) {
            this.$confirm('确定此账户已付款?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                orderApi.confirmPay(order.id)
                  .then(res => {
                      this.$message.success(res.message)
                      order.payState = 'PAID'
                  })
            })
        },
        onReceiveOrder(order) {
            orderApi.receiveOrder(order.id)
              .then(res => {
                  this.$message.success(res.message)
                  order.orderState = 'PRODUCING'
              })
        },
        onDeliveryOrder(order) {
            orderApi.deliveryOrder(order.id)
              .then(res => {
                  this.$message.success(res.message)
                  order.orderState = 'DELIVERING'
              })
        },
        onFinishOrder(order) {
            this.$confirm('确定当前订单已完成?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                orderApi.finishOrder(order.id)
                  .then(res => {
                      this.$message.success(res.message)
                      order.orderState = 'FINISHED'
                  })
            })
        },
        onRefundOrder(order) {
            this.$refundSubmitDialog(order).then(res => {
                this.$message.success(res.res.message)
                if (res.res.code === 0) {
                    order.orderState = res.orderState
                }
            })

            // refundLogApi.getAllRefundMoneyByOrderId(order.id).then(res => {
            //     const canRefundMoney = order.realPrice - parseInt(res.message)
            //
            //     this.$prompt(`请输入要退款的金额，最多只能退款 ₩${canRefundMoney.toLocaleString()}`, '提示', {
            //         confirmButtonText: '确定',
            //         cancelButtonText: '取消',
            //         inputPattern: /[0-9]+/,
            //         inputErrorMessage: '请输入退款金额'
            //     }).then(({ value }) => {
            //         value = parseInt(value)
            //         if (value === 0) {
            //             this.$message.error('退款金额必须大于0')
            //             return
            //         }
            //         if (value > canRefundMoney) {
            //             this.$message.error(`最多只能退款 ₩${canRefundMoney.toLocaleString()}`)
            //             return
            //         }
            //         orderApi.refundOrder(order.id, value)
            //           .then(res => {
            //               this.$message.success(res.message)
            //               if (canRefundMoney === value) {
            //                   order.orderState = 'REFUND'
            //               } else {
            //                   order.orderState = 'PART_REFUND'
            //               }
            //           })
            //     })
            // })
        },
        onDeleteOrder(order) {
            this.$confirm('确定要删除这个订单吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                orderApi.deleteOrder(order.id)
                  .then(res => {
                      this.$message.success(res.message)
                      this.getList()
                  })
            })
        }
    }
}
