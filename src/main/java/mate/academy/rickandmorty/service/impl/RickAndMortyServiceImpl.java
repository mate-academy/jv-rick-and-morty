package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final RickAndMortyMapper rickAndMortyMapper;

    @Override
    public void save(ExternalCharacterDto responseDto) {
        Character rickAndMortyModel = rickAndMortyMapper.toModel(responseDto);
        Character saved = rickAndMortyRepository.save(rickAndMortyModel);
        rickAndMortyMapper.toDto(saved);
    }

    @Override
    public CharacterDto getById(Long id) {
        Optional<Character> rickAndMortyRepositoryById
                = rickAndMortyRepository.findById(id);
        return rickAndMortyMapper.toDto(rickAndMortyRepositoryById.orElseThrow(()
                -> new EntityNotFoundException("Can not find "
                + "character by this ID: "
        + id)));
    }

    @Override
    public List<CharacterDto> getByName(String name) {
        return rickAndMortyRepository.findByNameContainsIgnoreCase(name).stream()
                .map(rickAndMortyMapper::toDto)
                .toList();
    }

    @Override
    public long getNumberAllCharacters() {
        return rickAndMortyRepository.findAll().size();
    }
}
