<template>
    <div class="backdrop" @click.self="closeComponent">
      <div class="center-container">
        <div class="takeInput">

          <!-- <button class="close-button" @click="closeComponent">X</button> -->
          <div class="ShowError" v-if="error">Invalid dimentions ! </div>
           
            <label v-if="shape.type === 'circle'" for="radiusInput">Radius:</label>
            <input v-if="shape.type === 'circle'" id="radiusInput" v-model.number="shape.radius" placeholder="Enter Radius">

            <label v-if="shape.type === 'triangle'" for="radiusInput">Side length:</label>
            <input v-if="shape.type === 'triangle'" id="radiusInput" v-model.number="this.sidelenght" placeholder="Enter Side length:">

            <label v-if="shape.type === 'rectangle' " for="widthInput">Width:</label>
            <input v-if="shape.type === 'rectangle'" id="widthInput" v-model.number="shape.width" placeholder="Enter Width">
            
            <label v-if="shape.type === 'rectangle'" for="heightInput">Height:</label>
            <input v-if="shape.type === 'rectangle'" id="heightInput" v-model.number="shape.height" placeholder="Enter Height">
            
            <label v-if="shape.type === 'square' " for="widthInput">Side length:</label>
            <input v-if="shape.type === 'square'" id="widthInput" v-model.number="shape.width" placeholder="Enter Side length">

            <label v-if="shape.type === 'ellipse'" for="radiusX">radiusX:</label>
            <input v-if="shape.type === 'ellipse'" id="radiusX" v-model.number="shape.radiusX" placeholder="Enter radiusX">

            <label v-if="shape.type === 'ellipse'" for="radiusY">radiusY:</label>
            <input v-if="shape.type === 'ellipse'" id="radiusY" v-model.number="shape.radiusY" placeholder="Enter radiusX">
            
          <button @click="saveShape">Save</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'inputProps',
    props: {
        shape: {
        type: Object,
        default: () => ({}),
        },
    },
    data() {
    return {
      error:false,
      sidelenght:0
    }
    },
    mounted(){
      if(this.shape.type=='triangle')
      this.sidelenght= Math.ceil(this.shape.radius*Math.sqrt(3))+1;
    },
    methods: {
      saveShape() {
        
        if(this.validation(this.shape))
          this.$emit('updateShape',this.shape);
        else{
          this.error=true;
        }
    },
    validation(shape){
      switch (shape.type) {
        case 'square':

          if(shape.width<1)
            return false;

          this.shape.height=this.shape.width;
          break;
        case 'circle':

          if(shape.radius<1)
              return false;
          break;

        case 'rectangle':
            if(shape.height<1 ||shape.width<1 )
                return false;

          break;

        case 'ellipse':
            if(shape.radiusX<1 ||shape.radiusX<1 )
                    return false;
          break;
        case 'triangle':
            if(shape.radius<1)
                  return false;
             this.shape.radius=parseInt(this.sidelenght/Math.sqrt(3)); 
          break;
     
      
        default:
          break;
      }
      return true;
    },
    closeComponent() {
    this.$emit('close');
    }
    },
  };
  </script>
  
  <style scoped>
  .backdrop {
    cursor: pointer;
    background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black background */
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 999; /* Ensure the backdrop is above other elements */
  }
  
  .center-container {
    cursor: pointer;
    background-color: white; /* Background color for the centered component */
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5); /* Box shadow for the centered component */
    padding: 20px;
  }
  
  .takeInput {
    width: 400px;
    display: flex;
    flex-direction: column;
    align-items: center; 
  }
  
  label {
    margin-top: 10px;
  }
  
  input, select {
    width: 100%;
    margin-bottom: 10px;
  }
 .ShowError{
  font-size: bold;
  color: red;
 } 
  /* .close-button {
   position: absolute;
    border: 1px solid black;
    top: 10px;
    right: 10px;
    border: none;
    background-color: transparent;
    font-size: 16px;
    cursor: pointer;
  }

  .close-button::after {
    content: 'X';
    color: #333;
    font-weight: bold;
  } */ 
  </style>
  