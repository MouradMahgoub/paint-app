package com.youssif.mourad.paint.app.paint;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youssif.mourad.paint.app.paint.shapes.Shape;

@Service
public class PaintService {
    
    private Paint currentPaint = new Paint();
    
    List<Shape> create(Shape shape) {
        currentPaint.addShape(shape);
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
