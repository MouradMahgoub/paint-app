package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;


public class Circle extends Shape{

    protected int radius;

    public Circle(){};

    @Builder
    Circle(String type, int id, int x, int y, String fill, String stroke, int radius,boolean draggable) {
        super(type, id, x, y, fill, stroke,draggable);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}


