package mate.academy.rickandmorty.dto.external;

public record CharacterResponseDto(long id,
                                   String name,
                                   String status,
                                   String gender
) {
}
