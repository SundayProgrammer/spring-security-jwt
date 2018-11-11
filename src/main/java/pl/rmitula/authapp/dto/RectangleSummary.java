package pl.rmitula.authapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.rmitula.authapp.model.StatusName;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RectangleSummary {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private StatusName status;
    private Long userId;
}
