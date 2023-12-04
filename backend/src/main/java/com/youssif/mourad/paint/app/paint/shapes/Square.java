package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Square extends Shape{

    int sideLength;

    public Square(){}

    @Builder
    Square(String type, int id, int x, int y, String fill, String stroke, int sideLength) {
        super(type, id, x, y, fill, stroke);
        this.sideLength = sideLength;
    }

    public int getSideLength() {
        return this.sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
}
