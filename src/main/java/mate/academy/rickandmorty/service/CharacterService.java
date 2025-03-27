package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;

public interface CharacterService {

    public CharacterResponseDto getRandomWiki();

    public List<CharacterResponseDto> getCharactersWithNameLike(String name);
}
