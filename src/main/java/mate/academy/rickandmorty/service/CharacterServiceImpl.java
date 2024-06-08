package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final Random random;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    public void loadDataFromExternalApi() {
        characterRepository.saveAll(characterClient
                .getCharacters()
                .stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList()));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long charactersAmount = characterRepository.count();
        long id = random.nextLong(charactersAmount);
        return characterRepository.findById(id)
                .map(characterMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Character not found with id: " + id));
    }

    @Override
    public List<CharacterDto> findByNamePart(String name) {
        return characterRepository.findByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
