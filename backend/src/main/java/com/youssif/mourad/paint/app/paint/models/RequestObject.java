package com.youssif.mourad.paint.app.paint.models;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.youssif.mourad.paint.app.paint.service.ShapeFactory;

public class RequestObject {
    private Map<String, Object> properties = new HashMap<String, Object>();
    
    public RequestObject() {
    }
    public RequestObject(Map<String,Object> properties) {
        this.properties = properties;
    }

    public Shape createShape() {
        return new ShapeFactory().create(this.properties);
    }
    public Map<String, Object> getProperties() {
        return this.properties;
    }
    public void setProperties(Map<String,Object> properties) {
        this.properties = properties;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RequestObject)) {
            return false;
        }
        RequestObject requestObject = (RequestObject) o;
        return Objects.equals(properties, requestObject.properties);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
            " properties='" + getProperties() + "'" +
            "}";
    }
    public RequestObject properties(Map<String, Object> properties) {
        setProperties(properties);
        return this;
    }  
    
}
