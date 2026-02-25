package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDto {
    private Long id;

    @NotNull
    private String externalId;

    @NotNull
    private String name;

    @NotNull
    private String status;

    @NotNull
    private String gender;
}
