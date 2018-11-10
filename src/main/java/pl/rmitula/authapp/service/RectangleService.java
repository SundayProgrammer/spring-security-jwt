package pl.rmitula.authapp.service;

import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.model.Rectangle;

public class RectangleService {

    public RectangleSummary getRectangleSummary(Rectangle rectangle) {
        return RectangleSummary.builder()
                .id(rectangle.getId())
                .name(rectangle.getName())
                .description(rectangle.getDescription())
                .price(rectangle.getPrice())
                .status(rectangle.getStatus())
                .userId(rectangle.getUserId())
                .build();
    }
}
