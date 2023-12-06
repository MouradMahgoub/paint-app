package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Ellipse extends Shape{

    protected int radiusX;
    protected int radiusY;

    public Ellipse(){}

    @Builder
    Ellipse(String type, int id, int x, int y, String fill, String stroke,int radiusX,int radiusY, boolean draggable) {
        super(type, id, x, y, fill, stroke, draggable);
        this.radiusX=radiusX;
        this.radiusY=radiusY;
    }

    public int getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }
}
