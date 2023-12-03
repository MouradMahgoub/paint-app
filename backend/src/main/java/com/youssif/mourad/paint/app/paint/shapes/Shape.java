package com.youssif.mourad.paint.app.paint.shapes;
import java.util.Objects;

public abstract class Shape {
    protected String type;
    protected int id;
    protected int x;
    protected int y;
    protected String fill;


    public Shape() {
    }

    public Shape(String type, int id, int x, int y, String fill) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.fill = fill;
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

    public Shape type(String type) {
        setType(type);
        return this;
    }

    public Shape id(int id) {
        setId(id);
        return this;
    }

    public Shape x(int x) {
        setX(x);
        return this;
    }

    public Shape y(int y) {
        setY(y);
        return this;
    }

    public Shape fill(String fill) {
        setFill(fill);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shape)) {
            return false;
        }
        Shape shape = (Shape) o;
        return Objects.equals(type, shape.type) && id == shape.id && x == shape.x && y == shape.y && Objects.equals(fill, shape.fill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, x, y, fill);
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", id='" + getId() + "'" +
            ", x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", fill='" + getFill() + "'" +
            "}";
    }
    
}
