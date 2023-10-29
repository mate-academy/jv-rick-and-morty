package mate.academy.rickandmorty.dto.external;

public record ExternalCharResponseDto(Long id,
                                      Long externalId,
                                      String name,
                                      String status,
                                      String gender) {
}
