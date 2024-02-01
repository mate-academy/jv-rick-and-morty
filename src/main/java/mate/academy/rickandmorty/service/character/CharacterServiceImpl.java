package mate.academy.rickandmorty.service.character;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.mapper.character.CharacterMapper;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
