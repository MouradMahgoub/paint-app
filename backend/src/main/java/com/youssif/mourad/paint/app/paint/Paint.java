package com.youssif.mourad.paint.app.paint;

import java.util.ArrayList;
import java.util.List;

import com.youssif.mourad.paint.app.paint.shapes.Shape;
import java.util.Objects;

public class Paint {
    private List<Shape> shapes = new ArrayList<>();

    public Paint() {
    }

    public Paint(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return this.shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Paint shapes(List<Shape> shapes) {
        setShapes(shapes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Paint)) {
            return false;
        }
        Paint paint = (Paint) o;
        return Objects.equals(shapes, paint.shapes);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
            " shapes='" + getShapes() + "'" +
            "}";
    }
}