package com.youssif.mourad.paint.app.paint;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Paint {
    private String name;
    private String path;
    private List<Shape> Shapes = new    ArrayList<Shape>();

    public Paint() {
    }

    public Paint(String name, String path, List<Shape> Shapes) {
        this.name = name;
        this.path = path;
        this.Shapes = Shapes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Shape> getShapes() {
        return this.Shapes;
    }

    public void setShapes(List<Shape> Shapes) {
        this.Shapes = Shapes;
    }

    public Paint name(String name) {
        setName(name);
        return this;
    }

    public Paint path(String path) {
        setPath(path);
        return this;
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
        return Objects.equals(name, paint.name) && Objects.equals(path, paint.path) && Objects.equals(Shapes, paint.Shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, Shapes);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", Shapes='" + getShapes() + "'" +
            "}";
    }

    
}
