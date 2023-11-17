package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.config.CharacterMapper;
import mate.academy.rickandmorty.dto.CharacterSearchParam;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final SpecificationBuilder<Character> specificationBuilder;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();
    private final CharacterMapper characterMapper;

    @Override
    public Character getRandomCharacter() {
        long size = characterRepository.count();
        Long id = random.nextLong(size);
        return characterRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Character not found with id: " + id));
    }

    @Override
    public List<Character> search(CharacterSearchParam param) {
        return characterRepository.findAll(specificationBuilder.build(param));
    }
}
