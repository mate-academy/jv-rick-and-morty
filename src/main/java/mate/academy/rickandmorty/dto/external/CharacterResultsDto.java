package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResultsDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
