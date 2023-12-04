package com.youssif.mourad.paint.app.paint;

import java.util.List;

import org.springframework.stereotype.Service;
import com.youssif.mourad.paint.app.paint.shapes.Shape;

@Service
public class PaintService {
    
    private Paint currentPaint = new Paint();

    List<Shape> draw(Shape shape) {
        currentPaint.getShapes().add(shape);
        return currentPaint.getShapes();
    }
    List<Shape> copy(Shape shape) {
        // Shape clonedShape = 
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
}
