package com.youssif.mourad.paint.app.paint.service.fileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youssif.mourad.paint.app.paint.models.Paint;
import com.youssif.mourad.paint.app.paint.models.PaintInfo;
import com.youssif.mourad.paint.app.paint.service.ShapeFactory;

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
        // paint.setName(paintObject.get("name").toString());
        // paint.setPath(paintObject.get("path").toString());
        return paint; 
    }

    public static void saveInfo(List<PaintInfo> paintsInfo, String paintInfoPath) throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File(paintInfoPath), paintsInfo);
    }
    public static List<PaintInfo> loadInfo(String paintInfoPath) throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(new File(paintInfoPath), new TypeReference<List<PaintInfo>>() {});
    }
}
