package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        List<Character> characters = repository.findAll();
        if (characters.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No characters found");
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(characters.size());
        Character character = characters.get(randomIndex);
        return mapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findByName(String namePart) {
        return repository.findAllByNameContainingIgnoreCase(namePart.trim()).stream()
                .map(mapper::toDto)
                .toList();
    }
}
