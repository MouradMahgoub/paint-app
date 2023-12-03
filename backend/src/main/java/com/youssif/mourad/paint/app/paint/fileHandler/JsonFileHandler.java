package com.youssif.mourad.paint.app.paint.fileHandler;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youssif.mourad.paint.app.paint.Paint;

public class JsonFileHandler {
    public void saveAsJsonFile(Paint paint, String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), paint);
    }

    
}
