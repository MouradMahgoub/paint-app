package com.youssif.mourad.paint.app.paint.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.stereotype.Service;

import com.youssif.mourad.paint.app.paint.models.Paint;
import com.youssif.mourad.paint.app.paint.models.PaintInfo;
import com.youssif.mourad.paint.app.paint.models.Shape;
import com.youssif.mourad.paint.app.paint.service.fileHandler.JsonFileHandler;
import com.youssif.mourad.paint.app.paint.service.fileHandler.XmlFileHandler;

@Service
@XmlSeeAlso({ArrayList.class, LinkedList.class})
public class PaintService {
    private Stack<List<Shape>> stack1 = new Stack<>();
    private Stack<List<Shape>> stack2 = new Stack<>();
    
    private static final String paintInfoPath =  Paths.get("").toAbsolutePath().resolve("src/main/resources/paintInfo.json").toString();    
    private Paint currentPaint = new Paint();

    public List<Shape> draw(Shape shape) throws CloneNotSupportedException {
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

    public List<Shape> copy(Shape shape) throws CloneNotSupportedException {
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
    public List<Shape> move(Shape shape) throws CloneNotSupportedException {
        stack2.clear();
        List<Shape> currentState = new ArrayList<>();
        Shape movedShape = null;
        for (Shape originalShape : currentPaint.getShapes()) {
            Shape clonedShape = originalShape.clone();
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
        stack1.push(new ArrayList<>(currentState));

        return currentState;
    }
    public List<Shape> resize(Shape shape) {
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
    public List<Shape> refill(Shape shape) throws CloneNotSupportedException {
        stack2.clear();
        List<Shape> currentState = new ArrayList<>();
        for (Shape originalShape : currentPaint.getShapes()) {
            Shape clonedShape = originalShape.clone();
            if (clonedShape.getId() == shape.getId()) {
                clonedShape.setFill(shape.getFill());
                clonedShape.setStroke(shape.getStroke());
            }
            currentState.add(clonedShape);
        }
        currentPaint.setShapes(new ArrayList<>(currentState));
        stack1.push(new ArrayList<>(currentState));
        return currentState;
    }

    public List<Shape> delete(Shape shape) {
        stack2.clear();
        currentPaint.getShapes().remove(shape);
        stack1.push(new ArrayList<> (currentPaint.getShapes()));
        return currentPaint.getShapes();
    }
    public List<Shape> undo() {
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
    public List<Shape> redo() {

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
    public List<Shape> clear(){
        currentPaint = new Paint();
        return currentPaint.getShapes();
    }
    public List<Shape> save(PaintInfo paintInfo) throws Exception {
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
    public List<Shape> load(PaintInfo paintInfo)throws Exception {
        if(paintInfo.getType().toString().toLowerCase().equals("json")){
            currentPaint = JsonFileHandler.load(paintInfo.getPath());
            return currentPaint.getShapes(); 
        }else{
            currentPaint = XmlFileHandler.load(paintInfo.getPath());
            return currentPaint.getShapes();
        }
    }
    public List<PaintInfo> loadInfo () throws IOException {
        return JsonFileHandler.loadInfo(paintInfoPath);
    }

}
