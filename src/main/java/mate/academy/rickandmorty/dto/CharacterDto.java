package mate.academy.rickandmorty.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Character DTO")
public class CharacterDto {
    @Schema(description = "Internal DB id")
    private Long id;

    @Schema(description = "Original external id (from Rick & Morty API) as string")
    private String externalId;

    private String name;
    private String status;
    private String gender;
}
