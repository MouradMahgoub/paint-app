package com.youssif.mourad.paint.app.paint.shapes;

import com.youssif.mourad.paint.app.paint.Shape;

import lombok.Builder;

public class Square extends Shape{

    int width;
    int height;

    public Square(){}

    @Builder
    Square(String type, int id, int x, int y, String fill, String stroke, int height, int width,boolean draggable) {
        super(type, id, x, y, fill, stroke,draggable);
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
