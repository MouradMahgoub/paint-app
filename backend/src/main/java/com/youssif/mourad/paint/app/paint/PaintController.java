package com.youssif.mourad.paint.app.paint;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PaintController {
    
    @Autowired
    private PaintService paintService;
    
    @PostMapping("/draw")
    public List<Shape> draw(@RequestBody RequestObject requestObject) {
        return paintService.draw(requestObject.createShape());
    }
    @PostMapping("/move")
    public List<Shape> move(@RequestBody RequestObject requestObject) {
        return paintService.move(requestObject.createShape());
    }
    @PostMapping("/delete")
    public List<Shape> delete(@RequestBody RequestObject requestObject) {
        return paintService.delete(requestObject.createShape());
    }
    @PostMapping("/resize")
    public List<Shape> resize(@RequestBody RequestObject requestObject) {
        return paintService.resize(requestObject.createShape());
    }
    @PostMapping("/refill")
    public List<Shape> refill(@RequestBody RequestObject requestObject) {
        return paintService.refill(requestObject.createShape());
    }
    @PostMapping("/copy")
    public List<Shape> copy(@RequestBody RequestObject requestObject) throws CloneNotSupportedException {
        return paintService.copy(requestObject.createShape());
    }
    // @GetMapping("/undo")
    // public List<Shape> undo() {
    //     return paintService.undo();
    // }
    // @GetMapping("/redo")
    // public List<Shape> redo() {
    //     return paintService.redo();
    // }
    // @GetMapping("/save")
    // public List<Shape> save() {
    //     return paintService.save();
    // }
    // @GetMapping("/load")
    // public List<Shape> load() {
    //     return paintService.load();
    // }
    @PostMapping("/save")
    public List<Shape> save(@RequestBody PaintInfo paintInfo) throws Exception {
        return paintService.save(paintInfo);
    }
    @GetMapping("/load/info")
    public List<PaintInfo> load() throws IOException {
        return paintService.loadInfo();
    }
    @PostMapping("/load/paint")
    public List<Shape> load(@RequestBody RequestObject requestObject) throws Exception {
        return paintService.load(requestObject);
    }
}
