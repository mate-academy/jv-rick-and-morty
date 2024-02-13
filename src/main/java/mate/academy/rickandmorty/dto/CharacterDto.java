package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CharacterDto extends CharacterDtoWithoutExternalId {
    private Long externalId;
}
