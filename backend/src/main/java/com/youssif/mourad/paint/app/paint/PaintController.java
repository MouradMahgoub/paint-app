package com.youssif.mourad.paint.app.paint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youssif.mourad.paint.app.paint.shapes.Shape;

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
    public List<Shape> copy(@RequestBody RequestObject requestObject) {
        return paintService.copy(requestObject.createShape());
    }
}
