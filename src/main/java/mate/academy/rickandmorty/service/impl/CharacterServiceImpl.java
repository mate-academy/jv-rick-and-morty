package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAll(List<ExternalCharacterDto> characterDtos) {
        List<Character> characters = characterDtos.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public List<CharacterResponseDto> search(Pageable pageable, String name) {
        return characterRepository
                .findAllByNameContainsIgnoreCase(pageable, name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
