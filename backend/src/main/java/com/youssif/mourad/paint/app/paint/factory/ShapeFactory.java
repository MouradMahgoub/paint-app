package com.youssif.mourad.paint.app.paint.factory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.youssif.mourad.paint.app.paint.Shape;
import com.youssif.mourad.paint.app.paint.shapes.Circle;
import com.youssif.mourad.paint.app.paint.shapes.Rectangle;
import com.youssif.mourad.paint.app.paint.shapes.Square;
import com.youssif.mourad.paint.app.paint.shapes.Triangle;
import com.youssif.mourad.paint.app.paint.shapes.Ellipse;
import com.youssif.mourad.paint.app.paint.shapes.*;
import com.youssif.mourad.paint.app.paint.shapes.Rectangle;



public class ShapeFactory {
    public Shape create(Map<String, Object> properties){
        Shape shape = null;
        String type = properties.get("type").toString();


        switch(type){
            case "circle" : shape = new Circle().builder()
                    .radius((int)properties.get("radius"))
                    .build();
                break;
            case "line" : shape = new Line().builder()
                    .strokeWidth((int)properties.get("strokeWidth"))
                    .points(convertToIntArray((ArrayList<?>) properties.get("points")))
                    .build();
                break;
            case "square": shape = new Square().builder()
                    .width((int)properties.get("width"))
                    .height((int)properties.get("height"))
                    .build();
                break;
            case "rectangle" : shape = new Rectangle().builder()
                    .width((int)properties.get("width"))
                    .height((int)properties.get("height"))
                    .build();
                break;
            case "ellipse": shape = new Ellipse().builder()
                    .radiusX((int)properties.get("radiusX"))
                    .radiusY((int)properties.get("radiusY"))
                    .build();
                break;
            case "triangle" : shape = new Triangle().builder()
                    .raduis((int)properties.get("radius"))
                    .sides((int)properties.get("sides"))
                    .build();
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
            shape.setDraggable((boolean)properties.get("draggable"));
        }

        return shape;
    }
    private double[] convertToIntArray(ArrayList<?> arrayList) {
        double[] result = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = (double) arrayList.get(i);
        }
        return result;
    }


}
