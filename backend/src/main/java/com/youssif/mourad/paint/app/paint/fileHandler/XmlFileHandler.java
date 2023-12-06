package com.youssif.mourad.paint.app.paint.fileHandler;

import com.youssif.mourad.paint.app.paint.Paint;
import com.youssif.mourad.paint.app.paint.Shape;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlFileHandler {
    public static List<Shape> save (Paint currentPaint, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Paint.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.marshal(currentPaint, new File(path));
        return currentPaint.getShapes();
    }
    public static Paint load(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Paint.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Paint paint = (Paint) unmarshaller.unmarshal(new File(path));
        return paint;
    }
}
