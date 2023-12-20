package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.external.ResponseSeriesCharacterDataDto;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterDto;
import mate.academy.rickandmorty.mapper.SeriesCharacterMapper;
import mate.academy.rickandmorty.repository.SeriesCharacterRepository;
import mate.academy.rickandmorty.service.SeriesCharacterService;
import org.springframework.stereotype.Service;

@Service
public class SeriesCharacterServiceImpl implements SeriesCharacterService {
    private final Random random = new Random();
    private final SeriesCharacterRepository seriesCharacterRepository;
    private final SeriesCharacterMapper seriesCharacterMapper;

    public SeriesCharacterServiceImpl(SeriesCharacterRepository seriesCharacterRepository,
                                      SeriesCharacterMapper seriesCharacterMapper) {
        this.seriesCharacterRepository = seriesCharacterRepository;
        this.seriesCharacterMapper = seriesCharacterMapper;
    }

    @Override
    public void saveAll(List<ResponseSeriesCharacterDataDto> seriesCharacterDataDtos) {
        seriesCharacterRepository.saveAll(
                seriesCharacterDataDtos.stream()
                        .map(seriesCharacterMapper::toModel)
                        .toList()
        );
    }

    @Override
    public SeriesCharacterDto getRandomCharacter() {
        long randomId = random.nextLong(seriesCharacterRepository.count());
        return seriesCharacterMapper.toDto(
                seriesCharacterRepository.findById(randomId)
                        .orElseThrow(() ->
                                new RuntimeException("Can't find character by id: " + randomId)
                        )
        );
    }

    @Override
    public List<SeriesCharacterDto> findSeriesCharacterByName(String name) {
        return seriesCharacterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(seriesCharacterMapper::toDto)
                .toList();
    }
}
