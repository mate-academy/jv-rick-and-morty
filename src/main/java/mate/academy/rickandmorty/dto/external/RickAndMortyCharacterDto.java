package mate.academy.rickandmorty.dto.external;

public record RickAndMortyCharacterDto(
        Long id,
        String name,
        String status,
        String gender
) {
}
