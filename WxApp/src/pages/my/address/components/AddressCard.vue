<template>
  <div class="address-card">
    <div v-if="address.isDefault" @click="cancelDefault">
      <van-icon class="address-default-icon" name="checked" color="#FFD200" size="1.3rem"></van-icon>
    </div>
    <div v-else @click="setDefault">
      <van-icon class="address-default-icon" name="checked" color="gray" size="1.3rem"></van-icon>
    </div>
    <div @click="editAddress">
      <base-panel>
        <van-cell>
          <view slot="title" style="margin-right: 0.2rem">
            {{address.region}}
            <br/>
            {{address.detail}}
          </view>
          <view slot="icon" style="margin-right: 0.2rem">
            <van-icon color="#FFD200" name="location" size="1.2rem"/>
          </view>
        </van-cell>
        <van-cell :title="address.phone">
          <view slot="icon" style="margin-right: 0.2rem">
            <van-icon color="#FFD200" name="phone" size="1.2rem"/>
          </view>
        </van-cell>
      </base-panel>
    </div>
  </div>
</template>

<script>
    import BasePanel from '@/components/BasePanel'

    export default {
        name: 'AddressCard',
        props: {
            address: {
                type: Object,
                required: true
            }
        },
        components: {
            BasePanel
        },
        methods: {
            editAddress (event) {
                console.log(event)
            },
            setDefault () {
                console.log('set default')
            },
            cancelDefault () {
                console.log('cancel default')
            }
        }
    }
</script>

<style scoped>
  .address-card {
    position: relative;
  }

  .address-default-icon {
    position: absolute;
    z-index: 1000;
    top: .1rem;
    right: .3rem;
  }
</style>
