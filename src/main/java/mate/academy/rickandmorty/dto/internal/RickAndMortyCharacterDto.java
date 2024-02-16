package mate.academy.rickandmorty.dto.internal;

public record RickAndMortyCharacterDto(
        Long id,
        String externalId,
        String name,
        String status,
        String type,
        String gender,
        String image) {
}
