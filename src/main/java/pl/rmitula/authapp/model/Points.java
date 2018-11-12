package pl.rmitula.authapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "points")
public class Points {

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

    public Points(Rectangle rectangle, @NotNull Double x, @NotNull Double y) {
        this.rectangle = rectangle;
        this.x = x;
        this.y = y;
    }
}
