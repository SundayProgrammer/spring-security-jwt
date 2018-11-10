package pl.rmitula.authapp.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Table(name = "rectangles")
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    private Double price;

    @NotBlank
    private List<TupleElement<Double, Double>> points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rectangles")
    private StatusName status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User user;
}
