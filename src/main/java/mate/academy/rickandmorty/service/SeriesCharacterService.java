package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterWikiDto;

public interface SeriesCharacterService {

    SeriesCharacterWikiDto getRandomSeriesCharacter();

    List<SeriesCharacterDto> saveAll(List<SeriesCharacterDto> seriesCharacterDtoList);

    List<SeriesCharacterWikiDto> findNameContains(String name);
}
