package mate.academy.rickandmorty.dto.external;

public record CharacterResponseDto(long externalId,
                                   String name,
                                   String status,
                                   String gender) {
}
