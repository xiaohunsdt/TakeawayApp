<template>
    <div ref="content">
        <slot></slot>
    </div>
</template>

<script>

    export default {
        name: 'MapInfoWindow',
        props: {
            marker: Object,
            isOpen: Boolean
        },
        watch: {
            isOpen(newValue) {
                newValue ? this.infoWindow.open(this.map, this.marker) : this.infoWindow.close()
                this.$emit(newValue ? 'open' : 'close', this)
            }
        },
        data() {
            return {
                infoWindow: null,
                map: null
            }
        },
        methods: {
            open(marker) {
                this.infoWindow.open(this.map, marker)
            },
            close() {
                this.infoWindow.close()
            }
        },
        mounted() {
            const naver = ((map) => {
                console.log(map)
                this.map = map
                this.infoWindow = new window.naver.maps.InfoWindow({
                    content: this.$refs.content,
                    borderWidth: 0,
                    backgroundColor: 'transparent',
                    disableAnchor: true
                })
                this.$emit('load', this)
            })
            if (!window.$naverMapsLoaded) {
                window.$naverMapsCallback.push(naver)
            } else {
                naver(window.$naverMapsObject)
            }
        },
        destroyed() {
            this.infoWindow.setMap(null)
        }
    }
</script>
