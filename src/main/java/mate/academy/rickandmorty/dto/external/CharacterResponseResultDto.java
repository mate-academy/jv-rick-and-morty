package mate.academy.rickandmorty.dto.external;

public record CharacterResponseResultDto(
        Long id,
        String name,
        String status,
        String gender
) {}
