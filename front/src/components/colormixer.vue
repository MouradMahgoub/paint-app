<template>
      <div class="WholeBar">
      <div class="color-bar">
        <div class="inline-container">
          <div class="preview" :style="{ backgroundColor: currentcolor }"></div>
          <div class="buttoncolors">
            <button class="grid-item" title="black" style="backgroundColor:#000000" @click="updatecolor('#000000')"></button>
            <button class="grid-item" title="grey" style="backgroundColor: #808080" @click="updatecolor('#808080')"></button>
            <button class="grid-item" title="Dark Red" style="backgroundColor: #6a0202" @click="updatecolor('#6a0202')"></button>
            <button class="grid-item" title="Red" style="backgroundColor:#ff0000" @click="updatecolor('#ff0000')"></button>
            <button class="grid-item" title="Orange" style="backgroundColor:#ffa500 " @click="updatecolor('#ffa500')"></button>
            <button class="grid-item" title="Yellow" style="backgroundColor:#ffff00" @click="updatecolor('#ffff00')"></button>
            <button class="grid-item" title="Green" style="backgroundColor:#008000" @click="updatecolor('#008000')"></button>
            <button class="grid-item" title="Turquoise" style="backgroundColor:#3aaea3" @click="updatecolor('#3aaea3')"></button>
            <button class="grid-item" title="blue" style="backgroundColor:#0000ff" @click="updatecolor('#0000ff')"></button>
            <button class="grid-item" title="purble" style="backgroundColor:#800080" @click="updatecolor('#800080')"></button>
            <button class="grid-item" title="White" style="backgroundColor:#ffffff" @click="updatecolor('#ffffff')"></button>
            <button class="grid-item" title="Light grey" style="backgroundColor:#d3d3d3" @click="updatecolor('#d3d3d3')"></button>
            <button class="grid-item" title="Brown" style="backgroundColor:#a52a2a" @click="updatecolor('#a52a2a')"></button>
            <button class="grid-item" title="Pink" style="backgroundColor:#ffc0cb" @click="updatecolor('#ffc0cb')"></button>
            <button class="grid-item" title="Gold" style="backgroundColor:#ffd700" @click="updatecolor('#ffd700')"></button>
            <button class="grid-item" title="Light Yellow" style="backgroundColor:#ffffe0" @click="updatecolor('#ffffe0')"></button>
            <button class="grid-item" title="Lime" style="backgroundColor:hwb(120 64% 0%)" @click="updatecolor('#a3ffa3')"></button>
            <button class="grid-item" title="Light turqoise" style="backgroundColor:#9ef2ea" @click="updatecolor('#9ef2ea')"></button>
            <button class="grid-item" title="Blue-Gray" style="backgroundColor:#8686b4" @click="updatecolor('#8686b4')"></button>
            <button class="grid-item" title="Lavander" style="backgroundColor:#965d95" @click="updatecolor('#965d95')"></button>
          </div>
        </div>
        <input class="boxx" type="color" v-model="currentcolor" @input="Changecolor" :style="{ backgroundColor: currentcolor }">
      </div>
    <!-- <div class="texts">
        <div class="curr">Current<br>&nbsp;&nbsp;Color</div>
        <div class="color">Edit<br>Color</div>
    </div> -->
    </div>
    
  </template>
  
  <script>
  export default {
    name: "ColorMixer",
    data() {
      return {
        currentcolor: "#000000",

      };
    },
    methods: {
      Changecolor() {
       this.currentcolor
       this.$emit('updateCurColor', this.currentcolor);

      },
      updatecolor(color){
        this.currentcolor=color
        this.$emit('updateCurColor', this.currentcolor);

      },
    },
  };
  </script>
  
  <style>
  .WholeBar {
    border: 1px solid rgb(219, 205, 205);
    height: auto;
    align-items: center;
    background: rgb(235, 233, 243);
    cursor: auto;

    /* background:cyan; */
    /* gap: 5px; */
    /* margin-left: 50%;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px; */
    padding-right:0.5vw ;
    padding-left:0.2vw ;
    margin: 0;
  }
  .color-bar{
    display: flex;
   
    align-items: center;
    /* gap: 5px; */
    gap: 0.3vw;
    margin-left: 19px;
  }
  
  .inline-container {
    display: flex;
    align-items: center;
  }
  
  .preview {
    cursor: pointer;
    /* height: 30px; */
    height: 1.4vw;
    width: 1.4vw;
    /* width: 30px; */
    font-size: large;
    margin-right: 1.2vw;
    /* border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px; */
    border-radius: 0.3vw;
    border: 1px solid#000;
    margin-top: 2vh;
  }
  
  .grid-item {
    cursor: pointer;
    border: 1px solid #000;
    padding: 0.5vw;
    text-align: center;
    /* height: 5px; */
    /* width: 5px; */
    margin-left: 1%;
    /* border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px; */
    border-radius: 0.3vw;

  }
  .grid-item:active{
    transform: scale(0.98); 
     box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24);         
  }
  .buttoncolors {
    
    display: grid;
    grid-template-columns: repeat(10, 1fr);
    margin-top: 2.5vh;
    gap: 0.2vw;
  }
  .texts {
  display: flex;
  flex-direction: column;
  align-items: center;
  display: grid;
  color: darkblue;
  grid-template-columns: repeat(2, .42fr);
  /* margin-right: 35px; */
}
  
  .color {
    color: black;
    margin-left: 38px;
    font-weight: 700;
  }
  .curr{
    font-family: sans-serif,'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-weight: 700;
    margin-left: 6px;
  }
  
  label {
    margin-bottom: 5px;
    font-size: 14px;
  }
  .boxx{

    cursor: pointer;
    margin-left: 3%;
    border-radius: 0.3vw;
    width: 3vw;
    height: 3vh;
/* 
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px; */
    margin-top: 2.5vh;
  }
  </style>
  