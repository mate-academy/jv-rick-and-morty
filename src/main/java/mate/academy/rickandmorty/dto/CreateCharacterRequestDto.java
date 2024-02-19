package mate.academy.rickandmorty.dto;

public record CreateCharacterRequestDto(
        Long externalId,
        String name,
        String status,
        String gender) {
}
