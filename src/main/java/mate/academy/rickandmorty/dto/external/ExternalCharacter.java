package mate.academy.rickandmorty.dto.external;

public record ExternalCharacter(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {
}
