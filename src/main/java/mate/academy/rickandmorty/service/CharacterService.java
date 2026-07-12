package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.ResourceNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterDto getRandom() {
        return characterRepository.getRandomCharacter()
                .map(characterMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No characters found in database"));
    }

    public List<CharacterDto> searchByName(String name) {
        return characterRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
