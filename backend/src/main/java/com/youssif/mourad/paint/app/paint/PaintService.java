package com.youssif.mourad.paint.app.paint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.youssif.mourad.paint.app.paint.fileHandler.JsonFileHandler;
import com.youssif.mourad.paint.app.paint.fileHandler.XmlFileHandler;

@Service
@XmlSeeAlso({ArrayList.class, LinkedList.class})
public class PaintService {
    
    private Paint currentPaint = new Paint();
    
    List<Shape> draw(Shape shape) {
        currentPaint.getShapes().add(shape);
        return currentPaint.getShapes();
    }
    List<Shape> copy(Shape shape) throws CloneNotSupportedException {
        Shape clonedShape = shape.clone();
        int cloneedShapeId = 0;
        for(Shape targetShape : currentPaint.getShapes())
            cloneedShapeId = Math.max(cloneedShapeId, targetShape.getId());
        clonedShape.setId(cloneedShapeId + 1);
        currentPaint.getShapes().add(clonedShape);
        return currentPaint.getShapes();
    }
    List<Shape> move(Shape shape) {
        for(Shape targetShape : currentPaint.getShapes()){
            if(targetShape.getId() == shape.getId()){
                targetShape.setX(shape.getX());
                targetShape.setY(shape.getY());
                break;
            }
        }
        return currentPaint.getShapes();
    }
    List<Shape> resize(Shape shape) {
        for(Shape targetShape : currentPaint.getShapes()){
            if(targetShape.getId() == shape.getId()){
                currentPaint.getShapes().set(currentPaint.getShapes().indexOf(targetShape), shape);
                break;
            }
        }
        return currentPaint.getShapes();
    }
    List<Shape> refill(Shape shape) {
        for(Shape targetShape : currentPaint.getShapes()){
            if(targetShape.getId() == shape.getId()){
                targetShape.setFill(shape.getFill());
                targetShape.setStroke(shape.getStroke());
                break;
            }
        }
        return currentPaint.getShapes();
    }
    List<Shape> delete(Shape shape) {
        currentPaint.getShapes().remove(shape);
        return currentPaint.getShapes();
    }
    // List<Shape> undo() {

    // }
    // List<Shape> redo() {

    // }

}
