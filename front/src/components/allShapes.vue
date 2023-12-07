<template>
  <div class="shapes">
    <div 
      class="mybutton" 
      v-for="(shape, index) in shapes" 
      :key="index" 
      :class="{ buttonClicked: isShapeClicked(shape) }"
      @click="handleShapeClick(shape)">
      <img class="button-image" :src="require(`@/assets/${shape}.png`)" alt="Logo" />
    </div>
  </div>
</template>
  
  <script>
  export default {
    name: 'allShapes',
    props: {
      shapeType:String
    },

    data() {
      return {
        shapes: ['line', 'circle', 'ellipse', 'triangle', 'rectangle', 'square']
      };
    },
    methods: {
      handleShapeClick(shapeType) {
        document.body.style.cursor = 'crosshair';
        this.$emit('shape-clicked', shapeType);
    
      },
      isShapeClicked(shape) {
      return this.shapeType == shape;
      }
    },
   
  };
  </script>
  
  <style scoped>
  /* Add any styling you need for the buttons here */
  .shapes {
    width: 100%;
    /* background: rgb(31, 6, 95); */
    border: 1px solid black;
    /* border-radius: 5px; */
    display: flex;
    justify-content: center;
    padding: 10px;
    gap: 10px;

  }
  
  .mybutton{
    display: flex; 
    justify-content: center;
    align-items: center; 
    background: rgb(242, 232, 232);
    font-size: bold;
    border-radius: 10px;
    cursor: pointer;
    border: 1px solid #b2acac;
    padding: 10px;
    width: 100%;


  }
.mybutton:hover {
  background-color: #ccc; /* Change the background color on hover */
}
.mybutton:active {
  background-color: gray; /* Change the background color while pressing */
}
.button-image {
  /* max-width: 60px;
  max-height: 60px; */
  width: 70%;
  height: 70%;
  /* border: 1px solid black; */
}
.buttonClicked {
  border: 1px solid blue;
  background-color: rgb(186, 189, 248); /* Define your desired background color */
  /* cursor: crosshair; */
  
}
  </style>
  