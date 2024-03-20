package mate.academy.rickandmorty.dto.internal;

import lombok.Data;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
