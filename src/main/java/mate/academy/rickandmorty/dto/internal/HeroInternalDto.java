package mate.academy.rickandmorty.dto.internal;

public record HeroInternalDto(Long id,
                              Long externalId,
                              String name,
                              String status,
                              String gender) {
}
