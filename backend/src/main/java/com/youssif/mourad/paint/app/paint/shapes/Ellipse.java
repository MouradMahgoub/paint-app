package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Ellipse extends Shape{

    int radiusX;
    int radiusY;

    public Ellipse(){}

    @Builder
    Ellipse(String type, int id, int x, int y, String fill, String stroke) {
        super(type, id, x, y, fill, stroke);
    }

}
