package mate.academy.rickandmorty.dto;

public record CartoonCharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender) {
}
