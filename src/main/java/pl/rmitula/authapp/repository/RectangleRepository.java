package pl.rmitula.authapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.authapp.model.Rectangle;
import pl.rmitula.authapp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
    List<Rectangle> findByUser(Long id);
    List<Rectangle> findByUser(User user);
    Optional<Rectangle> findByUserOrName(Long id, String name);
}
