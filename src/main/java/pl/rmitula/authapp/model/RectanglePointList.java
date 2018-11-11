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
    private Long rectangle;
    @NotNull
    private Double x;
    @NotNull
    private Double y;
}
