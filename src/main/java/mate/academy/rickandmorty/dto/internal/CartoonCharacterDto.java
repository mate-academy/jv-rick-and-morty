package mate.academy.rickandmorty.dto.internal;

public record CartoonCharacterDto(
        Long id,
        String externalId,
        String name,
        String status,
        String type,
        String gender,
        String image
) {}
