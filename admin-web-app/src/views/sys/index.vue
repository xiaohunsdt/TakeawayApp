<template>
  <div class="container">
    <base-card>
      <el-tabs type="card">
        <el-tab-pane label="系统设置">
          <el-form ref="form" :model="systemSetting" label-width="120px" style="max-width: 660px">
            <el-form-item label="接单服务">
              <el-tooltip content="当前是否正常结单" placement="right">
                <el-switch v-model="systemSetting.service_running"></el-switch>
              </el-tooltip>
            </el-form-item>
              <el-form-item label="服务关闭提送" v-if="!systemSetting.service_running">
                <el-tooltip content="当接单服务关闭时给用户看的提示信息" placement="right">
                  <el-input v-model="storeSetting.service_close_notice"></el-input>
                </el-tooltip>
              </el-form-item>
            <el-form-item label="商品页公告">
              <el-input v-model="storeSetting.order_page_notice"></el-input>
            </el-form-item>
            <el-form-item label="商品页标签">
              <el-tooltip content="多个标签用,分隔!例如:免费配送,快速送达,满2w送饮料" placement="right">
                <el-input v-model="storeSetting.order_page_tags"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSetting('system')">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="店铺设置">
          <el-form ref="form" :model="storeSetting" label-width="80px" style="max-width: 660px">
            <el-form-item label="运营周期" size="small">
              <el-checkbox-group v-model="storeSetting.store_open_date">
                <el-checkbox-button label="1">星期一</el-checkbox-button>
                <el-checkbox-button label="2">星期二</el-checkbox-button>
                <el-checkbox-button label="3">星期三</el-checkbox-button>
                <el-checkbox-button label="4">星期四</el-checkbox-button>
                <el-checkbox-button label="5">星期五</el-checkbox-button>
                <el-checkbox-button label="6">星期六</el-checkbox-button>
                <el-checkbox-button label="7">星期日</el-checkbox-button>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="运营时间">
              <el-time-picker
                is-range
                v-model="timePickValue"
                range-separator="至"
                start-placeholder="开门时间"
                end-placeholder="关门时间"
                placeholder="选择运营时间范围"/>
            </el-form-item>
            <el-form-item label="店铺地址">
              <el-input v-model="storeSetting.store_address"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSetting('store')">保存设置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="配送设置">
          <el-form ref="form" :model="expressSetting" label-width="120px" style="max-width: 660px">
            <el-form-item label="配送基本时间">
              <el-tooltip content="在最快的情况下配送所花的时间" placement="right">
                <el-input v-model="storeSetting.base_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="配送平均时间">
              <el-tooltip content="一般情况下，平均每单的配送时间" placement="right">
                <el-input v-model="storeSetting.average_express_time"></el-input>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="外卖人员">
              <el-input v-model="storeSetting.courier_count"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveSetting('express')">保存设置</el-button>
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
    export default {
        name: 'SysManagement',
        components: {
            BaseCard
        },
        data() {
            return {
                timePickValue: [new Date(), new Date()],
                systemSetting: {
                    service_running: true,
                    service_close_notice: '',
                    order_page_notice: '',
                    order_page_tags: ''
                },
                storeSetting: {
                    store_open_date: [],
                    store_open_time: [],
                    store_close_time: [],
                    store_address: ''
                },
                expressSetting: {
                    base_express_time: 25,
                    average_express_time: 10,
                    courier_count: 1
                }
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
