package com.youssif.mourad.paint.app.paint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youssif.mourad.paint.app.paint.shapes.Shape;

@RestController
@RequestMapping(value = "")
public class PaintController {
    
    @Autowired
    private PaintService paintService;
    
}
