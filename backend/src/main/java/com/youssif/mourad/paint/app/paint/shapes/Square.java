package com.youssif.mourad.paint.app.paint.shapes;

public class Square extends Shape{

    int sideLength;

    Square(String type, int id, int x, int y, String fill, String stroke, int sideLength) {
        super(type, id, x, y, fill, stroke);
        this.sideLength = sideLength;
        //TODO Auto-generated constructor stub
    }

}
