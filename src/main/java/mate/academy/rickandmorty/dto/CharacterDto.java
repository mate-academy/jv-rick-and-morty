package mate.academy.rickandmorty.dto;

public record CharacterDto(String id,
                           String name,
                           String status,
                           String species,
                           String type,
                           String gender,
                           String image) {
}
