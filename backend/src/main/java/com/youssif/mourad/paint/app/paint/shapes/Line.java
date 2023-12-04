package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Line extends Shape{

    protected int length;
    
    public Line(){}

    @Builder
    Line(String type, int id, int x, int y, String fill, String stroke,int length) {
        super(type, id, x, y, fill, stroke);
        this.length=length;
    }
}
