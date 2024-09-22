package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository repository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = repository.count();
        Random random = new Random();
        long index = random.nextLong(count);
        return characterMapper.toDto(repository.getReferenceById(index));
    }

    @Override
    public List<CharacterResponseDto> getCharacterBySearchParam(String namePart,
                                                                Pageable pageable) {
        return characterMapper.toDto(repository.findByNamePart(namePart, pageable));
    }
}
