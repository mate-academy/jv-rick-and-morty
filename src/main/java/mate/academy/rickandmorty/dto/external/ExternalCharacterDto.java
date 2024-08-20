package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalCharacterDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String status;
    @NotBlank
    private String gender;
}
