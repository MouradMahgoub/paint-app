package com.youssif.mourad.paint.app.paint.fileHandler;

import com.youssif.mourad.paint.app.paint.Paint;
import com.youssif.mourad.paint.app.paint.Shape;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

public class XmlFileHandler {
    public static void save(Paint currentPaint, String path) throws Exception {

        Serializer serializer = new Persister(new Format());

        serializer.write(currentPaint, new File(path));

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "ISO-8859-1")) {
            writer.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
            serializer.write(currentPaint, writer);
        }
    }

    public static Paint load(String path) throws Exception {

        Serializer serializer = new Persister(new Format());

        return serializer.read(Paint.class, new File(path));
    }
}
