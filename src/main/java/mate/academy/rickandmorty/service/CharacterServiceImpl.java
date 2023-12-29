package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final RickAndMortyApiClient apiClient;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getCharacterById(Long id) {
        Character character = repository
                .findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException("Can't find character with id " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> getPatternNameCharacters(String pattern) {
        return repository
                .getAllByNameContaining(pattern)
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
}
