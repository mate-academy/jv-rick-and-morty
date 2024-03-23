package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharactersApiClient client;

    @Override
    public List<CharacterResponseDto> findAll(Pageable pageable) {
        return characterRepository.findAll(pageable)
                .stream()
                .map(characterMapper::toResponseDto)
                .toList();
    }

    @Override
    public CharacterResponseDto findById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find Character by id " + id));
        return characterMapper.toResponseDto(character);
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = (long) (Math.random() * count);
        return findById(randomId);
    }

    @Override
    public List<CharacterResponseDto> findAllByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toResponseDto)
                .toList();
    }

    @Override
    public void saveCharactersToDb() {
        List<Character> characters = client.getAllCharacterFromApi()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
