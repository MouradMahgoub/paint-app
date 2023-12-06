package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Triangle extends Shape{
    protected int sideLength;

    public Triangle(){};
    @Builder
    Triangle(String type, int id, int x, int y, String fill, String stroke, boolean draggable, int sideLength) {
        super(type, id, x, y, fill, stroke, draggable);
        this.sideLength = sideLength;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
}
