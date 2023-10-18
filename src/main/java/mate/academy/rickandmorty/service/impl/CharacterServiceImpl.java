package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;


    @Override
    public CharacterResponseDto getRandom() {
        return characterRepository.findById(1L)
                .map(characterMapper::toDto)
                .orElseThrow();
    }

    @Override
    public List<CharacterResponseDto> search(String name) {
        return characterRepository.findCharacterByNameLike(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
