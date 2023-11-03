package mate.academy.rickandmorty.dto.external;

public record CharacterSearchParametersDto(
        String[] name,
        String[] status,
        String[] gender
) {
}
