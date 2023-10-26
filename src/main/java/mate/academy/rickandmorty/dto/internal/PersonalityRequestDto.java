package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonalityRequestDto {
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
