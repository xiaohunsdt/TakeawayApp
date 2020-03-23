import { mapMutations } from 'vuex'

export default {
  methods: {
    ...mapMutations('cart', [
      'ADD_GOODS',
      'REDUCE_GOODS'
    ]),
    onChange (event) {
      const currentVal = event.mp.detail
      // console.log(this.food.name, '---', currentVal, '---', this.currentFoodCount)
      if (currentVal > this.currentFoodCount) {
        this.ADD_GOODS(this.food)
      } else {
        this.REDUCE_GOODS(this.food)
      }
    }
  }
}
