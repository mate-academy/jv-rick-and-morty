package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponseDto {
    @NotBlank
    private CharacterMetadataDto info;
    @NotBlank
    private List<ExternalCharacterDto> results;
}
