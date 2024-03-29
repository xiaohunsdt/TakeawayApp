import produceService from '../../services/produce'
import myBehavior from '../mixins/goods-cart'
import {
  isObjectValueEqual
} from '../../utils/index'

Component({
  behaviors: [myBehavior],
  observers: {
    'produce': function (newVal) {
      if (newVal && newVal.selectedGoods) {
        this.setData({
          currentFoodCount: this.getCurrentFoodCount()
        })
      }
    },
    'isShow': function (newVal) {
      if (newVal && this.data.produceDetail) {
        const selectedSpec = {}
        let disableGoodsList = this.data.produceDetail.goodsList.filter(goods => goods.state !== 'ON')
        for (const key in this.data.produceDetail.specs.selectedSpecs) {
          selectedSpec[key] = null
        }
        this.setData({
          selectedSpec,
          disableGoodsList
        })
        this.checkInitDisableSpecOption()
      }
    },
    'selectedSpec': function (newVal) {
      let needArr = ['请选择']
      let selectedArr = ['已选']
      let isAllSelected = true
      for (const key in this.data.produceDetail.specs.selectedSpecs) {
        if (this.data.selectedSpec[key] === null) {
          isAllSelected = false
          needArr.push(this.data.produceDetail.specs.selectedSpecs[key])
        } else {
          selectedArr.push(newVal[key])
        }
      }
      if (isAllSelected) {
        const produce = this.data.produce
        produce.selectedGoods = this.data.produceDetail.goodsList.find(goods => isObjectValueEqual(goods.ownSpecs, newVal))
        // console.log(produce)
        this.setData({
          produce,
          needselectSpecsStr: selectedArr.join(' ')
        })
      } else {
        this.setData({
          needselectSpecsStr: needArr.join(' ')
        })
      }
      this.setData({
        isAllSelected
      })
    },
    'disableSpecOption': function (newVal) {
      const disableSpecOptionView = {}
      for (const specId in newVal) {
        for (const option of newVal[specId]) {
          if (!disableSpecOptionView[specId]) {
            disableSpecOptionView[specId] = {}
          }
          disableSpecOptionView[specId][option] = true
        }
      }
      this.setData({
        disableSpecOptionView
      })
    },
    'initDisableSpecOption': function (newVal) {
      this.setData({
        disableSpecOption: Object.assign({}, newVal)
      })
    }
  },
  data: {
    isShow: false,
    produce: null,
    produceDetail: null,
    needselectSpecsStr: null,
    selectedSpec: {},
    disableGoodsList: [],
    initDisableSpecOption: {},
    disableSpecOption: {},
    disableSpecOptionView: {},
    isAllSelected: false,
    currentFoodCount: 0
  },
  methods: {
    openDialog(produce) {
      wx.showLoading({
        title: '正在加载...'
      })
      produceService.getDetailById(produce.id)
        .then(res => {
          res.specs.selectedSpecs = JSON.parse(res.specs.selectedSpecs)
          res.specs.options = JSON.parse(res.specs.options)
          res.goodsList.forEach(goods => {
            goods.ownSpecs = JSON.parse(goods.ownSpecs)
          })
          this.setData({
            produce,
            produceDetail: res,
            isShow: true
          })
        })
        .finally(() => {
          wx.hideLoading()
        })
    },
    onClose() {
      this.setData({
        isShow: false
      })
      setTimeout(() => {
        this.setData({
          needselectSpecsStr: null,
          selectedSpec: {},
          disableGoodsList: [],
          initDisableSpecOption: {},
          disableSpecOption: {},
          disableSpecOptionView: {},
          isAllSelected: false,
          currentFoodCount: 0
        })
      }, 500)
    },
    chooseSku(event) {
      const selectedSpec = this.data.selectedSpec
      const specId = event.currentTarget.dataset.specId
      const option = event.currentTarget.dataset.option

      if (selectedSpec[specId] !== option) {
        selectedSpec[specId] = option
      } else {
        selectedSpec[specId] = null
      }
      this.checkStock(selectedSpec)
      this.setData({
        selectedSpec
      })
    },
    checkInitDisableSpecOption() {
      const initDisableSpecOption = {}
      if (this.data.disableGoodsList.length > 0) {
        let specIds = Object.keys(this.data.produceDetail.specs.selectedSpecs)
        for (const specId of specIds) {
          for (let index = 0; index < this.data.produceDetail.specs.options[specId].length; index++) {
            const option = this.data.produceDetail.specs.options[specId][index]
            let useable = false
            this.data.produceDetail.goodsList
              .filter(goods => goods.ownSpecs[specId] === option)
              .forEach(goods => {
                if (goods.state === 'ON') {
                  useable = true
                }
              })
            if (!useable) {
              if (!initDisableSpecOption[specId]) {
                initDisableSpecOption[specId] = []
              }
              initDisableSpecOption[specId].push(option)
            }
          }
        }
      }
      console.log(initDisableSpecOption)
      this.setData({
        initDisableSpecOption
      })
    },
    checkStock(selectedSpec) {
      let result = true
      let specsObj = this.data.produceDetail.specs.options
      let filterSkuGoodsList = function (goodsList, selectedSpec) {
        return goodsList.filter(goods => {
          let result = true
          for (const specId in selectedSpec) {
            const option = selectedSpec[specId]
            if (option !== null) {
              result = goods.ownSpecs[specId] === selectedSpec[specId]
              if (!result) {
                break;
              }
            }
          }
          return result;
        })
      }
      const disableSpecOption = Object.assign({}, this.data.initDisableSpecOption)
      for (const specId in selectedSpec) {
        const option = selectedSpec[specId]
        if (option === null) {
          const tempSelectedSpec = Object.assign({}, selectedSpec)
          for (const specVal of specsObj[specId]) {
            let useAble = false
            tempSelectedSpec[specId] = specVal
            filterSkuGoodsList(this.data.produceDetail.goodsList, tempSelectedSpec)
              .forEach(goods => {
                if (goods.state === 'ON') {
                  useAble = true
                }
              })
            if (!useAble) {
              if (!disableSpecOption[specId]) {
                disableSpecOption[specId] = []
              }
              disableSpecOption[specId].push(specVal)
            }
          }
        }
      }

      // 检测当前的选择项中是否存在不允许的选择项
      this.setData({
        disableSpecOption
      })
      return result
    }
  }
})