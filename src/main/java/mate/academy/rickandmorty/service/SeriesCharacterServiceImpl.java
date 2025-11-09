package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterWikiDto;
import mate.academy.rickandmorty.mapper.external.SeriesCharacterMapper;
import mate.academy.rickandmorty.mapper.internal.SeriesCharacterWikiMapper;
import mate.academy.rickandmorty.model.SeriesCharacter;
import mate.academy.rickandmorty.repository.SeriesCharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeriesCharacterServiceImpl implements SeriesCharacterService {
    private static final Long INDEX_CORRECTION = 1L;
    private final SeriesCharacterRepository characterRepository;
    private final SeriesCharacterMapper characterMapper;
    private final SeriesCharacterWikiMapper characterWikiMapper;

    @Override
    public SeriesCharacterWikiDto getRandomSeriesCharacter() {
        Random random = new Random();
        long randomIndex = random.nextInt((int) characterRepository.count()) + INDEX_CORRECTION;
        SeriesCharacter seriesCharacter = characterRepository.findById(randomIndex)
                .orElseThrow(() -> new RuntimeException("Series character not found"));
        return characterWikiMapper.toCharacterWikiDto(seriesCharacter);
    }

    @Override
    public List<SeriesCharacterDto> saveAll(List<SeriesCharacterDto> characterDtoList) {
        List<SeriesCharacter> characterList = characterDtoList.stream()
                .map(characterMapper::toSeriesCharacter)
                .collect(Collectors.toList());

        List<SeriesCharacter> savedCharacterList = characterRepository.saveAll(characterList);

        return savedCharacterList.stream()
                .map(characterMapper::toSeriesCharacterDto)
                .toList();
    }

    @Override
    public List<SeriesCharacterWikiDto> findNameContains(String name) {
        return characterRepository.findByNameContainsIgnoreCase(name).stream()
                .map(characterWikiMapper::toCharacterWikiDto)
                .toList();
    }
}
