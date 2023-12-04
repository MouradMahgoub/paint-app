package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Line extends Shape{

    int length;
    
    public Line(){}

    @Builder
    Line(String type, int id, int x, int y, String fill, String stroke) {
        super(type, id, x, y, fill, stroke);
    }

}
