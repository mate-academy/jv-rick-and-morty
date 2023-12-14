package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterInfoDto;

import java.util.List;

public interface CharacterService {
     List<CharacterInfoDto> getRandomCharacter();

     CharacterInfoDto getByName(String name);
}
