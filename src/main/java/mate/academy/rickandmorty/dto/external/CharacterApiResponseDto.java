package mate.academy.rickandmorty.dto.external;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CharacterApiResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String status;
    @NotBlank
    private String gender;
}
