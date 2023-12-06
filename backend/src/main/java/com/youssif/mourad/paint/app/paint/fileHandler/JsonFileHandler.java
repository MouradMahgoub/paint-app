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
    public void saveAsJsonFile(Paint paint, String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), paint);
    }

    
}
