package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
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
        CharacterModel characterModel = characterRepository.findByRandomId();
        return characterMapper.toCharacterDto(characterModel);
    }

    @Override
    public List<CharacterDto> findByNameContaining(String name) {
        List<CharacterModel> characterModels =
                characterRepository.findByNameContainingIgnoreCase(name);
        return characterModels.stream().map(characterMapper::toCharacterDto).toList();
    }
}
