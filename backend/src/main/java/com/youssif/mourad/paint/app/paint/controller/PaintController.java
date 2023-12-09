package com.youssif.mourad.paint.app.paint.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.youssif.mourad.paint.app.paint.models.PaintInfo;
import com.youssif.mourad.paint.app.paint.models.RequestObject;
import com.youssif.mourad.paint.app.paint.models.Shape;
import com.youssif.mourad.paint.app.paint.service.PaintService;

@RestController
@CrossOrigin
@RequestMapping
public class PaintController {
    
    @Autowired
    private PaintService paintService;

    @PostMapping("/draw")
    public List<Shape> draw(@RequestBody RequestObject requestObject) throws CloneNotSupportedException {
        return paintService.draw(requestObject.createShape());
    }
    @PostMapping("/move")
    public List<Shape> move(@RequestBody RequestObject requestObject) throws CloneNotSupportedException {
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
    public List<Shape> refill(@RequestBody RequestObject requestObject) throws CloneNotSupportedException {
        return paintService.refill(requestObject.createShape());
    }
    @PostMapping("/copy")
    public List<Shape> copy(@RequestBody RequestObject requestObject) throws CloneNotSupportedException {
        return paintService.copy(requestObject.createShape());
    }

    @GetMapping ("/undo")
    public List<Shape> undo() {
        return paintService.undo();
    }
    @GetMapping("/redo")
    public List<Shape> redo() {
        return paintService.redo();
    }
    @GetMapping("/clear")
    public List<Shape> clear() {
        return paintService.clear();
    }
    @PostMapping("/save")
    public List<Shape> save(@RequestBody PaintInfo paintInfo) throws Exception {
        return paintService.save(paintInfo);
    }
    @GetMapping("/load/info")
    public List<PaintInfo> loadInfo() throws IOException {
        return paintService.loadInfo();
    }
    @PostMapping("/load/paint")
    public List<Shape> load(@RequestBody PaintInfo paintInfo) throws Exception {
        return paintService.load(paintInfo);
    }
}
