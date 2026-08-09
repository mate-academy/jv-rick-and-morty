package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    private final CharacterMapper mapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Character randomCharacter = characterRepository.findRandomCharacter();
        return mapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterDto> searchByName(String name) {
        List<Character> allByNameContains = characterRepository.findAllByNameContains(name);
        List<CharacterDto> characterDtos = new ArrayList<>();
        allByNameContains.forEach((character) -> characterDtos.add(mapper.toDto(character)));
        return characterDtos;
    }
}
