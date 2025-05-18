package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RickCharacterDto;
import mate.academy.rickandmorty.model.RickCharacter;
import mate.academy.rickandmorty.repository.RickCharacterRepository;
import mate.academy.rickandmorty.service.RickCharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickCharacterServiceImpl implements RickCharacterService {
    private final RickCharacterRepository repository;

    @Override
    public RickCharacterDto getRandomCharacter() {
        RickCharacter character = repository.findRandomCharacter();
        return convertToDto(character);
    }

    @Override
    public List<RickCharacterDto> searchCharacters(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private RickCharacterDto convertToDto(RickCharacter character) {
        return new RickCharacterDto(
                character.getId(),
                character.getExternalId(),
                character.getName(),
                character.getStatus(),
                character.getGender()
        );
    }
}
