package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        if(count == 0) {
            throw new EntityNotFoundException("No character found");
        }
        long randomNumber = ThreadLocalRandom.current().nextLong(count);
        return characterRepository.findById(randomNumber)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Character not found for ID: " + randomNumber));
    }

    @Override
    public List<CharacterDto> findCharacterByName(String name) {
        List<Character> character = characterRepository.findByNameContainingIgnoreCase(name);
        return characterMapper.toDtoList(character);
    }
}
