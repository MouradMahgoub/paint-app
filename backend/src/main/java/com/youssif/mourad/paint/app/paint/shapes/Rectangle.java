package com.youssif.mourad.paint.app.paint.shapes;

import lombok.Builder;

public class Rectangle extends Shape{

    int width;
    int hight;

    public Rectangle(){}

    @Builder
    Rectangle(String type, int id, int x, int y, String fill, String stroke, int hight, int width) {
        super(type, id, x, y, fill, stroke);
        this.hight = hight;
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHight() {
        return this.hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

}
