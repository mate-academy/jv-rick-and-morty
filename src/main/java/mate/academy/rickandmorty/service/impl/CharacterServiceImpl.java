package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final RickMortyClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void importCharacters() {

        if (characterRepository.count() > 0) {
            return;
        }

        CharacterResponseDto firstPage = client.getCharacters(1);

        int pages = firstPage.info().pages();

        for (int i = 2; i <= pages; i++) {
            CharacterResponseDto response = client.getCharacters(i);

            characterRepository.saveAll(
                    response.results().stream()
                            .map(characterMapper::toModel)
                            .toList());
        }
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Character character = characterRepository.getRandomCharacter();
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findContaining(String name) {
        return characterRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
