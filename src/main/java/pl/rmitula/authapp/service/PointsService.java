package pl.rmitula.authapp.service;

import org.springframework.stereotype.Service;
import pl.rmitula.authapp.dto.PointSummary;
import pl.rmitula.authapp.model.Points;

import java.util.List;

@Service
public class PointsService {

    public PointSummary getPoint(Points points) {
        return PointSummary.builder()
                .x(points.getX())
                .y(points.getY())
                .build();
    }
}
