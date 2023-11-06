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
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient dataLoadOnStartup;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                RickAndMortyClient dataLoadOnStartup) {
        this.characterRepository = characterRepository;
        this.dataLoadOnStartup = dataLoadOnStartup;
    }

    @PostConstruct
    @Override
    public void saveCharactersToDB() {
        List<CharacterResultDto> characterResultDtoList = dataLoadOnStartup.getCharacters();
        for (CharacterResultDto ch : characterResultDtoList) {
            characterRepository.save(convertDtoToCharacter(ch));
        }
    }

    @Override
    public Character convertDtoToCharacter(CharacterResultDto characterResultDto) {
        Character character = new Character();
        character.setExternalId(characterResultDto.getExternalId());
        character.setName(characterResultDto.getName());
        character.setStatus(characterResultDto.getStatus());
        character.setGender(characterResultDto.getGender());
        return character;
    }

    @Override
    public Character getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        Random random = new Random();
        Long randomCharacterId = random.nextLong(characters.size()) + 1;
        Optional<Character> character = characterRepository.findById(randomCharacterId);
        return character.get();
    }

    @Override
    public List<Character> searchCharactersByName(String searchQuery) {
        return characterRepository.findByNameContaining(searchQuery);
    }
}
