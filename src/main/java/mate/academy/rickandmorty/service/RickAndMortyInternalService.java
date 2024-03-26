package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterPerson;
import mate.academy.rickandmorty.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyInternalService {

    private final RickAndMortyClientService rickAndMortyClientService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    private void saveCharacters() {
        List<CharacterRequestDto> characters = rickAndMortyClientService.getCharacters();
        List<CharacterPerson> result = characters.stream()
                                           .map(characterMapper::toModel)
                                           .toList();
        characterRepository.saveAll(result);

    }

    public List<CharacterDto> getAllCharactersLike(String name) {
        System.out.println(name);
        List<CharacterPerson> characters = characterRepository
                                                          .findCharacterPersonByNameLikeIgnoreCase(
                                                              name);
        return characters.stream()
            .map(characterMapper::toDto)
            .toList();
    }

    public CharacterDto getRandomCharacter() {
        Optional<CharacterPerson> byId = characterRepository
                                             .findById(new Random().nextLong(826));
        return characterMapper.toDto(byId.orElseThrow(RuntimeException::new));

    }

}
