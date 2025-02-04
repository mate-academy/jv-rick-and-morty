package mate.academy.rickandmorty.service.internal;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public CharacterDto save(CreateCharacterRequestDto characterRequestDto) {
        if (characterRepository.existsByExternalId(characterRequestDto.getExternalId())) {
            System.out.println("Character with externalId "
                    + characterRequestDto.getExternalId() + " already exists.");
            return null;
        } else {
            Character character = characterMapper.toModel(characterRequestDto);
            return characterMapper.toDto(characterRepository.save(character));
        }
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        int randomIndex = new Random().nextInt(characters.size());
        return characterMapper.toDto(characters.get(randomIndex));
    }

    @Override
    public List<CharacterDto> searchCharactersByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(character -> characterMapper.toDto(character))
                .toList();
    }
}
