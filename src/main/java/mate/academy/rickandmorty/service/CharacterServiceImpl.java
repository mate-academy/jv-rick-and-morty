package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final RickAndMortyClient rickAndMortyClient;
    @Value("${rick-and-morty.get.characters.url}")
    private String getCharactersUrl;

    @PostConstruct
    public void init() {
        String url = getCharactersUrl;
        CharacterDataResponseDto data;
        do {
            data = rickAndMortyClient.getAllCharacter(url);
            saveAll(data.results());
            url = data.info().next();
        } while (url != null);
    }

    @Override
    public void saveAll(List<CharacterResponseDto> responseDtos) {
        characterRepository.saveAll(characterMapper.toModels(responseDtos));
    }

    @Override
    public CharacterDto findById(Long id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        return characterMapper.toDto(optionalCharacter.orElseThrow(
                () -> new EntityNotFoundException("Couldn't find character by id " + id)
        ));
    }

    @Override
    @Cacheable(value = "count")
    public Long count() {
        return characterRepository.count();
    }

    @Override
    public List<CharacterDto> getAllByName(String string) {
        return characterMapper.toDtos(characterRepository.findAllByNameContainsIgnoreCase(string));
    }
}
