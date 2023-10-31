package mate.academy.rickandmorty.dto;

public record ResponseCharacterDto(
        long id,
        String externalId,
        String name,
        String status,
        String gender
) {
}
