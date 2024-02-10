package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Random random = new Random();
    private static Long size_of_character;

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        size_of_character = characterRepository.countAll();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long randomNum = random.nextLong(size_of_character + 1);
        return characterMapper.toDto(
                characterRepository.findById(
                        randomNum
                ).orElseThrow(() -> new EntityNotFoundException(
                        "can't find Character by id " + randomNum)
                )
        );
    }

    @Override
    public List<CharacterDto> getByName(String name) {
        return characterMapper.toDtos(
                characterRepository.findAllByNameContainsIgnoreCase(name)
        );
    }
}
