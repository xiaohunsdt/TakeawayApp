<template>
  <div :style="{height:height,width:width}">
    test
  </div>
</template>

<script>
import resize from './mixins/resize'

export default {
  name: 'SimpleStoreCard',
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
  data() {
    return {
      store: null
    }
  },
  mounted() {
  },
  methods: {}
}
</script>
