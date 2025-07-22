package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.RickAndMorty;
import mate.academy.rickandmorty.repository.character.RickAndMortyRepository;
import mate.academy.rickandmorty.repository.character.RickAndMortySpecificationBuilder;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private final RickAndMortyRepository repository;
    private final RickAndMortyMapper mapper;
    private final RickAndMortySpecificationBuilder specificationBuilder;
    private final Random random = new Random();

    @Override
    public CharacterDto getRandomCharacter() {
        RickAndMorty randomRickAndMorty = findRandomCharacter();
        return mapper.toDto(randomRickAndMorty);
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParametersDto searchParametersDto) {
        Specification<RickAndMorty> specification = specificationBuilder.build(searchParametersDto);
        return repository.findAll(specification).stream()
                .map(mapper::toDto)
                .toList();
    }

    private RickAndMorty findRandomCharacter() {
        long totalCount = repository.count();
        if (totalCount == 0) {
            throw new EntityNotFoundException("Can't find any character from"
                    + " DB because DB is empty");
        }
        int randomise = ThreadLocalRandom.current().nextInt((int) totalCount);
        Page<RickAndMorty> character = repository.findAll(PageRequest.of(randomise, 1));
        return character.getContent().get(0);
    }
}
