package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Rectangel extends Shape{

    int width;
    int hight;

    public Rectangel(){}

    @Builder
    Rectangel(String type, int id, int x, int y, String fill, String stroke) {
        super(type, id, x, y, fill, stroke);
    }

}
