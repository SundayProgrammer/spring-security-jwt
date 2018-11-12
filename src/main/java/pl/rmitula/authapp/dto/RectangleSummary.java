package pl.rmitula.authapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.rmitula.authapp.model.RectanglePointList;
import pl.rmitula.authapp.model.StatusName;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RectangleSummary {
    private Long id;
    private String name;
    private String address;
    private String description;
    private Double price;
    private StatusName status;
    private List<RectanglePointList> points;
    private Long userId;
}
