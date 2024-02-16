package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final RickAndMortyMapper rickAndMortyMapper;

    @Override
    public RickAndMortyCharacterDto getRandomCharacter() {
        Long randomId = new Random().nextLong(rickAndMortyRepository.count()) + 1;
        return rickAndMortyMapper.toDto(
                rickAndMortyRepository.findById(randomId)
                        .orElseThrow(() -> new RuntimeException(
                                "Can't get a random character from db")));
    }

    @Override
    public List<RickAndMortyCharacterDto> findCharactersByNameContaining(String nameSegment) {
        return rickAndMortyRepository.findAllByNameContainsIgnoreCase(nameSegment).stream()
                .map(rickAndMortyMapper::toDto)
                .toList();
    }
}
