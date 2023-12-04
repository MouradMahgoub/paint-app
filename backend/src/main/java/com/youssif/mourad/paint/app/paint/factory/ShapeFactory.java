package com.youssif.mourad.paint.app.paint.factory;

import java.util.Map;

import com.youssif.mourad.paint.app.paint.shapes.Circle;
import com.youssif.mourad.paint.app.paint.shapes.Rectangle;
import com.youssif.mourad.paint.app.paint.shapes.Shape;
import com.youssif.mourad.paint.app.paint.shapes.Square;
import com.youssif.mourad.paint.app.paint.shapes.Triangle;
import com.youssif.mourad.paint.app.paint.shapes.Ellipse;


public class ShapeFactory {
    public Shape create(Map<String, Object> properties){ 
        Shape shape = null;
        String type = properties.get("type").toString();

            switch(type){
                case "circle" : shape = new Circle().builder()
                                        .radius((int)properties.get("radius"))
                                        .build();
                break;
                case "square": shape = new Square().builder()
                                        .sideLength((int)properties.get("sideLength"))
                                        .build();
                break;
                case "rectangle" : shape = new Rectangle().builder()
                                            .width((int)properties.get("width"))
                                            .hight((int)properties.get("hight"))
                                            .build();
                break;
                case "ellipse": shape = new Ellipse().builder()
                                            .radiusX((int)properties.get("radiusX"))
                                            .radiusY((int)properties.get("radiusY"))
                                            .build();
                break;
                case "triangle" : shape = new Triangle();
                break;
                default: return null;
            }
            if(shape != null){
                shape.setType(type);
                shape.setId((int)properties.get("id"));
                shape.setX((int)properties.get("x"));
                shape.setY((int)properties.get("y"));
                shape.setFill(properties.get("fill").toString());
                shape.setStroke(properties.get("stroke").toString());
            }
            return shape;
    }
}
