package pl.rmitula.authapp.service;

import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.exception.NotFoundException;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.repository.RectangleRepository;

public class RectangleService {

    private RectangleRepository rectangleRepository;

    public RectangleService(RectangleRepository rectangleRepository) {
        this.rectangleRepository = rectangleRepository;
    }

    public List<RectangleSummary> getAllRectangles(Long id) {
        List<Rectangle> rectangleList = rectangleRepository.findByUser(id);
        ArrayList<RectangleSummary> rectangleSummaryList = new ArrayList<>();

        rectangleList.forEach(rectangle -> rectangleSummaryList.add(new RectangleSummary()))

        return RectangleSummary.builder()
                .id(rectangle.getId())
                .name(rectangle.getName())
                .description(rectangle.getDescription())
                .price(rectangle.getPrice())
                .status(rectangle.getStatus())
                .userId(rectangle.getUser().getId())
                .build();
    }

    private RectangleSummary getOneRectangle(Long id) {
        Rectangle rectangle = rectangleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found rectangle with [id: " + id + "]")
        );

        return RectangleSummary.builder()
                .id(rectangle.getId())
                .name(rectangle.getName())
                .description(rectangle.getDescription())
                .price(rectangle.getPrice())
                .status(rectangle.getStatus())
                .userId(rectangle.getUser().getId())
                .build();
    }
}
