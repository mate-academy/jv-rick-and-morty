package mate.academy.rickandmorty.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterRM;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public List<CharacterDto> searchByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    public CharacterDto getRandomCharacter() {
        List<CharacterRM> characters = characterRepository.findAll();

        if (characters.isEmpty()) {
            throw new EntityNotFoundException("No characters found in database");
        }

        CharacterRM random = characters.get((int) (Math.random() * characters.size()));
        return characterMapper.toDto(random);
    }
}
