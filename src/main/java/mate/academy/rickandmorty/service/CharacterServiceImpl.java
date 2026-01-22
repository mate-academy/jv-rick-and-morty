package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient rickAndMortyClient;

    @Override
    public void saveCharacters(List<CharacterResultsDto> characterResultsDto) {
        List<Character> characterModel = characterMapper.toCharacterModels(characterResultsDto);
        characterRepository.saveAll(characterModel);
    }

    @Override
    public List<Character> getCharacterByName(String name) {
        return characterRepository.findByNameContains(name);
    }

    @Override
    public void readAndSaveCharacters() {
        CharacterResponseDataDto characterResponseDataDto =
                rickAndMortyClient.getCharacter(BASE_URL);
        while (characterResponseDataDto != null) {
            saveCharacters(characterResponseDataDto.getResults());
            if (characterResponseDataDto.getInfo().getNext() == null) {
                break;
            }
            characterResponseDataDto =
                    rickAndMortyClient.getCharacter(characterResponseDataDto.getInfo().getNext());
        }
    }

    @Override
    public Character getRandomCharacter() {
        long id = ThreadLocalRandom.current().nextLong(1, characterRepository.count() + 1);
        return characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant found random character"));
    }
}
