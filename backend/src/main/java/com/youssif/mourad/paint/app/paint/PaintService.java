package com.youssif.mourad.paint.app.paint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.stereotype.Service;

import com.youssif.mourad.paint.app.paint.fileHandler.JsonFileHandler;
import com.youssif.mourad.paint.app.paint.fileHandler.XmlFileHandler;

@Service
@XmlSeeAlso({ArrayList.class, LinkedList.class})
public class PaintService {
    private Stack<List<Shape>> stack1 = new Stack<>();
    private Stack<List<Shape>> stack2 = new Stack<>();
    
    private String paintInfoPath = "C:\\Users\\elkarnk\\Code\\paint-mourad\\backend\\src\\main\\resources\\paintInfo.json";
    private Paint currentPaint = new Paint();

    List<Shape> draw(Shape shape) throws CloneNotSupportedException {
        stack2.clear();
        List<Shape> currentState = new ArrayList<>(currentPaint.getShapes());
        Shape clonedShape = shape.clone();
        int cloneedShapeId = 0;
        for(Shape targetShape : currentState)
            cloneedShapeId = Math.max(cloneedShapeId, targetShape.getId());
        clonedShape.setId(cloneedShapeId + 1);
        currentState.add(clonedShape);
        stack1.push(new ArrayList<>(currentState));
        currentPaint.setShapes(currentState);
        return currentPaint.getShapes();
    }



    List<Shape> copy(Shape shape) throws CloneNotSupportedException {
        stack2.clear();
        List<Shape> currentState = new ArrayList<>(currentPaint.getShapes());
        Shape clonedShape = shape.clone();
        int cloneedShapeId = 0;
        for(Shape targetShape : currentState)
            cloneedShapeId = Math.max(cloneedShapeId, targetShape.getId());
        clonedShape.setId(cloneedShapeId + 1);
        currentState.add(clonedShape);
        currentPaint.setShapes(new ArrayList<>(currentState));
        stack1.push(currentState);
        return currentState;
    }
    List<Shape> move(Shape shape) throws CloneNotSupportedException {
        stack2.clear();

        // Create a deep copy of the list of shapes
        List<Shape> currentState = new ArrayList<>();
        Shape movedShape = null;
        for (Shape originalShape : currentPaint.getShapes()) {
            Shape clonedShape = originalShape.clone();

            // Update the fill and stroke properties of the target shape
            if (clonedShape.getId() == shape.getId()) {
                clonedShape.setX(shape.getX());
                clonedShape.setY(shape.getY());
                movedShape = clonedShape;
            }else{
                currentState.add(clonedShape);
            }
        }
        if (movedShape != null) {
            currentState.add(movedShape);
        }

        currentPaint.setShapes(new ArrayList<>(currentState));
        // Push a deep copy of the currentState onto stack1
        stack1.push(new ArrayList<>(currentState));

        return currentState;
    }
    List<Shape> resize(Shape shape) {
        stack2.clear();
        List<Shape> currentState = new ArrayList<>(currentPaint.getShapes());
        for(Shape targetShape : currentState){
            if(targetShape.getId() == shape.getId()){
                currentState.set(currentState.indexOf(targetShape), shape);
                break;
            }
        }
        currentPaint.setShapes(new ArrayList<>(currentState));
        stack1.push(currentState);
        return currentState;
    }
    List<Shape> refill(Shape shape) throws CloneNotSupportedException {
        stack2.clear();

        // Create a deep copy of the list of shapes
        List<Shape> currentState = new ArrayList<>();
        for (Shape originalShape : currentPaint.getShapes()) {
            Shape clonedShape = originalShape.clone();

            // Update the fill and stroke properties of the target shape
            if (clonedShape.getId() == shape.getId()) {
                clonedShape.setFill(shape.getFill());
                clonedShape.setStroke(shape.getStroke());
            }

            currentState.add(clonedShape);
        }

        currentPaint.setShapes(new ArrayList<>(currentState));
        // Push a deep copy of the currentState onto stack1
        stack1.push(new ArrayList<>(currentState));

        return currentState;
    }

    List<Shape> delete(Shape shape) {
        stack2.clear();
        currentPaint.getShapes().remove(shape);
        stack1.push(new ArrayList<> (currentPaint.getShapes()));
        return currentPaint.getShapes();
    }
    List<Shape> undo() {
        if (!stack1.isEmpty()) {
            stack2.push(new ArrayList<>(stack1.pop()));
        }
        List<Shape> back;
        if(stack1.isEmpty()){
            back = new ArrayList<>();
        }else{
            back =stack1.peek();
        }
        currentPaint.setShapes(back);
        return back;
    }
    List<Shape> redo() {

        List<Shape> back;
        if(stack2.isEmpty()){
            back=currentPaint.getShapes();
        }else{
            stack1.push(new ArrayList<>(stack2.pop()));
            back=stack1.peek();
            currentPaint.setShapes(back);
        }
        return back;
    }
    List<Shape> clear(){
        currentPaint = new Paint();
        return currentPaint.getShapes();
    }
    List<Shape> save(PaintInfo paintInfo) throws Exception {
        List<PaintInfo> paintsInfo = new ArrayList<PaintInfo>();
        if((new File(paintInfoPath)).length() > 0) 
            paintsInfo = JsonFileHandler.loadInfo(paintInfoPath);
        paintsInfo.add(paintInfo);
        JsonFileHandler.saveInfo(paintsInfo, paintInfoPath);

        if(paintInfo.getType().toString().toLowerCase().equals("json")){
            JsonFileHandler.save(currentPaint, paintInfo.getPath().toString());
            return currentPaint.getShapes(); 
        }else{
            XmlFileHandler.save(currentPaint, paintInfo.getPath().toString());
            return currentPaint.getShapes();
        }
    }
    List<Shape> load(PaintInfo paintInfo)throws Exception {
        // String type = requestObject.getProperties().get("type").toString();
        // String path = requestObject.getProperties().get("path").toString();
        if(paintInfo.getType().toLowerCase().equals("json")){
            currentPaint = JsonFileHandler.load(paintInfo.getPath());
            return currentPaint.getShapes(); 
        }else{
            currentPaint = XmlFileHandler.load(paintInfo.getPath());
            return currentPaint.getShapes();
        }
    }
    List<PaintInfo> loadInfo () throws IOException {
        return JsonFileHandler.loadInfo(paintInfoPath);
    }

}
