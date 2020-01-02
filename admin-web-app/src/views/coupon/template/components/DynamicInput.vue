<template>
  <div class="dynamic-input">
    <el-form-item :label="label" v-if="modelArray.length===0">
      <el-button @click.prevent="addModel" circle icon="el-icon-plus" size="mini" type="success"/>
    </el-form-item>
    <el-form-item
      :key="model.key"
      :label="`${label}(${index})`"
      :prop="`model.${index}`.value"
      :rules="{required: true, message: '不能为空', trigger: 'blur'}"
      v-for="(model, index) in modelArray">
      <el-input v-model="model.value" @change="onValueChanged">
        <el-button slot="append" icon="el-icon-delete" @click.prevent="removeModel(model)"></el-button>
      </el-input>
    </el-form-item>
    <div style="text-align: center">
      <el-button @click.prevent="addModel" circle icon="el-icon-plus" size="mini" type="success"
                 v-if="modelArray.length>0"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'DynamicInput',
    props: {
      modelArray: {
        type: Array,
        required: true
      },
      label: {
        type: String,
        required: true
      }
    },
    methods: {
      onValueChanged(value) {
        this.$emit('update:modelArray', this.modelArray)
      },
      addModel() {
        this.modelArray.push({
          key: new Date().getTime(),
          value: ''
        })
      },
      removeModel(model) {
        const index = this.modelArray.indexOf(model)
        if (index !== -1) {
          this.modelArray.splice(index, 1)
        }
      }
    }
  }
</script>

<style lang="scss">
  .dynamic-input .el-input-group__append {
    cursor: pointer;
  }

  .dynamic-input .el-form-item {
    margin-bottom: 12px;
  }
</style>

<style lang="scss" scoped>
  .dynamic-input{
    margin-bottom: 10px;
  }
</style>
