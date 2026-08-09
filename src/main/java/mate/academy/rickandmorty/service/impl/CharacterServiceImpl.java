package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;
    private final Random random = new Random();

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        if (count == 0) {
            throw new RuntimeException("Database is empty");
        }
        int randomIndex = random.nextInt((int) count);
        PageRequest pageRequest = PageRequest.of(randomIndex, 1);
        Page<Character> characterPage = characterRepository.findAll(pageRequest);
        Character character = characterPage.getContent().get(0);
        return mapper.toDto(character);
    }

    @Override
    public List<CharacterDto> searchByName(String name) {
        List<Character> allByNameContains = characterRepository.findAllByNameContainingIgnoreCase(name);
        List<CharacterDto> characterDtos = new ArrayList<>();
        allByNameContains.forEach((character) -> characterDtos.add(mapper.toDto(character)));
        return characterDtos;
    }
}
