package mate.academy.rickandmorty.dto;

import mate.academy.rickandmorty.model.Character;

public record CharacterFromApi(Long id,
                               String name,
                               String status,
                               Character.Gender gender) {
}
