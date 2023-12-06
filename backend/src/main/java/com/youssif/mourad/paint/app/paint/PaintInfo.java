package com.youssif.mourad.paint.app.paint;
import java.util.Objects;

public class PaintInfo {
    
    private String name = "";
    private String path = "";
    private String type = "json";

    public PaintInfo() {
    }

    public PaintInfo(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PaintInfo name(String name) {
        setName(name);
        return this;
    }

    public PaintInfo path(String path) {
        setPath(path);
        return this;
    }

    public PaintInfo type(String type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PaintInfo)) {
            return false;
        }
        PaintInfo paintInfo = (PaintInfo) o;
        return Objects.equals(name, paintInfo.name) && Objects.equals(path, paintInfo.path) && Objects.equals(type, paintInfo.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, type);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
