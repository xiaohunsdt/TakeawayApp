<template>
  <div>
    <div class="tag-view">
      <el-tag :disable-transitions="false" :key="tag.key" @close="handleClose(tag)" closable v-for="tag in distancePriceArr">
        {{ tag.key }}米以上, 起送价: ₩ {{ tag.value }}
      </el-tag>
      <el-button @click="showInput" class="button-new-tag" size="small" v-if="!inputVisible">+ 添加</el-button>
    </div>
    <div class="dynamic-input" v-if="inputVisible">
      <div class="add-operation">
        <el-input class="input-with-select" placeholder="请输入距离下限" style="width: 120px;margin-right: 8px" v-model.number="key"/>
        :
        <el-input class="input-with-select" placeholder="请输入起送价格" style="width: 120px;margin: 0px 8px" v-model.number="value"/>
        <el-button @click="handleInputConfirm" type="success">添加</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'

export default {
  name: 'DynamicInput',
  model: {
    prop: 'distancePriceArr',
    event: 'change'
  },
  props: {
    distancePriceArr: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      inputVisible: false,
      key: null,
      value: null
    }
  },
  methods: {
    handleClose(tag) {
      this.distancePriceArr.splice(this.distancePriceArr.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
    },

    handleInputConfirm() {
      if (this.key && this.value) {
        this.distancePriceArr.forEach(item => {

        })
        for (let i = 0; i < this.distancePriceArr.length; i++) {
          const item = this.distancePriceArr[i]
          if (item.key === this.key) {
            Message({
              message: '存在相同距离的设置! 请确认!',
              type: 'error',
              duration: 3 * 1000
            })
            return
          }
        }
        this.distancePriceArr.push(
            {
              key: this.key,
              value: this.value
            }
        )
      }
      this.inputVisible = false
      this.key = ''
      this.value = ''

      this.distancePriceArr.sort((a, b) => {
        return b.key - a.key
      })
      this.$emit('change', this.distancePriceArr)
    }
  }
}
</script>

<style lang="scss" scoped>
//.dynamic-input {
//  margin: 10px 0px;
//}
.tag-view {
  display: flex;
  justify-content: start;
  flex-wrap: wrap;
}

.add-operation {
  display: flex;
  justify-content: start;
}

.el-tag {
  margin-right: 10px;
  margin-bottom: 10px;
}

.button-new-tag {
  //margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
