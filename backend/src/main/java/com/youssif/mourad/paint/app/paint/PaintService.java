package com.youssif.mourad.paint.app.paint;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youssif.mourad.paint.app.paint.factory.CircleFactory;
import com.youssif.mourad.paint.app.paint.shapes.Circle;
import com.youssif.mourad.paint.app.paint.shapes.Shape;

@Service
public class PaintService {
    
    private Paint currentPaint = new Paint();
    
    List<Shape> create(Map<String, Object> properties) {
        String type = properties.get("type").toString();
        switch(type){
            case "circle" : currentPaint.addShape(new CircleFactory().create(properties));
            break;
            case "square" :
            break;
            case "rectangle" :
            break;
            case "ellipse" :
            break;
            case "triangle" :
            break;
            default : return currentPaint.getShapes();
        }
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
