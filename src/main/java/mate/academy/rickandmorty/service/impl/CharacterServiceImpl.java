package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient client;
    private final Random random = new Random();

    @Override
    public CharacterInternalDto getRandomCharacter() {
        long maxId = client.getCharacterInfo().getCount();
        Long id = random.nextLong(maxId) + 1;
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id)
        );
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterInternalDto> findByName(String name) {
        return characterRepository.findCharacterByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto).toList();
    }
}
