package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import mate.academy.rickandmorty.model.external.CharacterResultDto;
import mate.academy.rickandmorty.model.internal.Character;
import mate.academy.rickandmorty.repo.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final DataLoadOnStartup dataLoadOnStartup;

    public CharacterService(CharacterRepository characterRepository,
                            DataLoadOnStartup dataLoadOnStartup) {
        this.characterRepository = characterRepository;
        this.dataLoadOnStartup = dataLoadOnStartup;
    }

    @PostConstruct
    public void saveCharactersToDB() {
        List<CharacterResultDto> characterResultDtoList = dataLoadOnStartup.getDataByApi();
        for (CharacterResultDto ch : characterResultDtoList) {
            characterRepository.save(convertDtoToCharacter(ch));
        }
    }

    public Character convertDtoToCharacter(CharacterResultDto characterResultDto) {
        Character character = new Character();
        character.setExternalId(characterResultDto.getExternalId());
        character.setName(characterResultDto.getName());
        character.setStatus(characterResultDto.getStatus());
        character.setGender(characterResultDto.getGender());
        return character;
    }

    public Character getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        Random random = new Random();
        Long randomCharacterId = random.nextLong(characters.size()) + 1;
        Optional<Character> character = characterRepository.findById(randomCharacterId);
        return character.get();
    }

    public List<Character> searchCharactersByName(String searchQuery) {
        return characterRepository.findByNameContaining(searchQuery);
    }
}
