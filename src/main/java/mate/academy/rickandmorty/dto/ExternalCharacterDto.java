package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;

    @JsonProperty("id")
    public Long getExternalId() {
        return id;
    }
}
