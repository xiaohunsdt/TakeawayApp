<template>
  <div :style="{height:height,width:width}">
    <div style="font-size: 24px; font-weight: bolder; padding: 10px 0">余额:
      <count-to :duration="3200" :end-val="balance" :start-val="0" class="card-panel-num"/>
    </div>
    <el-table
      v-loading="listLoading"
      :data="logList"
      border
      class="tb-edit"
      element-loading-text="正在加载中..."
      highlight-current-row
      size="mini"
      stripe
      style="width: 100%;height: calc(100% - 50px);overflow: scroll">
      <el-table-column
        align="center"
        label="时间"
        width="90">
        <template v-slot="props">
          {{props.row.createDate | formatDate}}
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="内容"
        prop="ps">
      </el-table-column>
      <el-table-column
        align="center"
        label="金额"
        width="100">
        <template v-slot="props">
          <el-tag :type="props.row.money > 0 ? 'success':'danger'">
            {{ parseInt(props.row.money).toLocaleString() }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="变更后资金"
        width="100">
        <template v-slot="props">
          {{ parseInt(props.row.afterMoney).toLocaleString() }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import storeBalancelApi from '@a/balance'
import { parseTime } from '@u/index'

import CountTo from 'vue-count-to'
import resize from './mixins/resize'
import { mapGetters } from 'vuex'

export default {
  name: 'SimpleStoreCard',
  components: {
    CountTo
  },
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  filters: {
    formatDate(dateStr) {
      return parseTime(new Date(dateStr), '{m}-{d} {h}:{i}')
    }
  },
  data() {
    return {
      balance: 0,
      logList: [],
      listLoading: false
    }
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      storeBalancelApi.getMyBalance().then(res => {
        this.balance = parseInt(res.money)
      })

      this.listLoading = true
      storeBalancelApi.getBalanceLogListByPage({
        current: 1,
        size: 8,
        total: 0
      }).then(res => {
        this.logList = res.records
      }).finally(() => {
        this.listLoading = false
      })
    }
  }
}
</script>
