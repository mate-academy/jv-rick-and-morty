package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record GetCharactersResponseDto(
        CharacterMetadataDto info,
        List<CharacterDto> results
) {}
