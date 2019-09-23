<template>
  <div>
    <el-card>
      <div slot="header">
        <span>개인 정보</span>
      </div>
      <div id="info">
        이름: {{ userData.name }}
        <br>
        <br>
        <div style="color: #13ce66">
          계산 방법: {{ userData.nickName }}
        </div>
        <br>
        Money: {{ userData.money }}
        <br>
        <br>
        Point: {{ userData.point }}
        <br>
        <br>
      </div>
    </el-card>
  </div>
</template>

<script>
    import { mapGetters } from 'vuex'

    export default {
        name: 'InfoPanel',
        computed: {
            ...mapGetters([
                'userData'
            ])
        }
    }
</script>

<style scoped>
  #info {
    width: 100%;
  }
</style>
