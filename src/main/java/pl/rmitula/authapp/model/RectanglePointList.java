package pl.rmitula.authapp.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "points")
public class RectanglePointList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "points")
    private Rectangle rectangle;
    @NotNull
    private Double x;
    @NotNull
    private Double y;

    public RectanglePointList(Rectangle rectangle, @NotNull Double x, @NotNull Double y) {
        this.rectangle = rectangle;
        this.x = x;
        this.y = y;
    }
}
