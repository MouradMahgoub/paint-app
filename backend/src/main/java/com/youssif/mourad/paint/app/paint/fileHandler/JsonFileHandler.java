package com.youssif.mourad.paint.app.paint.fileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youssif.mourad.paint.app.paint.Paint;
import com.youssif.mourad.paint.app.paint.PaintInfo;
import com.youssif.mourad.paint.app.paint.Shape;
import com.youssif.mourad.paint.app.paint.factory.ShapeFactory;

public class JsonFileHandler {
    public static void save(Paint paint, String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), paint);
    }

    public static Paint load(String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> paintObject = objectMapper.readValue(new File(path), new TypeReference<Map<String, Object>>() {});
        ShapeFactory shapeFactory = new ShapeFactory();
        Paint paint = new Paint();
        List<Map<String, Object>> shapes =  (List<Map<String, Object>>)paintObject.get("shapes");
        for(Map<String, Object> properties : shapes)
            paint.getShapes().add(shapeFactory.create(properties));
        paint.setName(paintObject.get("name").toString());
        paint.setPath(paintObject.get("path").toString());
        return paint; 
    }

    public static void saveInfo(List<PaintInfo> paintsInfo) throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File("C:\\Users\\mourad mahgoub\\OneDrive\\Desktop\\OOP\\paint-app\\backend\\src\\main\\resources\\paintInfo.json"), paintsInfo);
    }
    public static List<PaintInfo> loadInfo() throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(new File("C:\\Users\\mourad mahgoub\\OneDrive\\Desktop\\OOP\\paint-app\\backend\\src\\main\\resources\\paintInfo.json"), new TypeReference<List<PaintInfo>>() {});
    }
}
