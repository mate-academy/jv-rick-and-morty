package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final RickAndMortyMapper rickAndMortyMapper;

    @Override
    public RickAndMortyCharacterDto getRandomCharacter() {
        long allCharactersInDb = rickAndMortyRepository.count();
        Long randomId = new Random().nextLong(allCharactersInDb) + 1;
        return rickAndMortyMapper.toDto(
                rickAndMortyRepository.findById(randomId)
                        .orElseThrow(RuntimeException::new));
    }

    @Override
    public List<RickAndMortyCharacterDto> findCharactersByNameContaining(String nameSegment) {
        return  rickAndMortyRepository.findAllByNameContainsIgnoreCase(nameSegment).stream()
                .map(rickAndMortyMapper::toDto).toList();
    }
}
