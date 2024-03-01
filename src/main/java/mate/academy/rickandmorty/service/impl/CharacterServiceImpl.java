package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.request.CharacterRequestDto;
import mate.academy.rickandmorty.dto.response.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.util.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final RickAndMortyClient rickAndMortyClient;

    @Override
    public List<CharacterResponseDto> getCharactersByNameContaining(String name) {
        return characterRepository.findByNameContaining(name)
                                    .stream()
                                    .map(characterMapper::toDto)
                                    .toList();
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        return characterMapper.toDto(characters.get(new Random().nextInt(characters.size())));
    }

    @PostConstruct
    public void saveCharactersFromApi() {
        List<CharacterRequestDto> characterDtos = rickAndMortyClient.fetchCharacters();
        List<Character> characters = characterDtos.stream().map(characterMapper::toModel).toList();
        characterRepository.saveAll(characters);
    }
}
