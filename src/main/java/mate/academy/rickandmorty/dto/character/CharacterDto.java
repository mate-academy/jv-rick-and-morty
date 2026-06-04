package mate.academy.rickandmorty.dto.character;

import lombok.Data;
import mate.academy.rickandmorty.model.type.GenderType;
import mate.academy.rickandmorty.model.type.StatusType;

@Data
public class CharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private StatusType status;
    private GenderType gender;
}
