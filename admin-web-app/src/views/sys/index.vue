<template>
  <div class="container">
    <base-card>
      <el-tabs type="card">
        <el-tab-pane label="系统设置">
          <el-form ref="form" :model="systemSetting" label-width="120px" size="mini" style="max-width: 660px">
            <el-form-item label="接单服务">
              <el-tooltip content="是否允许下单" placement="right">
                <el-switch v-model="systemSetting.service_running"></el-switch>
              </el-tooltip>
            </el-form-item>
            <el-form-item v-if="!systemSetting.service_running" label="服务关闭提送">
              <el-tooltip content="当接单服务关闭时给用户看的提示信息" placement="right">
                <el-input v-model="systemSetting.service_close_notice"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="自动接单">
              <el-tooltip content="是否允许系统自动接单" placement="right">
                <el-switch v-model="systemSetting.auto_receive_order"></el-switch>
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
              <el-button :loading="saveLoading" type="primary" @click="saveSetting('SYSTEM')">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="店铺设置">
          <el-form ref="form" :model="storeSetting" label-width="130px" size="mini" style="max-width: 660px">
            <el-form-item label="店铺Logo">
              <el-upload
                  ref="upload"
                  :action="$VUE_APP_BASE_API + '/api/admin/uploadStoreLogo'"
                  :before-upload="beforeUpload"
                  :headers="authHeader"
                  :multiple="false"
                  :on-success="handleUploadSuccess"
                  :show-file-list="false">
                <img
                    v-if="storeSetting.store_logo !== ''"
                    :src="$VUE_APP_BASE_API + storeSetting.store_logo"
                    style="height: 150px;width: auto;"/>
                <el-button v-else size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过5M！建议上传长宽相等的正方形图片</div>
              </el-upload>
            </el-form-item>
            <el-form-item label="运营周期" size="small">
              <el-checkbox-group v-model="storeSetting.store_open_date" size="mini">
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
                  v-model="timePickValue"
                  end-placeholder="关门时间"
                  is-range
                  placeholder="选择运营时间范围"
                  range-separator="至"
                  start-placeholder="开门时间"/>
            </el-form-item>
            <el-form-item label="店铺地址">
              <el-select
                  v-model="storeSetting.store_address"
                  :loading="searchLoading"
                  :remote-method="onSearch"
                  filterable
                  placeholder="请输入关键词"
                  remote
                  reserve-keyword
                  style="display: block;"
                  @change="onSelect">
                <el-option
                    v-for="item in addressList"
                    :key="item.address"
                    :label="item.address"
                    :value="item.address">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="地址经度">
              <el-input v-model="storeSetting.store_address_x" disabled></el-input>
            </el-form-item>
            <el-form-item label="地址纬度">
              <el-input v-model="storeSetting.store_address_y" disabled></el-input>
            </el-form-item>
            <el-form-item label="厨师体温">
              <el-input v-model="storeSetting.temperature1"></el-input>
            </el-form-item>
            <el-form-item label="外卖员体温">
              <el-input v-model="storeSetting.temperature2"></el-input>
            </el-form-item>
            <el-form-item label="老板体温">
              <el-input v-model="storeSetting.temperature3"></el-input>
            </el-form-item>
            <el-form-item label="打包员体温">
              <el-input v-model="storeSetting.temperature4"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSetting('STORE')">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="配送设置">
          <el-form ref="form" :model="expressSetting" label-width="120px" size="mini" style="max-width: 660px">
            <el-form-item label="最低起送价格">
              <el-tooltip content="最低起送价格" placement="right">
                <el-input v-model.number="expressSetting.lowest_order_price"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="最远配送距离">
              <el-tooltip content="最远配送距离，单位为米" placement="right">
                <el-input v-model.number="expressSetting.max_express_distance"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="配送费">
              <el-tooltip content="每单收取的配送费" placement="right">
                <el-input v-model.number="expressSetting.delivery_price"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="起送价范围设置">
              <el-tooltip content="距离不同,起送价格不同" placement="right">
                <dynamic-input v-model="expressSetting.distance_price_arr"/>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="基本配送时间">
              <el-tooltip content="只有一单的情况下,配送需要多长时间" placement="right">
                <el-input v-model.number="expressSetting.base_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="配送平均时间">
              <el-tooltip content="一般情况下，平均每单的配送时间" placement="right">
                <el-input v-model.number="expressSetting.average_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="外卖人员">
              <el-input v-model.number="expressSetting.deliverier_count"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSetting('EXPRESS')">保存设置</el-button>
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
import DynamicInput from './components/DynamicInput'
import { getToken } from '@/utils/auth'

export default {
  name: 'SysManagement',
  components: {
    BaseCard,
    DynamicInput
  },
  computed: {
    authHeader() {
      return {
        Authorization: `Bearer ${getToken()}`
      }
    }
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
        auto_receive_order: false,
        service_close_notice: '',
        goods_page_notice: '',
        goods_page_tags: ''
      },
      storeSetting: {
        store_logo: '',
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
        lowest_order_price: 0,
        delivery_price: 0,
        distance_price_arr: [],
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
          if (item.key === 'service_running' || item.key === 'auto_receive_order') {
            this.$set(this.systemSetting, item.key, item.value === 'true')
            return
          }
          this.$set(this.systemSetting, item.key, item.value)
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
          if (item.key === 'distance_price_arr') {
            this.$set(this.expressSetting, item.key, JSON.parse(item.value))
            return
          }
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
          settings = Object.assign({}, this.expressSetting)
          settings.distance_price_arr = JSON.stringify(settings.distance_price_arr)
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
    },
    beforeUpload(file) {
      const isImg = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt5M = file.size / 1024 / 1024 < 5

      if (!isImg) {
        this.$message.error('只能上传图片')
      }
      if (!isLt5M) {
        this.$message.error('上传头像图片大小不能超过 5MB!')
      }
      return isImg && isLt5M
    },
    handleUploadSuccess(response, file, fileList) {
      const storeLogo = `/upload/images/store/${response.message}`
      this.storeSetting.store_logo = storeLogo
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
