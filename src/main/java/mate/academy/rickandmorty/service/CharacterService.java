package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.external.ExtCharacterRecordDto;
import mate.academy.rickandmorty.dto.internal.RnMCharacterDto;
import mate.academy.rickandmorty.dto.search.CharacterSearchParameters;
import mate.academy.rickandmorty.model.RnMCharacter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    RnMCharacterDto getRandomCharacter();

    RnMCharacter save(RnMCharacter character);

    RnMCharacter save(ExtCharacterRecordDto character);

    String initDb();

    Page<RnMCharacterDto> search(CharacterSearchParameters requestParams, Pageable pageable);
}
