package com.youssif.mourad.paint.app.paint.factory;


import java.util.Map;

import com.youssif.mourad.paint.app.paint.shapes.Circle;
import com.youssif.mourad.paint.app.paint.shapes.Shape;

public class CircleFactory extends ShapeFactory{
    protected Shape createShape(Map<String, Object> properties){
        Circle shape = Circle.builder()
                        .type(properties.get("type").toString())
                        .id((int)properties.get("id"))
                        .x((int)properties.get("x"))
                        .y((int)properties.get("y"))
                        .fill(properties.get("fill").toString())
                        .stroke(properties.get("stroke").toString())
                        .radius((int)properties.get("radius"))
                        .build();
        return shape;
    }

}
