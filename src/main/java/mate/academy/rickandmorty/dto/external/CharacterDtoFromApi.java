package mate.academy.rickandmorty.dto.external;

import lombok.Data;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;

@Data
public class CharacterDtoFromApi {
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
