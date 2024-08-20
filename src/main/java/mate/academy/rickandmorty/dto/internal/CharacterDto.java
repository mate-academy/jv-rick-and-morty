package mate.academy.rickandmorty.dto.internal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CharacterDto {
    @NotNull
    private Long id;
    @NotNull
    private Long externalId;
    @NotBlank
    private String name;
    @NotBlank
    private String status;
    @NotBlank
    private String gender;
}
