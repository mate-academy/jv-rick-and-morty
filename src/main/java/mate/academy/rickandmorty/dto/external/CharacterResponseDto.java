package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDto(
        Info info,
        List<ExternalCharacterDto> results
) {
    public record Info(
            int count,
            int pages,
            String next,
            String prev
    ) {
    }
}
