package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final Random random = new Random();
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public CharacterRequestDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        return characterMapper.toDto(characters.get(random.nextInt(characters.size())));
    }

    @Override
    public List<CharacterRequestDto> getByNameContaining(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @PostConstruct
    public void saveCharactersFromApi() {
        List<CharacterResponseDto> characterResponseDtos = rickAndMortyClient.fetchCharacters();
        characterRepository.saveAll(characterResponseDtos.stream()
                .map(characterMapper::toModel)
                .toList());
    }
}
