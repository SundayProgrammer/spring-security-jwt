package pl.rmitula.authapp.model;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private StatusName statusName;

    @OneToMany(mappedBy = "status")
    private Set<Rectangle> rectangles = new HashSet<>();
}
