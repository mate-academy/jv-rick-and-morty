package mate.academy.rickandmorty.dto;

import mate.academy.rickandmorty.model.Character;

public record CharacterDto(Long id, String externalId, String name, String status, Character.Gender gender) {
}
