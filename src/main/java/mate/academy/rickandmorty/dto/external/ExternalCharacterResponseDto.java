package mate.academy.rickandmorty.dto.external;

public record ExternalCharacterResponseDto(
        Long id,
        String name,
        String status,
        String gender
) {}
