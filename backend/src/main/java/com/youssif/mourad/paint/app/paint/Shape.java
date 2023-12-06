package com.youssif.mourad.paint.app.paint;
import java.util.Objects;


public abstract class Shape implements Cloneable{
    protected String type;
    protected String fill;
    protected String stroke;
    protected int id;
    protected int x;
    protected int y;
    protected boolean draggable;

    public Shape() {
    }
    
    public Shape(String type, int id, int x, int y, String fill, String stroke, boolean draggable) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.stroke = stroke;
        this.draggable = draggable;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getFill() {
        return this.fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getStroke() {
        return this.stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shape)) {
            return false;
        }
        Shape shape = (Shape) o;
        return Objects.equals(type, shape.type) && id == shape.id && x == shape.x && y == shape.y && Objects.equals(fill, shape.fill) && Objects.equals(stroke, shape.stroke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, x, y, fill, stroke);
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", id='" + getId() + "'" +
            ", x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", fill='" + getFill() + "'" +
            ", stroke='" + getStroke() + "'" +
            "}";
    }


}
