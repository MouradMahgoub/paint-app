package com.youssif.mourad.paint.app.paint.factory;

import java.util.Map;

import com.youssif.mourad.paint.app.paint.shapes.Shape;


public abstract class ShapeFactory {
    public Shape create(Map<String, Object> properties) {
        Shape shape = createShape(properties);
        shape.draw(shape);
        return shape;
    }
    protected abstract Shape createShape(Map<String, Object> properties);
}
