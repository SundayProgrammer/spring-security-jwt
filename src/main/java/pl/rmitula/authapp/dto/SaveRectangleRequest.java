package pl.rmitula.authapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@JsonDeserialize(using = SaveRequestDeserialize.class)
public class SaveRectangleRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 100)
    private String address;

    @NotBlank
    @Size(max = 200)
    private String description;

    private Double price;

    private List<PointSummary> points;
}
