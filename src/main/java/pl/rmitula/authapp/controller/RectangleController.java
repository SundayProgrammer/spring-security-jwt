package pl.rmitula.authapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.service.RectangleService;

@RestController
@RequestMapping("/api/rectangles")
public class RectangleController {

    private RectangleService rectangleService;

    @Autowired
    public RectangleController(RectangleService rectangleService) {
        this.rectangleService = rectangleService;
    }

    @GetMapping("{id}")
    public RectangleSummary getRectangle() {
        return rectangleService.getRectangleSummary();
    }
}
