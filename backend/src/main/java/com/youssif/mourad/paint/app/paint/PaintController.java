package com.youssif.mourad.paint.app.paint;

import java.util.List;
import java.util.Map;

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
    
    @PostMapping("/create")
    public List<Shape> create(@RequestBody Map<String, Object> properties) {
        return paintService.create(properties);
    }
}
