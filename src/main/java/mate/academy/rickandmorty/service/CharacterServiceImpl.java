package mate.academy.rickandmorty.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient characterClient;
    private final CharMapper characterMapper;
    private final CharacterRepository repository;

    @Override
    @Transactional
    public CharacterDto postRandomCharacter() {
        CharacterDto characterDto = characterClient.getJsonResponse();
        CharacterModel characterModel = characterMapper.toModel(characterDto);
        CharacterModel savedCharacter = repository.save(characterModel);
        return characterMapper.toDto(savedCharacter);
    }

    @Override
    public List<CharacterDto> searchByString(String string) {
        return repository.findCharacterModelByNameContainingIgnoreCase(string).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
