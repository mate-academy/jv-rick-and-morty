package mate.academy.rickandmorty.dto.internal;

public record CharacterResponseDto(
        Long id,
        int externalId,
        String name,
        String status,
        String gender
){
}
