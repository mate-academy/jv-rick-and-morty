package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterPerson;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyInternalService {

    private static long UPPER_CHARACTERS_BOUND;
    private final RickAndMortyClientService rickAndMortyClientService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final SecureRandom random;

    public List<CharacterDto> getAllCharactersNameLike(String name) {
        name = "%" + name + "%";
        List<CharacterPerson> characters = characterRepository
                                                          .findByNameLikeIgnoreCase(name);
        return characters.stream()
            .map(characterMapper::toDto)
            .toList();
    }

    public CharacterDto getRandomCharacter() {
        Optional<CharacterPerson> charById = characterRepository
                                             .findById(random.nextLong(UPPER_CHARACTERS_BOUND));
        return characterMapper.toDto(charById.orElseThrow(
            () -> new RuntimeException("Cannot find character with random number")));

    }

    @PostConstruct
    private void saveCharacters() {
        List<CharacterPerson> result = rickAndMortyClientService.getCharacters().stream()
                                         .map(characterMapper::toModel)
                                         .toList();
        UPPER_CHARACTERS_BOUND = result.size() + 1;
        characterRepository.saveAll(result);
    }
}
