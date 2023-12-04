package com.youssif.mourad.paint.app.paint;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.youssif.mourad.paint.app.paint.factory.ShapeFactory;
import com.youssif.mourad.paint.app.paint.shapes.Shape;

@Service
public class PaintService {
    
    private Paint currentPaint = new Paint();
    
    List<Shape> create(Map<String, Object> properties) {
        ShapeFactory shapeFactory = new ShapeFactory();
        currentPaint.addShape(shapeFactory.create(properties));
        return currentPaint.getShapes();
    }
    // List<Shape> copy() {
        
    // }
    // List<Shape> move() {
        
    // }
    // List<Shape> resize() {
        
    // }
    // List<Shape> refill() {
        
    // }
    // List<Shape> delete() {
        
    // }
}
