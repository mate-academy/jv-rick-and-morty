package mate.academy.rickandmorty.dto.internal;

public record PersonageResponseDto(Long id,
                                   Long externalId,
                                   String name,
                                   String status,
                                   String gender) {
}
