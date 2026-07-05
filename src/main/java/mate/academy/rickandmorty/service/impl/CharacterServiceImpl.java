package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> saveAll(CharacterResponseDataDto responseDataDto) {
        List<Character> characters = responseDataDto.getResults()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        return characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(
                characterRepository.findRandomCharacter()
        );
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
