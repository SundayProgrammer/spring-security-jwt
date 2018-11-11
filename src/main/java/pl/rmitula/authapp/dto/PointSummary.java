package pl.rmitula.authapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PointSummary {

    private Double x;
    private Double y;
}
