package mate.academy.rickandmorty.dto.external;

import mate.academy.rickandmorty.model.Character;

public record CharacterDataResponseDto(
        Long id,
        String name,
        String status,
        Character.Gender gender) {
}
