package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class ExternalCharacterApiResponseDto {
    private CharacterResponseDataDto[] results;
}