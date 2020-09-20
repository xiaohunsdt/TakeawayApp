<template>
  <div class="container-contain">
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
      <div :style="{'min-height': scrollerHeight}">
        <base-card>
          <van-field
              :value="formData.startDate"
              clickable
              label="开始日期"
              placeholder="点击选择时间"
              readonly
              @click="()=>{this.datePickType = 1; this.showPicker = true;}"/>
          <van-field
              :value="formData.endDate"
              clickable
              label="结束日期"
              placeholder="点击选择时间"
              readonly
              @click="()=>{this.datePickType = 2; this.showPicker = true;}"/>
        </base-card>

        <base-card>
          <el-table :data="deliveryList" style="width: 100%">
            <el-table-column
                prop="number"
                label="编号"
                align="center">
            </el-table-column>
            <el-table-column
                prop="orderCreateDate"
                label="日期"
                width="120"
                align="center">
              <template v-slot="props">
                <div>
                  {{props.row.orderCreateDate}}
                </div>
                <div>
                  {{props.row.createDate}}
                </div>
              </template>
            </el-table-column>
            <el-table-column
                prop="createDate"
                label="完成时间"
                width="100"
                align="center">
              <template v-slot="props">
                <el-tag :type="props.row.orderFinishMinute!=='未完成'?'success':'info'" size="mini">{{props.row.orderFinishMinute}} {{props.row.orderFinishMinute!=='未完成'?'分钟' : ''}}</el-tag>
                <br />
                <el-tag :type="props.row.deliveryFinishMinute!=='未完成'?'success':'info'" size="mini">{{props.row.deliveryFinishMinute}} {{props.row.deliveryFinishMinute!=='未完成'?' 分钟' : ''}}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </base-card>
      </div>
    </van-pull-refresh>
    <van-popup v-model="showPicker" position="bottom">
      <van-datetime-picker
          v-model="currentDate"
          title="选择年月日"
          type="date"
          @cancel="showPicker = false"
          @confirm="onSelectDate"/>
    </van-popup>
  </div>
</template>

<script>
import deliveryService from '@a/delivery'
import { parseTime } from '@u/index'

import BaseCard from '@/components/BaseCard'
import { DatetimePicker, Field, Form, Popup, PullRefresh } from 'vant'

export default {
  name: 'DelivererLogManagement',
  components: {
    BaseCard,
    [PullRefresh.name]: PullRefresh,
    [DatetimePicker.name]: DatetimePicker,
    [Form.name]: Form,
    [Popup.name]: Popup,
    [Field.name]: Field
  },
  computed: {
    scrollerHeight() {
      return window.innerHeight - 44 + 'px'
    }
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        startDate: '',
        endDate: ''
      },
      currentDate: new Date(),
      datePickType: 1,
      showPicker: false,
      isLoading: false,
      deliveryList: []
    }
  },
  created() {
    this.formData.startDate = parseTime(this.currentDate, '{y}-{m}-{d}')
    this.formData.endDate = parseTime(this.currentDate, '{y}-{m}-{d}')
    this.getDeliveryList()
  },
  methods: {
    onSelectDate(time) {
      switch (this.datePickType) {
        case 1:
          this.formData.startDate = parseTime(time, '{y}-{m}-{d}')
          break
        case 2:
          this.formData.endDate = parseTime(time, '{y}-{m}-{d}')
          break
      }
      this.showPicker = false
    },
    getDeliveryList() {
      deliveryService.getMyDeliveryListByPage(this.page, this.formData).then(res => {
        this.deliveryList.push(...res.records)
      }).finally(() => {
        this.isLoading = false
      })
    },
    onRefresh() {
      this.page.current = 1
      this.deliveryList = []
      this.getDeliveryList()
    }
  }
}
</script>

<style scoped>
.van-pull-refresh{
  overflow: unset !important;
}
</style>
