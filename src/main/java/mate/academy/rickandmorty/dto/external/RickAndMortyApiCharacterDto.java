package mate.academy.rickandmorty.dto.external;

public record RickAndMortyApiCharacterDto(
        Integer id,
        String name,
        String status,
        String gender
) {
}
