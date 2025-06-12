package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.RickyCharacterDto;
import mate.academy.rickandmorty.model.RickyCharacter;

public interface RickyCharacterService {
    RickyCharacterDto save(RickyCharacter rickyCharacter);

}
