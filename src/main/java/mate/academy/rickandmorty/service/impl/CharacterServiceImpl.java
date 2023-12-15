package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;
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
    public ResponseCharacterDto getRandom() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public List<ResponseCharacterDto> searchByTemplate(String template) {
        return characterRepository.getAllByNameContainsIgnoreCase(template).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
