package com.youssif.mourad.paint.app.paint.fileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youssif.mourad.paint.app.paint.Paint;
import com.youssif.mourad.paint.app.paint.Shape;
import com.youssif.mourad.paint.app.paint.factory.ShapeFactory;

public class JsonFileHandler {
    public static void save(Paint paint, String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), paint);
    }

    public static Paint load(String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Paint currentPaint = objectMapper.readValue(new File(path), new TypeReference<Paint>() {});
        // ShapeFactory shapeFactory = new ShapeFactory();
        // List<Shape> paint = new ArrayList<Shape>();
        // for(Map<String, Object> properties : shapes)
        //     paint.add(shapeFactory.create(properties));
        return currentPaint; 
    }
}
