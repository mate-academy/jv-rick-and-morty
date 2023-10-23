package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResults;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParametersDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Long FIRST_ENTITY_ID = 1L;
    private final CharacterMapper characterMapper;
    private final CharactersClient charactersClient;
    private final CharacterRepository characterRepository;

    private void deleteAllCharacters() {
        characterRepository.deleteAll();
    }

    @PostConstruct
    public void init() {
        saveAll();
    }

    @Override
    public List<Character> saveAll() {
        List<CharacterResults> characterResults = charactersClient.downloadAllCharacters();
        List<Character> characterList = characterResults.stream()
                .map(characterMapper::fromApiToDto)
                .map(characterMapper::toModel)
                .toList();
        deleteAllCharacters();
        return characterRepository.saveAll(characterList);
    }

    @Override
    public CharacterDto findByRandomId() {
        long amountOfCharactersInDb = characterRepository.count();
        long maxIdFromCharacters = characterRepository.getMaxIdFromCharacters();
        long generatedLong = new Random().nextLong(maxIdFromCharacters - amountOfCharactersInDb,
                maxIdFromCharacters + 1);
        return characterMapper.toDto(characterRepository.findById(generatedLong)
                .orElseThrow(() -> new NoSuchElementException("Can't find a book by id: "
                        + generatedLong)));
    }

    @Override
    public List<CharacterDto> searchCharactersByName(
            CharacterSearchParametersDto searchParametersDto,
            Pageable pageable) {
        String stringForSearch = searchParametersDto.name();
        return characterRepository.getCharacterByNameContains(stringForSearch, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public List<CharacterDto> getAll(Pageable pageable) {
        return characterRepository.findAll()
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
