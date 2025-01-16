package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterConversionDto;
import mate.academy.rickandmorty.exeption.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository repository;

    @Override
    public CharacterConversionDto getRandomCharacter() {
        List<Long> ids = repository.findAllIds();
        if (ids.isEmpty()) {
            throw new EntityNotFoundException("No characters found");
        }
        Long randomId = ids.get(new Random().nextInt(ids.size()));
        CharacterModel model = repository.findById(randomId)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        return characterMapper.toCharacterConversionDto(model);
    }

    @Override
    public List<CharacterConversionDto> searchCharacters(String searchParameter) {
        if (searchParameter.isBlank()) {
            throw new EntityNotFoundException(
                    "Search parameter cannot be blank or empty. "
                            + "Please provide a valid search query."
            );
        }
        List<CharacterModel> characters =
                repository.findByNameContainingIgnoreCase(searchParameter);
        if (characters.isEmpty()) {
            throw new EntityNotFoundException("No characters found");
        }
        return characterMapper.toCharacterDtos(characters);
    }
}
