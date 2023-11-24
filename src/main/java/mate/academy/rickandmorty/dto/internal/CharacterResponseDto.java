package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CharacterResponseDto {
    @Schema(description = "Character Id", example = "159")
    private Long id;
    @Schema(description = "Character external Id", example = "159")
    @JsonProperty("external_id")
    private Long externalId;
    @Schema(description = "Character name", example = "Hunter")
    private String name;
    @Schema(description = "Character Id", example = "Dead")
    private String status;
    @Schema(description = "Character Id", example = "Male")
    private String gender;
}
