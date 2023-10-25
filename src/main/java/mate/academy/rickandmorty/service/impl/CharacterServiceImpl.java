package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.dto.internal.InternalCharListDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final RickAndMortyClient client;
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    @PostConstruct
    public void save() {
        List<Character> characters = new ArrayList<>();
        int pageAmount = 42;
        if (repository.findAll().isEmpty()) {
            for (int i = 1; i < pageAmount + 1; i++) {
                InternalCharListDto dtoList = client.getAllCharacters(i);
                if (repository.findAll().isEmpty()) {
                    characters.addAll(
                            dtoList.getResults().stream()
                                    .map(mapper::toEntity)
                                    .toList()
                    );
                }
            }
            repository.saveAll(characters);
        }
    }

    @Override
    public List<Character> getCharactersBySearchString(String searchString) {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll()
                    .stream()
                    .filter(character -> character.getName().toLowerCase()
                            .contains(searchString.toLowerCase()))
                    .toList();
        }
        throw new RuntimeException("Can't get all characters with this search string: "
                + searchString);
    }

    @Override
    public ExternalCharResponseDto getRandomCharacter() {
        Random random = new Random();
        int maxCharAmount = 826;
        return mapper.toDto(
                repository.findById(random.nextLong(maxCharAmount))
                        .orElseThrow(RuntimeException::new)
        );
    }
}
