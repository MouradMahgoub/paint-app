package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Line extends Shape{

    protected int strokeWidth;
    protected double[] points = new double[4];

    public Line(){};

    @Builder
    Line(String type, int id, int x, int y, String fill, String stroke,boolean draggable,int strokeWidth,double[] points) {
        super(type, id, x, y, fill, stroke,draggable);
        this.strokeWidth=strokeWidth;
        this.points=points;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }
}
