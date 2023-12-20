package mate.academy.rickandmorty.dto.character.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
