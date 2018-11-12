package pl.rmitula.authapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.rmitula.authapp.model.Points;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonSerialize(using = RectangleSummarySerialize.class)
public class RectangleSummary {
    private Long id;
    private String name;
    private String address;
    private String description;
    private Double price;
    private List<Points> points;
    private Long userId;

    public RectangleSummary(Long id,
                            String name,
                            String address,
                            String description,
                            List<Points> points,
                            Long userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.points = points;
        this.userId = userId;
    }
}
