<template>
  <div class="address-card">
    <div v-if="address.isDefault">
      <van-icon class="address-default-icon" color="#FFD200" name="checked" size="1.3rem"></van-icon>
    </div>
    <div v-else>
      <van-icon @click="$emit('set-default', address.id)" class="address-default-icon" color="gray" name="checked" size="1.3rem" />
      <van-icon @click="$emit('delete-address', address.id)" class="address-delete-icon" color="red" name="clear" size="1.3rem" />
    </div>
    <div @click="editAddress">
      <base-panel>
        <van-cell>
          <view slot="title" style="margin-right: 0.2rem">
            {{address.address}}
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
      editAddress () {
        mpvue.navigateTo({
          url: `/pages/my/address/edit/main?addressId=${this.address.id}`
        })
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

  .address-delete-icon {
    position: absolute;
    z-index: 1000;
    top: .1rem;
    left: .3rem;
  }
</style>
