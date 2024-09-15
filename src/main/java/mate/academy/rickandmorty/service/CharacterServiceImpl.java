package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.config.mapper.CharacterMapper;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.CharacterRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterDto getRandomCharacter() {
        long totalCharacters = characterRepository.count();
        Random random = new Random();
        Long randomId = random.nextLong(totalCharacters);
        CharacterRickAndMorty character = characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Character not found"));
        return characterMapper.toDto(character);

    }

    @Override
    public List<CharacterDto> findByPartOfName(String partOfName) {
        List<CharacterRickAndMorty> listOfCharacters =
                characterRepository.findByNameContaining(partOfName);
        return listOfCharacters
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
