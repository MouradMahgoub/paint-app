<template>
    <div class="shapeActions">
      <!-- <div class="mybutton" v-for="(action, index) in controls" :key="index" @click="handleActionClick(action)">{{ action }}</div> -->

      <div 
      class="mybutton" 
      v-for="(action, index) in controls" 
      :key="index" 
      :class="{ buttonClicked: isActionClicked(action) }"
      @click="handleActionClick(action)">
      <img class="button-image" :src="require(`@/assets/${action}.png`)" alt="Logo" />

      </div>


    </div>
</template>
  
  <script>
  import changeColorCursor from '@/assets/changeColorCursor.png';
  import deleteCursor from '@/assets/deleteCursor.png';
  import resizeCursor from '@/assets/resizeCursor.png';
  import copyCursor from '@/assets/copyCursor.png';

  export default {
    name: 'methods',
    props: {
      actionType:String
    },

    data() {
      return {
        controls: ['move', 'delete', 'copy', 'resize', 'changeColor']
      };
    },
    methods: {
      handleActionClick(action) {
        if(action=='changeColor'){
  
          document.body.style.cursor = `url(${changeColorCursor}), auto`;}

        else if(action=='move'){
          document.body.style.cursor='grab';
        }
        else if(action=='delete'){
          document.body.style.cursor = `url(${deleteCursor}), auto`;
        }
        else if(action=='copy'){
          document.body.style.cursor = `url(${copyCursor}), auto`;
        }
        else if(action=='resize'){
          document.body.style.cursor = `url(${resizeCursor}), auto`;
        }

        this.$emit('shape-Edit', action);
      },

      isActionClicked(action) {
        return this.actionType == action;
      }
    }
  };
  </script>
  
  <style >
  /* Add any styling you need for the buttons here */
  .shapeActions {
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
  width:50%;
  height: 50%;
  /* border: 1px solid black; */
}
.buttonClicked {
  border: 1px solid blue;
  background-color: rgb(186, 189, 248); /* Define your desired background color */
}
  </style>
  