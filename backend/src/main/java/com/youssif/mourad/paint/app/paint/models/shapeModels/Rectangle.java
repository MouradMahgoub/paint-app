package com.youssif.mourad.paint.app.paint.models.shapeModels;

import com.youssif.mourad.paint.app.paint.models.Shape;

import lombok.Builder;

public class Rectangle extends Shape{

    int width;
    int height;

    public Rectangle(){}

    @Builder
    Rectangle(String type, int id, int x, int y, String fill, String stroke, int height, int width,boolean draggable) {
        super(type, id, x, y, fill, stroke,draggable);
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int hight) {
        this.height = hight;
    }

}
