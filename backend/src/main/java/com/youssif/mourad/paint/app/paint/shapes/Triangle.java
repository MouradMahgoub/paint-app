package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Triangle extends Shape{
    // protected int sideLength;
    protected int radius;
    protected int sides;

    public Triangle(){};
    @Builder
    Triangle(String type, int id, int x, int y, String fill, String stroke, int raduis,int sides,boolean draggable) {
        super(type, id, x, y, fill, stroke,draggable);
        //  this.sideLength = sideLength;
        this.radius=raduis;
        this.sides=sides;
    }

//    public int getSideLength() {
//        return sideLength;
//    }
//
//    public void setSideLength(int sideLength) {
//        this.sideLength = sideLength;
//    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
}
