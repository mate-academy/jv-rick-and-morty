package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.mapper.RickAndMortyCharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyCharacterRepository;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private final RickAndMortyCharacterRepository rickAndMortyCharacterRepository;
    private final RickAndMortyCharacterMapper rickAndMortyCharacterMapper;

    @Override
    public RickAndMortyCharacterDto getRandomCharacter() {
        Long randomId = new Random().nextLong(1,rickAndMortyCharacterRepository.count());
        return rickAndMortyCharacterMapper
                .toDto(rickAndMortyCharacterRepository
                        .getReferenceById(randomId));

    }

    @Override
    public List<RickAndMortyCharacterDto> getCharacterByName(String name, Pageable pageable) {

        return rickAndMortyCharacterRepository
                .findRickAndMortyCharactersByNameContains(name, pageable)
                .get().map(rickAndMortyCharacterMapper::toDto).toList();
    }
}
