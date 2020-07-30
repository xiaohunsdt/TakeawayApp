<template>
  <div class="container">
    <base-card>
      <el-tabs type="card">
        <el-tab-pane label="系统设置">
          <el-form :model="systemSetting" label-width="120px" ref="form" style="max-width: 660px">
            <el-form-item label="接单服务">
              <el-tooltip content="当前是否正常结单" placement="right">
                <el-switch v-model="systemSetting.service_running"></el-switch>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="服务关闭提送" v-if="!systemSetting.service_running">
              <el-tooltip content="当接单服务关闭时给用户看的提示信息" placement="right">
                <el-input v-model="systemSetting.service_close_notice"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="商品页公告">
              <el-input v-model="systemSetting.goods_page_notice"></el-input>
            </el-form-item>
            <el-form-item label="商品页标签">
              <el-tooltip content="多个标签用,分隔!例如:免费配送,快速送达,满2w送饮料" placement="right">
                <el-input v-model="systemSetting.goods_page_tags"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item>
              <el-button :loading="saveLoading" @click="saveSetting('SYSTEM')" type="primary">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="店铺设置">
          <el-form :model="storeSetting" label-width="130px" ref="form" style="max-width: 660px">
            <el-form-item label="运营周期" size="small">
              <el-checkbox-group v-model="storeSetting.store_open_date">
                <el-checkbox-button label="2">星期一</el-checkbox-button>
                <el-checkbox-button label="3">星期二</el-checkbox-button>
                <el-checkbox-button label="4">星期三</el-checkbox-button>
                <el-checkbox-button label="5">星期四</el-checkbox-button>
                <el-checkbox-button label="6">星期五</el-checkbox-button>
                <el-checkbox-button label="7">星期六</el-checkbox-button>
                <el-checkbox-button label="1">星期日</el-checkbox-button>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="运营时间">
              <el-time-picker
                end-placeholder="关门时间"
                is-range
                placeholder="选择运营时间范围"
                range-separator="至"
                start-placeholder="开门时间"
                v-model="timePickValue"/>
            </el-form-item>
            <el-form-item label="店铺地址">
              <el-select
                  :loading="searchLoading"
                  :remote-method="onSearch"
                  @change="onSelect"
                  filterable
                  placeholder="请输入关键词"
                  remote
                  reserve-keyword
                  style="display: block;"
                  v-model="storeSetting.store_address">
                <el-option
                    :key="item.address"
                    :label="item.address"
                    :value="item.address"
                    v-for="item in addressList">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="地址经度">
              <el-input v-model="storeSetting.store_address_x"></el-input>
            </el-form-item>
            <el-form-item label="地址纬度">
              <el-input v-model="storeSetting.store_address_y"></el-input>
            </el-form-item>
            <el-form-item label="厨师体温">
              <el-input v-model="storeSetting.temperature1"></el-input>
            </el-form-item>
            <el-form-item label="外卖员体温">
              <el-input v-model="storeSetting.temperature2"></el-input>
            </el-form-item>
            <el-form-item label="王老板体温">
              <el-input v-model="storeSetting.temperature3"></el-input>
            </el-form-item>
            <el-form-item label="打包员体温">
              <el-input v-model="storeSetting.temperature4"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="saveSetting('STORE')" type="primary">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="配送设置">
          <el-form :model="expressSetting" label-width="120px" ref="form" style="max-width: 660px">
            <el-form-item label="配送基本时间">
              <el-tooltip content="在最快的情况下配送所花的时间" placement="right">
                <el-input v-model="expressSetting.base_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="配送平均时间">
              <el-tooltip content="一般情况下，平均每单的配送时间" placement="right">
                <el-input v-model="expressSetting.average_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="外卖人员">
              <el-input v-model="expressSetting.deliverier_count"></el-input>
            </el-form-item>
            <el-form-item label="最远配送距离">
              <el-tooltip content="最远配送距离，单位为米" placement="right">
                <el-input v-model="expressSetting.max_express_distance"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item>
              <el-button @click="saveSetting('EXPRESS')" type="primary">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </base-card>

  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'
  import settingApi from '@/api/sys-setting'
  import addressApi from '@/api/address'

  export default {
    name: 'SysManagement',
    components: {
      BaseCard
    },
    created() {
      this.init(0)
    },
    data() {
      return {
        saveLoading: false,
        timePickValue: [new Date(), new Date()],
        systemSetting: {
          service_running: true,
          service_close_notice: '',
          goods_page_notice: '',
          goods_page_tags: ''
        },
        storeSetting: {
          store_open_date: [],
          store_open_time: null,
          store_close_time: null,
          store_address: '',
          store_address_x: null,
          store_address_y: null,
          temperature1: null,
          temperature2: null,
          temperature3: null,
          temperature4: null
        },
        expressSetting: {
          base_express_time: 0,
          average_express_time: 0,
          deliverier_count: 0,
          max_express_distance: 0
        },
        searchLoading: false,
        addressList: []
      }
    },
    methods: {
      init() {
        settingApi.getSettingsByScope('SYSTEM').then(res => {
          res.forEach(item => {
            if (item.key === 'service_running') {
              this.$set(this.systemSetting, item.key, item.value === 'true')
            } else {
              this.$set(this.systemSetting, item.key, item.value)
            }
          })
        })
        settingApi.getSettingsByScope('STORE').then(res => {
          res.forEach(item => {
            if (item.key === 'store_open_date') {
              this.$set(this.storeSetting, item.key, item.value.split(','))
            } else {
              if (item.key === 'store_open_time') {
                this.$set(this.timePickValue, 0, new Date(item.value))
              }
              if (item.key === 'store_close_time') {
                this.$set(this.timePickValue, 1, new Date(item.value))
              }
              this.$set(this.storeSetting, item.key, item.value)
            }
          })
        })
        settingApi.getSettingsByScope('EXPRESS').then(res => {
          res.forEach(item => {
            this.$set(this.expressSetting, item.key, item.value)
          })
        })
      },
      saveSetting(scope) {
        let settings
        switch (scope) {
          case 'SYSTEM':
            settings = this.systemSetting
            break
          case 'STORE':
            settings = Object.assign({}, this.storeSetting)
            settings.store_open_date = settings.store_open_date.join()
            settings.store_open_time = this.timePickValue[0]
            settings.store_close_time = this.timePickValue[1]
            break
          case 'EXPRESS':
            settings = this.expressSetting
            break
        }

        this.saveLoading = true
        settingApi.updateSetting(settings, scope)
          .then(res => {
            this.saveLoading = false
            this.$message({
              message: res.message,
              type: 'success'
            })
          })
          .catch(res => {
            this.saveLoading = false
          })
      },
      onSearch(query) {
        if (query !== '') {
          this.searchLoading = true
          addressApi.searchAddress(query)
              .then(res => {
                this.addressList = res
                this.searchLoading = false
              })
              .catch(() => {
                this.addressList = []
                this.searchLoading = false
              })
        } else {
          this.addressList = []
        }
      },
      onSelect(address) {
        const temp = this.addressList.find(item => item.address === address)
        this.storeSetting.store_address = temp.address
        this.storeSetting.store_address_x = temp.x
        this.storeSetting.store_address_y = temp.y
      }
    }
  }
</script>

<style lang="scss" scoped>
  $bg: #F3F3F9;

  .container {
    padding: 1rem;
    min-height: inherit;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
  }
</style>
