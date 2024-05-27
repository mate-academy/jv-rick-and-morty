package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @Override
    public List<CharacterDto> searchCharacters(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return mapper.toDto(repository.getRandom());
    }

    @Override
    public CharacterDto save(CreateCharacterRequestDto requestDto) {
        CartoonCharacter character = mapper.toModel(requestDto);
        return mapper.toDto(repository.save(character));
    }
}
