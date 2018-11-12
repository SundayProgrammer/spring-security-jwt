package pl.rmitula.authapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.dto.SaveRectangleRequest;
import pl.rmitula.authapp.exception.NotFoundException;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.repository.RectangleRepository;
import pl.rmitula.authapp.security.CurrentUser;
import pl.rmitula.authapp.security.UserPrincipal;
import pl.rmitula.authapp.service.RectangleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rectangles")
public class RectangleController {

    private RectangleService rectangleService;
    private RectangleRepository rectangleRepository;

    @Autowired
    public RectangleController(RectangleService rectangleService, RectangleRepository rectangleRepository) {
        this.rectangleService = rectangleService;
        this.rectangleRepository = rectangleRepository;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public List<RectangleSummary> getRectanglesList(@PathVariable Long id) {
        List<Rectangle> rectangle = rectangleRepository.findByUser(id);
        if (!rectangle.isEmpty()) {
            return rectangleService.getRectangleSummaryList(rectangle);
        } else {
            throw new NotFoundException("Rectangle with given ID not found.");
        }
    }

    @PostMapping("save")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Long saveRectangle(@Valid @RequestBody SaveRectangleRequest saveRectangleRequest,
                              @CurrentUser UserPrincipal userPrincipal) {
        return rectangleService.saveRectangle(saveRectangleRequest, userPrincipal.getId());
    }
}
