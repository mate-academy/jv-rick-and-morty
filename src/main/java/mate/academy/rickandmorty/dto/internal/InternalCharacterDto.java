package mate.academy.rickandmorty.dto.internal;

import lombok.Data;
import mate.academy.rickandmorty.model.Character;

@Data
public class InternalCharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private Character.Status status;
    private Character.Gender gender;
}
