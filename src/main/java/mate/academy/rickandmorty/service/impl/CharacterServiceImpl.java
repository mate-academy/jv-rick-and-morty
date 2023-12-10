package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterClient.getAllCharacters().size();
        Long id = random.nextLong(count);
        return characterRepository.findById(id)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find a character by id " + id
                ));
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name, Pageable pageable) {
        return characterRepository.findAllByName(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
