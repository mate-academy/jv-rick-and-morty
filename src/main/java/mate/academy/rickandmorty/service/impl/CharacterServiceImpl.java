package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    
    @Override
    public CharacterDto getRandomCharacter() {
        return characterRepository.findRandomCharacter()
                .map(characterMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can't get random character"));
    }
    
    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
