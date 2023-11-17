package mate.academy.rickandmorty.dto;

import java.util.List;

public record ExternalResponseDto(
        List<ExternalCharacterDto> results){}
