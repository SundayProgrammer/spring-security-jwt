package pl.rmitula.authapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.authapp.dto.RectangleSummary;
import pl.rmitula.authapp.dto.SaveRectangleRequest;
import pl.rmitula.authapp.exception.AppException;
import pl.rmitula.authapp.exception.BadRequestException;
import pl.rmitula.authapp.exception.NotFoundException;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.model.RectanglePointList;
import pl.rmitula.authapp.model.User;
import pl.rmitula.authapp.repository.RectanglePointsRepository;
import pl.rmitula.authapp.repository.RectangleRepository;
import pl.rmitula.authapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RectangleService {

    private RectangleRepository rectangleRepository;
    private UserRepository userRepository;
    private RectanglePointsRepository rectanglePointsRepository;

    @Autowired
    public RectangleService(RectangleRepository rectangleRepository,
                            UserRepository userRepository,
                            RectanglePointsRepository rectanglePointsRepository) {
        this.rectangleRepository = rectangleRepository;
        this.userRepository = userRepository;
        this.rectanglePointsRepository = rectanglePointsRepository;
    }

    public List<RectangleSummary> getAllRectangles(Long id) {
        List<Rectangle> rectangleList = rectangleRepository.findByUser(id);
        if (rectangleList.isEmpty()) {
            throw new NotFoundException("Not found rectangles for user with [id: " + id + "]");
        }
        ArrayList<RectangleSummary> rectangleSummaryList = new ArrayList<>();

        rectangleList.forEach(rectangle -> rectangleSummaryList.add(new RectangleSummary(
                rectangle.getId(),
                rectangle.getName(),
                rectangle.getAddress(),
                rectangle.getDescription(),
                rectangle.getPrice(),
                rectangle.getStatus().getStatusName(),
                rectangle.getPoints(),
                rectangle.getUser().getId()
        )));

        return rectangleSummaryList;
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
                .status(rectangle.getStatus().getStatusName())
                .userId(rectangle.getUser().getId())
                .build();
    }

    public List<RectangleSummary> getRectangleSummaryList(List<Rectangle> rectangles) {
        List<RectangleSummary> rectangleSummaries = new ArrayList<>();
        rectangles.forEach(rectangle -> rectangleSummaries.add(new RectangleSummary(
                rectangle.getId(),
                rectangle.getName(),
                rectangle.getAddress(),
                rectangle.getDescription(),
                rectangle.getPrice(),
                rectangle.getStatus().getStatusName(),
                rectangle.getPoints(),
                rectangle.getUser().getId()
        )));

        return rectangleSummaries;
    }

    public Long saveRectangle(SaveRectangleRequest request, Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            Rectangle rectangle = new Rectangle(
                    request.getName(),
                    request.getAddress(),
                    request.getDescription(),
                    user.get()
            );

            long recId = rectangleRepository.save(rectangle).getId();

            Optional<Rectangle> createdRectangle = rectangleRepository.findById(recId);
            Rectangle rec = createdRectangle.get();

            if (createdRectangle.isPresent()) {
                request.getPoints().forEach(point -> {
                    rectanglePointsRepository
                            .save(new RectanglePointList(rec, point.getX(), point.getY()));
                });

                List<RectanglePointList> points = rectanglePointsRepository.findAllByRectangle(rec);
                rec.setPoints(points);

                rectangleRepository.save(rec);
            } else {
                throw new AppException("Can not create rectangle");
            }

            return rec.getId();
        } else {
            throw new BadRequestException("Bad request");
        }
    }
}
