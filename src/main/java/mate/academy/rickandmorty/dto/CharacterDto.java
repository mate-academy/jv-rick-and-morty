package mate.academy.rickandmorty.dto;

public record CharacterDto(long id, long externalId,
                           String name, String status, String gender) {
}
