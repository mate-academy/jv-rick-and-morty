package mate.academy.rickandmorty.dto;

import java.util.List;

public record CartoonCharactersListResponseDto(
        CartoonCharacterInfoDto info,
        List<CreateCartoonCharacterRequestDto> results) {
}
