package mate.academy.rickandmorty.dto.external;

public record ResponseSeriesCharacterDataDto(
        Long id,
        String name,
        String status,
        String gender
) {}
