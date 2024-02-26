package mate.academy.rickandmorty.dto.internal;

import mate.academy.rickandmorty.model.Character;

public record CharacterResponseDto(
        Long id,
        Long externalId,
        String name,
        String status,
        Character.Gender gender) {

}
