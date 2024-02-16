package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.config.CharacterMapper;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParam;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.SpecificationBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final SpecificationBuilder<CharacterEntity> specificationBuilder;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();
    private final CharacterMapper characterMapper;

    public CharacterEntity getRandomCharacter() {
        long size = characterRepository.count();
        Long id = random.nextLong(size);
        return characterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Character not found with id: " + id));
    }

    public List<CharacterEntity> search(CharacterSearchParam param) {
        return characterRepository.findAll(specificationBuilder.build(param));
    }

}
