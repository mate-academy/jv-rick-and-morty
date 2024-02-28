package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDataDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String gender;
}
