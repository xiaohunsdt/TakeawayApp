<template>
    <div id="app">
        <section style="display: inline-block;position: absolute">
            <button type="button" id="maxbt" @click="sendIpcMsg('window-max')">max</button>
            <button type="button" id="minbt" @click="sendIpcMsg('window-min')">min</button>
            <button type="button" id="closebt" @click="sendIpcMsg('window-close')">close</button>
        </section>
        <router-view></router-view>
    </div>
</template>

<script>
  import { ipcRenderer } from 'electron'

  export default {
    name: 'windows-app',
    methods: {
      sendIpcMsg (msg) {
        ipcRenderer.send(msg)
      }
    }
  }
</script>

<style>

</style>
