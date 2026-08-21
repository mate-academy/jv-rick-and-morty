package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();

        int randomIndex = (int) (Math.random() * characters.size());

        Character character = characters.get(randomIndex);

        return mapToDto(character);
    }

    @Override
    public List<CharacterResponseDto> searchByName(String name) {
        return characterRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private CharacterResponseDto mapToDto(Character character) {
        return new CharacterResponseDto(
                character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender()
        );
    }
}
