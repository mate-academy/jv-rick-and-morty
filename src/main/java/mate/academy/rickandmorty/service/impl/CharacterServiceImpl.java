package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public List<CharacterDto> getCharacterByName(String name, Pageable pageable) {
        return characterRepository.findCharacterByNameContaining(name, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
