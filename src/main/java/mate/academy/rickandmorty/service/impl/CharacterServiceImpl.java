package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CreateCharacterRequestDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    public CharacterDto getRandomCharacter() {
        return mapper.toDto(repository.getRandomCharacter());
    }

    @Override
    public List<CharacterDto> getAllByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto save(CreateCharacterRequestDto requestDto) {
        CharacterModel characterModel = mapper.toModel(requestDto);
        CharacterModel savedCharacterModel = repository.save(characterModel);
        return mapper.toDto(savedCharacterModel);
    }

}
