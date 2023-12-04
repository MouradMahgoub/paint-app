package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Ellipse extends Shape{

    int radiusX;
    int radiusY;

    public Ellipse(){}

    @Builder
    Ellipse(String type, int id, int x, int y, String fill, String stroke, int radiusX, int radiusY) {
        super(type, id, x, y, fill, stroke);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public int getRadiusX() {
        return this.radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public int getRadiusY() {
        return this.radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }
}
