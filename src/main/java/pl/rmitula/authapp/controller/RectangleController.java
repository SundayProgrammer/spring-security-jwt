package pl.rmitula.authapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.exception.NotFoundException;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.repository.RectangleRepository;
import pl.rmitula.authapp.service.RectangleService;

import java.util.Optional;

@RestController
@RequestMapping("/api/rectangles")
public class RectangleController {

    private RectangleService rectangleService;
    private RectangleRepository rectangleRepository;

    @Autowired
    public RectangleController(RectangleService rectangleService) {
        this.rectangleService = rectangleService;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public RectangleSummary getRectangle(@RequestParam Long id) {
        Optional<Rectangle> rectangle = rectangleRepository.findByUser(id);
        if (((Optional) rectangle).isPresent()) {
            return rectangleService.getRectangleSummary(rectangle.get());
        } else {
            throw new NotFoundException("Rectangle with given ID not found.");
        }
    }
}
