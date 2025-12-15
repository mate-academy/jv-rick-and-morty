package mate.academy.rickandmorty.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {

    CharacterOfRickyAndMortyDto save(RandMResponseDto inputDto);

    List<BigDecimal> findAllExternalId();

    RandMResponseDto findByExternalId(BigDecimal externalId);

    List<Character> getAllCharactersWithName(String name);
}
