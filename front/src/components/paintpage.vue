<template>
  <div>
    <taskbar @undo_redo="undo_redo_clear" @clear="clear" @receiveLoad="load"/>
    <div class="drawingTools">
      <methods @shape-Edit="setController" :actionType="curAction" />
      <allShapes @shape-clicked="addNewShape" :shapeType="newShapeType" />
      <colormixer @updateCurColor="updateCurColor"/>
    </div>

      <div class="konva-holder"></div>
      <inputProps v-if="allowInput" :shape="this.shape" @updateShape="receiveChange" @close="closeInputProps"/>
  </div>
</template>

<script>
import Konva from 'konva';
import allShapes from '@/components/allShapes.vue';
import Taskbar from './taskbar.vue';
import methods from './methods.vue';
import colormixer from './colormixer.vue';
import inputProps from './inputProps.vue';
import axios from 'axios';
import { resolveTransitionHooks } from 'vue';



export default {
  name: 'paintpage',
  components: {
    Taskbar,
    methods,
    colormixer,
    allShapes,
    inputProps
  },
  data() {
    return {
      allShapes:[],
      stage: null,
      layer: null,
      shape: null,
      topLayer:null,
      curline:null,
      curColor:"black",


      takeInput:false,
      drawingShape:false,
      newShapeType:"manga",
      resize:false,
      move:false,
      copy:false,
      delete:false,
      changeColor:false,
      allowInput:false,
      curAction:null,
      isDeletingNow:false,
    };
  },
  mounted() {
    this.createKonvaStage();
    // this.fetchShapes();
  },
  methods: {

    addNewShape(shapeType){//take shape name if i click on circle/triangle ...and make update this.newShapeName
      this.clearControles();
      this.drawingShape=true;
      this.newShapeType=shapeType;
    },

    createKonvaStage() {//creating stage with events (if i click a shape then i clicked the screen it will create a shape)create line in this(special case ) 
   
   this.stage = new Konva.Stage({
     container: '.konva-holder',
     width: window.innerWidth,
     height: window.innerHeight,
   });
   this.topLayer=new Konva.Layer();
   this.layer = new Konva.Layer();
   this.stage.add(this.layer);
   this.stage.add(this.topLayer); 

   this.stage.on('click', (e) => {
      


      const mousePos = this.stage.getPointerPosition();
      if(e.evt.button === 0&&this.drawingShape&&this.newShapeType!='line'){
        // console.log(parseInt(mousePos.x), parseInt(mousePos.y));
        this.createShapeAtPosition(parseInt(mousePos.x), parseInt(mousePos.y),this.newShapeType);
      }
    });
           





    this.stage.on('mousedown', (e) => {
         
       if(e.evt.button === 0&&this.delete){
               this.isDeletingNow=true;
          }

        if (e.evt.button === 0&&this.drawingShape && this.newShapeType === 'line') {

          const mousePos = this.stage.getPointerPosition();

          this.curline = new Konva.Line({
            draggable:false,
            stroke: this.curColor,
            strokeWidth: 2,
            points: [mousePos.x+0.0000001,mousePos.y+0.0000001, mousePos.x+0.0000001,mousePos.y+0.0000001]
          });

          this.layer.add(this.curline);
        }
      });

      this.stage.on('mousemove', (e) => {
       
        if (e.evt.button === 0&&this.drawingShape && this.newShapeType === 'line' && this.curline) {
          const mousePos = this.stage.getPointerPosition();
          // Update only the end point
          let points = this.curline.points();
          points[2] = mousePos.x+0.0000001; // Update end x
          points[3] =mousePos.y+0.0000001; // Update end y
          this.curline.points(points);
          // this.layer.batchDraw();
        }
      });

      this.stage.on('mouseup', (e) => {


        if(e.evt.button === 0&&this.delete){
               this.isDeletingNow=false;
          }


          if (this.drawingShape && this.newShapeType === 'line' && this.curline) {
             
    
            this.shape = {stroke: this.curline.stroke()
              ,points: this.curline.points()
              ,strokeWidth: this.curline.strokeWidth()
              ,draggable:false
              ,id:1
              ,type:"line"
              ,x:0,
              y:0,
              fill:"black"
            };
            this.curline=null;

            // console.log(555);
            // console.log(JSON.stringify(this.shape, null, 2));
            this.layer.draw();

          this.updateLayer("draw",this.shape);
          }
       
      });
   



    },

    clearControles(){
      this.resize=false;
      this.move=false;
      this.delete=false;
      this.changeColor=false;
      this.copy=false;
      this.drawingShape=false;
      this.takeInput=false;
      this.allowInput=false;
      this.newShapeType=null;
      this.curAction=null;
    },

    updateCurColor(newColor){
       this.curColor=newColor;
    },
    
    setController(action) {//take any action from methods component and return it's name 
      this.clearControles();
      this.curAction=action;
      if (action === "resize") {
        this.resize = true;
      } else if (action === "move") {
        this.move = true;
      } else if (action === "delete") {
        this.delete = true;
      } else if (action === "changeColor") {
        this.changeColor = true;
      } else if (action === "copy") {
        this.copy = true;
      }
    },
    
    // fetchShapes() {

    //   fetch('http://localhost:3000/allShapes')
    //   .then((response) => response.json()) 
    //   .then((data) => {
    //     this.allShapes = data;
        
    //     this.allShapes.forEach((shape) => {
    //       this.drawShape(shape);
    //     });
    //   })
    //   .catch((err) => console.error('Error fetching shapes:', err));
    // },


    clearAndDraw() {//clear the layer and draw the array elements again using this.drawShape 

      this.layer.destroyChildren();

      this.allShapes.forEach((shape) => {
          this.drawShape(shape);
        });
    },
    
    // updateShapePosition(shapeId, newX, newY) {

    //   this.allShapes = this.allShapes.map(shape => {
    //   if (shape.id === shapeId) {
    //     // console.log(newX,newY);
    //     return { ...shape, x: newX, y: newY };
    //   }
    //   return shape;
    // });
    // },

    // changeShapeColor(shapeId, newColor) {
    //  this.allShapes = this.allShapes.map(shape => {
    //   if (shape.id === shapeId) {
    //     return {...shape, fill: newColor};
    //   }
    //   return shape;
    // });

    // // this.allShapes.forEach(shape => this.drawShape(shape)); // Redraw with new colors
    // },

    drawShape(shape) {// (print the array ) take a shape object and convert it to element of Konva and have the events of every shape(methods)
      // console.log(shape);
      let newShape=null;
      
      if (shape.type === 'circle') {
        newShape = new Konva.Circle(shape);
        
      } else if (shape.type === 'ellipse') {
        newShape = new Konva.Ellipse(shape);
        
      } else if (shape.type === 'triangle') {
        newShape=new Konva.RegularPolygon(shape);

      } else if (shape.type === 'rectangle') {
        newShape=new Konva.Rect(shape);

      } else if (shape.type === 'square') {
        newShape=new Konva.Rect(shape);   

      } else if (shape.type === 'line') {
        newShape=new Konva.Line(shape);    
      }

      this.layer.add(newShape);




      newShape.on('mousedown', (e) => {
          if (e.evt.button === 0&&this.move) {
            newShape.draggable(true);
          }
          // else if(e.evt.button === 0&&this.delete){
          //      this.isDeletingNow=true;
          //      this.updateLayer("delete",shape);

          // }
        });
        
      newShape.on('click', (e) => {
        
        if(e.evt.button === 0){
            this.shape=shape;
            
            // if(this.delete){
            //   this.updateLayer("delete",shape);
            // }
            // else 
            if(this.copy){
              let tempShape=shape;
              tempShape.x+=5;
              tempShape.y-=5;
              this.updateLayer("copy",tempShape);
            }
            else if(this.changeColor){
              shape.fill=this.curColor;
              this.updateLayer("refill",shape);
            }
            else if(this.resize&&this.newShapeType!='line'){//I don't need to submit the shape because inputProps will do the rest
              this.allowInput=true;
            }
         
          }


      
      });
      
      newShape.on('dragstart', (e) => {
         document.body.style.cursor = 'grabbing';
         console.log("dragStart");
            this.topLayer.add(newShape); 
            this.topLayer.draw();
          });
          
      newShape.on('dragend', (e) => {
            
        console.log("dragend");

              newShape.draggable(false); 
              this.layer.add(newShape); // Move the shape back to the original layer when dragging ends
              this.layer.draw();
              this.topLayer.clear(); // Clear the top layer
              newShape.draggable(false);
              shape.x=parseInt(newShape.attrs.x);
              shape.y=parseInt(newShape.attrs.y);
              document.body.style.cursor = 'grab';

            

              this.updateLayer("move",shape);
          
        });
        newShape.on('dragmove', (e) => {
          console.log("dragmove");
          document.body.style.cursor = 'grabbing';
        });

        newShape.on('mousemove', () => {
          if(this.isDeletingNow){
            this.updateLayer("delete",shape);
          }
          
        });
        // newShape.on('mouseup', () => {
               
        // });
        


    },


    createShapeAtPosition(x, y, shapetype) {
     const id = this.allShapes.length + 1; // Simple way to generate a unique id

     let shapeObj = {
        type: shapetype,
        id: id,
        x: x,
        y: y,
        fill: 'white',
        stroke: this.curColor,
        draggable: false

      };

      switch (shapetype) {
        case 'circle':
          shapeObj.radius = 10;
          break;
        case 'ellipse':
          shapeObj.radiusX = 10;
          shapeObj.radiusY = 25;
          break;
        case 'triangle':
          shapeObj.radius = 10;
          shapeObj.sides=3;
          break;
        case 'rectangle':
          shapeObj.width = 100;
          shapeObj.height = 50;
          break;
        case 'square':
          shapeObj.width = 50;
          shapeObj.height = 50;
          break;
      
        default:
          console.error('Unknown shape type:', shapetype);
          return;
      }

      this.shape=shapeObj;
      let temp=this.newShapeType;
      this.clearControles()
      this.newShapeType=temp;
      this.drawingShape=true;
      this.allowInput=true;


    },
   
     receiveChange(shape) {//recive change from inputProps (resize or draw new shape)

        this.allowInput = false;
       
        if(this.resize)
         this.updateLayer("resize",shape);
        else
         this.updateLayer("draw",shape);
     },

    async updateLayer(path,shape){

        try {
            await this.sendShapeToBack(path,shape);
            // console.log(JSON.stringify(this.allShapes, null, 2));

            this.clearAndDraw();

          } catch (error) {
            console.error('Error sending shape to back:', error);
          }
    },
//mourad
    sendShapeToBack(addURL,shape) {
        return new Promise((resolve, reject) => {
          let x={};
          x.properties=shape;

            console.log("sent element ")
            console.log(JSON.stringify(x, null, 2));
            console.log("allShapes before")
            console.log(JSON.stringify( this.allShapes, null, 2));
        axios.post('http://localhost:8082/'+addURL, x)
          .then(response => {

            this.allShapes = response.data;
           
            resolve(response.data);
          })
          .catch(error => {
            console.error('There was an error!', error);
            reject(error);
          });
      });
    },

    async undo_redo_clear(action){
      try {
          await this.sendActionToBack(action);
          this.clearAndDraw();
        } catch (error) {
          console.error('Error sending shape to back:', error);
        }
    },
    sendActionToBack(action) {
        return new Promise((resolve, reject) => {
        axios.get('http://localhost:8082/'+action)
          .then(response => {
            this.allShapes = response.data;

            resolve(response.data);
          })
          .catch(error => {
            console.error('There was an error!', error);
            reject(error);
          });
      });
    },
    clear(){
      this.undo_redo_clear("clear");
    },
    closeInputProps(){
      this.allowInput=false;
    },
    load(allShapes){
      this.allShapes=allShapes;
      this.clearAndDraw();
    }
 }
};
</script>

<style scoped>
.konva-holder {
  height: 100%;
  width: 99.%;
  border: 1px solid black;
  /* padding-right:-1px  ; */
}
.drawingTools{
  border: 1px solid black;
  display: flex;
}
</style>
