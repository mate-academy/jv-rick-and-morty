package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePersonageRequestDto {
    @NotBlank(message = "externalId is mandatory")
    private String externalId;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "status is mandatory")
    private String status;
    @NotBlank(message = "gender is mandatory")
    private String gender;
}
