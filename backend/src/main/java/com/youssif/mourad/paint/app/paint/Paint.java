package com.youssif.mourad.paint.app.paint;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Paint {
    private List<Shape> Shapes = new ArrayList<Shape>();

    public Paint() {
    }

    public Paint(List<Shape> Shapes) {
        this.Shapes = Shapes;
    }

    public List<Shape> getShapes() {
        return this.Shapes;
    }

    public void setShapes(List<Shape> Shapes) {
        this.Shapes = Shapes;
    }

    public Paint Shapes(List<Shape> Shapes) {
        setShapes(Shapes);
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
        return Objects.equals(Shapes, paint.Shapes);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
            " Shapes='" + getShapes() + "'" +
            "}";
    }

}
