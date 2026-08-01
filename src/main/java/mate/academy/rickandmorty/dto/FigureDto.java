package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FigureDto {
    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private String externalId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("gender")
    private String gender;
}
