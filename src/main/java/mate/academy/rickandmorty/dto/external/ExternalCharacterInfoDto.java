package mate.academy.rickandmorty.dto.external;

public record ExternalCharacterInfoDto(
    Long id,
    String name,
    String status,
    String gender
) {
}
