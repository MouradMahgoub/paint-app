package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Line extends Shape{

    protected int length;
    
    public Line(){}

    @Builder
    Line(String type, int id, int x, int y, String fill, String stroke,int length, boolean draggable) {
        super(type, id, x, y, fill, stroke, draggable);
        this.length=length;
    }
}
