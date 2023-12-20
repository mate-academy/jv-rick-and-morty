package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ResponseSeriesCharacterDataDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterDto;

public interface SeriesCharacterService {
    void saveAll(List<ResponseSeriesCharacterDataDto> seriesCharacterDataDtos);

    SeriesCharacterDto getRandomCharacter();

    List<SeriesCharacterDto> findSeriesCharacterByName(String name);
}
