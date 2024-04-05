package mate.academy.rickandmorty.dto.internal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterRequestDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String gender;
}
