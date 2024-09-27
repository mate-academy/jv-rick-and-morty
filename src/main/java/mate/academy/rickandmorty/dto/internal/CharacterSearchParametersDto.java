package mate.academy.rickandmorty.dto.internal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterSearchParametersDto {
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
