package mate.academy.rickandmorty.dto;

import java.util.List;

public record RickMortyResponse(InfoDto info, List<CharacterDto> results) {
}

