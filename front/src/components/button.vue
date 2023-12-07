<template>
  <div class="button">

  </div>
</template>

<script>
 export default {
    props: {
     imgsrc,

     
  },
  mounted(){
   this.imagePath = require(`@/assets/${imgsrc}`);
  },
    name: 'button',
    data() {
      return {
        imagePath:null,      

      };
    },

    methods: {
    
    }

};

</script>

<style>
.button{
    width: auto;
}
</style>