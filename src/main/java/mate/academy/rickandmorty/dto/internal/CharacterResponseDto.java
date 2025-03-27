package mate.academy.rickandmorty.dto.internal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CharacterResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String externalId;
    @NotBlank
    private String name;
    @NotBlank
    private String status;
    @NotBlank
    private String gender;
}
