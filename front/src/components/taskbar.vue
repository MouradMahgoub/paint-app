<template>
  <div>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <div class="taskbar">
      <span class="btnsaveload">
        <button class="btn" @click="show_save">
          <i class="fa fa-save"></i>&nbsp;save
        </button>
        <button class="btn" @click="show_load" @keyup.ctrl.s="handleSaveShortcut">
          <i class="fa fa-spinner"></i>&nbsp;Load
        </button>
      </span>
      <button class="btn" @click="undo" @keyup.ctrl.z="handleUndoShortcut">
        <i class="fa fa-undo"></i>&nbsp;Undo
      </button>
      <button class="btn" @click="redo" @keyup.ctrl.y="handleRedoShortcut">
        <i class="fa fa-rotate-right"></i>&nbsp;Redo
      </button>
      <button class="btn" @click="clear">
        <i class="fa fa-broom"></i>
        clear
      </button>
    </div>
    <div class="savebox" v-if="sa">
      <span>File name:&nbsp;</span>
      <input type="text" v-model="Fn" />
      <span> &nbsp;&nbsp;File Path:&nbsp;</span>
      <input type="text" v-model="Fp" />
      <span> &nbsp;&nbsp;File type:&nbsp;</span>
      <select v-model="filetype" class="scroll">
        <option value="json">Json</option>
        <option value="xml">Xml</option>
      </select>
      <button @click="save(Fp, Fn, filetype)">Save</button>
      <button @click="show_save">Close</button>
    </div>
  
    <div class="loads" v-if="lo">
      <span>Available loads: &nbsp;</span>
      <select v-model="choosenPath"  @change="load" class="scroll">
        <option v-for="(layer, index) in loadInfo" :key="index" :value="layer">
          {{ layer.name }}
        </option>
      </select>
      <button id="closeSave" @click="show_load">Close</button>
    </div>
  </div> 
  </template>
  
  <script>

  import axios from 'axios';
  export default {
    
    name: "taskbar",
    data() {
      return {
        Fn: "",
        Fp: "",
        sa: false,
        lo: false,
        allShapes:[],
        choosenPath:null,
        loadInfo:null,
        filetype: "Json",
      };
    },
    mounted() {
      window.addEventListener("keyup", this.handleUndoShortcut);
      window.addEventListener("keyup", this.handleRedoShortcut);
    },
    beforeDestroy() {
      window.removeEventListener("keyup", this.handleUndoShortcut);
      window.removeEventListener("keyup", this.handleRedoShortcut);
    },
    methods: {
      show_save() {
        if (this.lo) {
          this.show_load();
        }
        this.sa = !this.sa;
      },

      save(filepath, filename, filet) {
        this.Fn = filename;
        this.Fp = filepath;
        this.fliletype = filet;
        this.show_save();
        // console.log(JSON.stringify({name: this.Fn , type:this.filetype , path:this.Fp}, null, 2));

        return new Promise((resolve, reject) => {
          
          // console.log(JSON.stringify({name: this.Fn , type:this.filetype , path:this.Fp}, null, 2));
           let x ={name: this.Fn , type:this.filetype , path:this.Fp+"\\"+this.Fn+"."+ this.fliletype};
           console.log(JSON.stringify(x, null, 2));
           axios.post('http://localhost:8082/save', x)
           .then(response => {
             
            //  console.log(222222222);
                this.allShapes = response.data;
                resolve(response.data);
              })
              .catch(error => {
                console.error('There was an error!', error);
                reject(error);
              });
        });
      },

      show_load() {

        if (this.sa) {
          this.show_save();
        }
        this.lo = !this.lo;
   
           axios.get('http://localhost:8082/load/info')
               .then(response => {
                 this.loadInfo = response.data;
                //  resolve(response.data);
               })
               .catch(error => {
                 console.error('There was an error!', error);
                //  reject(error);
               });
      },

      load() {
        
        
        this.show_load();

       
          axios.post('http://localhost:8082/load/paint',this.choosenPath)
              .then(response => {
                this.allShapes= response.data;
                this.$emit('receiveLoad',this.allShapes);
                // resolve(response.data);
              })
              .catch(error => {
                console.error('There was an error!', error);
                // reject(error);
              });
        
            

      },
      handleUndoShortcut(event) {
        if (event.ctrlKey && event.key === "z") {
          event.preventDefault();
          this.undo();
        }
      },
  
      undo() {
  
        this.$emit('undo_redo',"undo");
      },
      handleRedoShortcut(event) {
        if (event.ctrlKey && event.key === "y") {
          event.preventDefault();
          this.redo();
        }
      },
      redo() {
        this.$emit('undo_redo',"redo");
      },
      clear() {
        this.$emit('clear');
          
      },
    },
  };
  </script>
  <style>
  .btnsaveload {
    margin-left: 0.5%;
    margin-right: 3%;
  }
  
  .taskbar {
    align-content: left ;
    justify-content: space-around;
    width: auto;
    height: auto;
    cursor: auto;
    /* background-color: rgb(7, 7, 223);
    background: rgb(235, 233, 243); */
    background-color: navy;
    /* border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px; */
    /* padding: 5px; */
    padding: 0.5vh  0.3vw;
    text-align: left;

  }
  .btn {
    text-align: center;
    /* font-size: 14px; */
    font-size: 1.1vw;
    color: white;
    width: 7%;
    height: auto;
    /* padding: 9px 16px; */
    padding: 1vh 1.2vw;
    
    background-color: dodgerblue;
    /* border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    border-bottom-left-radius: 5px; */
    /* border-bottom-right-radius: 5px; */
    border-radius: 0.3vw;

    align-content:center;
    font: bold;
    cursor: pointer;
  }
  .btn:hover {
    background-color: rgb(0, 0, 0);
  }
  .savebox {
    /* height: 2vh; */
    display: flex;
    border: 2px solid #5a7e5b;
    padding: 0.5vw;
    /* margin: 1vh 0; */
    cursor: auto;
    background-color: #f8f8f8;
    /* border-radius: 5px; */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  .loads {
    border: 2px solid #5a7e5b;
    padding: 0.5vw;
    /* margin: 10px 0; */
    background-color: #f8f8f8;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    gap: 3%;
  }
  .scroll {
    width: 20%;
    height: 8%;
  }
  #closeSave {
    margin-left: 1vw;
  }
  #SaveDone {
    margin-left: 1vw;
    }
  .fa fa-save {
    margin-right: 10%;
  }
  </style>
  