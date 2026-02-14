package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterExternalDto;
import mate.academy.rickandmorty.dto.CharacterOutputDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void saveCharacters(List<CharacterExternalDto> characters) {
        List<RickAndMortyCharacter> characterList = new ArrayList<>();

        for (CharacterExternalDto dto : characters) {
            RickAndMortyCharacter saveCharacter = new RickAndMortyCharacter();
            saveCharacter.setExternalId(dto.getId());
            saveCharacter.setName(dto.getName());
            saveCharacter.setStatus(dto.getStatus());
            saveCharacter.setGender(dto.getGender());
            characterList.add(saveCharacter);
        }
        characterRepository.saveAll(characterList);
    }

    public CharacterOutputDto getRandomCharacter() {
        long totalCharacters = characterRepository.count();

        if (totalCharacters == 0) {
            throw new RuntimeException("No characters found in database");
        }

        Random random = new Random();
        int index = random.nextInt(0, (int) totalCharacters);

        PageRequest pageRequest = PageRequest.of(index, 1);
        Page<RickAndMortyCharacter> characterPage = characterRepository
                .findAll(pageRequest);

        RickAndMortyCharacter character = characterPage.getContent().get(0);

        CharacterOutputDto characterOutputDto = new CharacterOutputDto();
        characterOutputDto.setId(character.getId());
        characterOutputDto.setExternalId(character.getExternalId());
        characterOutputDto.setName(character.getName());
        characterOutputDto.setStatus(character.getStatus());
        characterOutputDto.setGender(character.getGender());

        return characterOutputDto;
    }

    public List<CharacterOutputDto> getCharacterByName(String name) {
        List<RickAndMortyCharacter> characterList = characterRepository
                .findByNameContainingIgnoreCase(name);

        List<CharacterOutputDto> outputDtoList = new ArrayList<>();

        for (RickAndMortyCharacter character : characterList) {
            CharacterOutputDto characterByName = new CharacterOutputDto();
            characterByName.setId(character.getId());
            characterByName.setExternalId(character.getExternalId());
            characterByName.setName(character.getName());
            characterByName.setStatus(character.getStatus());
            characterByName.setGender(character.getGender());
            outputDtoList.add(characterByName);
        }
        return outputDtoList;
    }
}
