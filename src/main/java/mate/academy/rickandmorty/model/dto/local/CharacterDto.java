package mate.academy.rickandmorty.model.dto.local;

public record CharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String species,
        String type,
        String gender) {
}
