package com.youssif.mourad.paint.app.paint.factory;

import java.util.Map;

import com.youssif.mourad.paint.app.paint.shapes.Circle;
import com.youssif.mourad.paint.app.paint.shapes.Rectangel;
import com.youssif.mourad.paint.app.paint.shapes.Shape;
import com.youssif.mourad.paint.app.paint.shapes.Square;
import com.youssif.mourad.paint.app.paint.shapes.Triangle;
import com.youssif.mourad.paint.app.paint.shapes.Ellipse;


public class ShapeFactory {
    public Shape create(Map<String, Object> properties){ 
        Shape shape = null;
        String type = properties.get("type").toString();

            switch(type){
                case "circle" : shape = new Circle();
                break;
                case "square": shape = new Square();
                break;
                case "rectangle" : shape = new Rectangel();
                break;
                case "ellipse": shape = new Ellipse();
                break;
                case "triangle" : shape = new Triangle();
                break;
                default: return null;
            }
            return shape;
    }
}
