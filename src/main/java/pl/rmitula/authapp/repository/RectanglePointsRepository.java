package pl.rmitula.authapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.model.RectanglePointList;

import java.util.List;

@Repository
public interface RectanglePointsRepository extends JpaRepository<RectanglePointList, Long> {
    List<RectanglePointList> findAllByRectangle(Rectangle rectangle);
}
