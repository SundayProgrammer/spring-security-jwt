package pl.rmitula.authapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "rectangles")
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotBlank
    @Size(max = 200)
    private String description;

    @Setter
    private Double price;

    @Setter
    @OneToMany(mappedBy = "rectangle")
    private List<Points> points;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rectangles")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User user;

    public Rectangle(@NotBlank @Size(max = 100) String name,
                     @NotBlank @Size(max = 100) String address,
                     @NotBlank @Size(max = 200) String description,
                     User user) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.user = user;
    }
}
