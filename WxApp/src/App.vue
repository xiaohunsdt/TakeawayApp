<script>
  export default {
    created () {
      console.log(`log at:${Date.now()}`)
    }
  }
</script>

<style>
  page {
    height: 100%;
  }

  .container {
    background-color: #F0F0F0;
    min-height: 100%;
    width: 100%;
  }

  .gradientDiv {
    position: absolute;
    background: linear-gradient(#FFD200, white, #F0F0F0);
    width: 100%;
    height: 3rem;
  }
</style>
