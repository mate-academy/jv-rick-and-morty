package mate.academy.rickandmorty.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Character response DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseCharacterDto {

    @Schema(description = "Database ID", example = "1")
    private Long id;

    @Schema(description = "External API ID", example = "25")
    private Long externalId;

    @Schema(description = "Name of character", example = "Rick Sanchez")
    private String name;

    @Schema(description = "Character status", example = "Alive")
    private String status;

    @Schema(description = "Character gender", example = "Male")
    private String gender;
}

