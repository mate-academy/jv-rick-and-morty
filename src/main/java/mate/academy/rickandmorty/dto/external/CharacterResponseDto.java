package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String gender;
}
