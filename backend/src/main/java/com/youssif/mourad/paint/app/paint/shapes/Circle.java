package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;
import lombok.Getter;


public class Circle extends Shape{

    protected int radius;

    public Circle(){};

    @Builder
    Circle(String type, int id, int x, int y, String fill, String stroke, int radius) {
        super(type, id, x, y, fill, stroke);
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}


