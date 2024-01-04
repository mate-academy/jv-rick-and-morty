package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final RickAndMortyApiClient apiClient;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterDto getCharacterById(Long id) {
        return repository
                .findById(id)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new
                        EntityNotFoundException("Can't find character with id " + id));
    }

    @Override
    public List<CharacterDto> getCharactersByName(String namePattern) {
        return repository
                .getAllByNameContainsIgnoreCase(namePattern)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void loadAllCharacters() {
        Long pages = apiClient.getCharacterInfo(0L).getInfo().getPages();
        for (int i = 0; i < pages; i++) {
            RickAndMortyApiResponseDto characterInfo = apiClient.getCharacterInfo((long) i);
            for (CharacterResponseDto characterResponseDto: characterInfo.getResults()) {
                Character character = characterMapper.toModel(characterResponseDto);
                repository.save(character);
            }
        }
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomId = random.nextLong(repository.count());
        return repository
                .findById(randomId)
                .map(characterMapper::toDto)
                .orElseThrow(() ->
                        new RuntimeException("Can't find character with id " + randomId));
    }
}
