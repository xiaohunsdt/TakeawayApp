<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <van-field
        value="{{ username }}"
        required
        clearable
        label="地址"
        icon="question-o"
        placeholder="请输入地址,"
        @click-icon="onClickIcon"
      />
    </div>
  </div>
</template>

<script>
    import BasePanel from '@/components/BasePanel'

    export default {
        name: 'AddressEdit',
        props: {
            addressId: {
                type: Number,
                required: false
            }
        },
        components: {
            BasePanel
        },
        data () {
            return {}
        },
        methods: {}
    }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }
</style>
