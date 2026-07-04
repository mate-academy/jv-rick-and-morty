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
    public void syncExternalCharacters() {
        List<CharacterDto> allCharacters = characterClient.getJsonResponse();
        List<CharacterModel> models = allCharacters.stream()
                .map(characterMapper::toModel)
                .toList();
        repository.saveAll(models);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return repository.findRandom().map(characterMapper::toDto).orElseThrow(
                () -> new RuntimeException("Base is empty"));
    }

    @Override
    public List<CharacterDto> searchByString(String string) {
        return repository.findCharacterModelByNameContainingIgnoreCase(string).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
