package mate.academy.rickandmorty.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiCharacterRequestDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
