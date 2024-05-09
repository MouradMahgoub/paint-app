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
      mousedown:false,

    };
  },
  mounted() {
    this.startUp();
    // this.createKonvaStage();
    // this.clearAndDraw();
    // this.fetchShapes();
  },
  methods: {
    
  async startUp() {
    await this.createKonvaStage();
    // this.undo_redo_clear('undo');//we van raplace it be fetching shapes from backend
    this.undo_redo_clear('redo');
  },
    addNewShape(shapeType){//take shape name if i click on circle/triangle ...and make update this.newShapeName
      this.clearControles();
      this.drawingShape=true;
      this.newShapeType=shapeType;
    },

    async createKonvaStage() {//creating stage with events (if i click a shape then i clicked the screen it will create a shape)create line in this(special case ) 
      const viewportWidth = Math.max(document.documentElement.clientWidth || 0, window.innerWidth || 0);
      const viewportHeight = Math.max(document.documentElement.clientHeight || 0, window.innerHeight || 0);

      const stageWidth = viewportWidth * (98.8 / 100); // Replace percentageWidth with your desired percentage
     const stageHeight = viewportHeight * (80 /100); // Replace percentageHeight with your desired percentage
      this.stage = new Konva.Stage({
     container: '.konva-holder',
     width: stageWidth,
     height: stageHeight,
   });
   this.topLayer=new Konva.Layer();
   this.layer = new Konva.Layer();
   this.stage.add(this.layer);
   this.stage.add(this.topLayer); 

    let shapebyMouse=null;
    let KonvaShape=null;

    //  this.stage.on('click',(e)=>{
    //   if(e.currentTarget === this.stage&&e.evt.button === 0&&this.changeColor){
       
    //     var container = this.stage.container();
    //     container.style.backgroundColor = this.curColor;
    //     this.layer.draw();
    //       }
    //  });

    this.stage.on('mousedown', (e) => {
      if(this.drawingShape){
        shapebyMouse={
          type:this.newShapeType,
          x:this.stage.getPointerPosition().x,
          y:this.stage.getPointerPosition().y,
          fill:"transparent",
          stroke:this.curColor
        };

         if(this.newShapeType=='circle'){shapebyMouse.radius=0;}

         else if(this.newShapeType=='rectangle'){shapebyMouse.width=0;shapebyMouse.height=0;}

         else if(this.newShapeType=='square'){shapebyMouse.width=0;shapebyMouse.height=0;}

         else if(this.newShapeType=='triangle'){shapebyMouse.radius=0;shapebyMouse.sides=3;}

         else if(this.newShapeType=='ellipse'){shapebyMouse.radiusX=0;shapebyMouse.radiusY=0;}

        KonvaShape=this.drawKonvaShape(shapebyMouse);
        
        
        this.layer.add(KonvaShape).batchDraw();
        
        this.mousedown=true;

       }


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
       
        if(this.mousedown&&this.drawingShape&&this.newShapeType!='line'){
          const mouseX = this.stage.getPointerPosition().x;
          const mouseY = this.stage.getPointerPosition().y;
          const startX = KonvaShape.attrs.x;
          const startY = KonvaShape.attrs.y;



            if(this.newShapeType=='circle'||this.newShapeType=='triangle'){
              const rise=Math.pow(mouseY-startY,2);
              const run=Math.pow(mouseX-startX,2);
              const newRadius=Math.sqrt(rise+run);
            
            KonvaShape.attrs.radius=parseInt(newRadius);
          }
          else if(this.newShapeType === 'rectangle' || this.newShapeType === 'square'){
            const width = mouseX - startX;
            const height = mouseY - startY;
            KonvaShape.attrs.width = width;

            if(this.newShapeType === 'square'){
              KonvaShape.attrs.height=width;
            }
            else
            KonvaShape.attrs.height=height;
          
          }
          else if(this.newShapeType==='ellipse'){
            const radiusX = Math.abs(mouseX - startX) / 2;
            const radiusY = Math.abs(mouseY - startY) / 2;
            KonvaShape.attrs.radiusX = radiusX;
            KonvaShape.attrs.radiusY = radiusY;
          }
        this.layer.batchDraw(KonvaShape); 
        }

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
        
        this.mousedown=false;
        if(this.drawingShape&&this.newShapeType!='line'){
          
          this.updateLayer("draw",this.convertToObject(KonvaShape));

        }

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
    
    async  clearAndDraw() {//clear the layer and draw the array elements again using this.drawShape 

      this.layer.destroyChildren();

      this.allShapes.forEach((shape) => {
          this.drawShape(shape);
        });
    },
   
    drawKonvaShape(shape){
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


      return newShape;
    },


    drawShape(shape) {// (print the array ) take a shape object and convert it to element of Konva and have the events of every shape(methods)
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
        
        });
        
      newShape.on('click', (e) => {
        
        if(e.evt.button === 0){
            this.shape=shape;
            
           
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
            this.topLayer.add(newShape); 
            this.topLayer.draw();
          });
          
      newShape.on('dragend', (e) => {
            

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
          document.body.style.cursor = 'grabbing';
        });

      newShape.on('mousemove', () => {
          if(this.isDeletingNow){
            this.updateLayer("delete",shape);
          }
          
        });
        


    },

    convertToObject(KonvaShape){
    
      let shapeObj = {
        type: KonvaShape.attrs.type,
        id:1,
        x: parseInt(KonvaShape.attrs.x),
        y: parseInt(KonvaShape.attrs.y),
        fill: 'transparent',
        stroke: this.curColor,
        draggable: false
      };
      switch (shapeObj.type) {
        case 'circle':
          shapeObj.radius = KonvaShape.attrs.radius ;
          break;
        case 'ellipse':
          shapeObj.radiusX =parseInt(KonvaShape.attrs.radiusX);
          shapeObj.radiusY =parseInt(KonvaShape.attrs.radiusY);
          break;
        case 'triangle':
          shapeObj.radius = KonvaShape.attrs.radius;
          shapeObj.sides=KonvaShape.attrs.sides;
          break;
        case 'rectangle':
          shapeObj.width = parseInt(KonvaShape.attrs.width);
          shapeObj.height = parseInt(KonvaShape.attrs.height);
          break;
        case 'square':
          shapeObj.width = parseInt(KonvaShape.attrs.width);
          shapeObj.height = parseInt(KonvaShape.attrs.height);
          break;
      
        default:
          console.error('Unknown shape type:', shapetype);
          return;
      }
      return shapeObj;
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

            this.clearAndDraw();

          } catch (error) {
            console.error('Error sending shape to back:', error);
          }
    },

    sendShapeToBack(addURL,shape) {
        return new Promise((resolve, reject) => {
          let x={};
          x.properties=shape;

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
    },
 }
};
</script>

<style scoped>
.konva-holder {
  height: 80vh;
  width:98.8vw;
  border: 1px solid black;
  /* padding-right:-1px  ; */
}
.drawingTools{
  border: 1% solid black;
  display: flex;
}
</style>
