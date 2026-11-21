package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.internal.InternalResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.specification.NameSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final NameSpecification nameSpecification;
    private final Random random;

    public CharacterServiceImpl(
            CharacterClient client,
            CharacterRepository characterRepository,
            CharacterMapper characterMapper,
            NameSpecification nameSpecification,
            Random random
    ) {
        this.client = client;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.nameSpecification = nameSpecification;
        this.random = random;
    }

    @Override
    public InternalResponseDto getRandomCharacter() {
        List<Long> ids = characterRepository.findAllIds();

        if (ids.isEmpty()) {
            return null;
        }

        long randomId = ids.get(random.nextInt(ids.size()));

        return characterRepository.findById(randomId)
                .map(characterMapper::toInternalResponseDto)
                .orElse(null);
    }

    @Override
    public List<InternalResponseDto> getCharactersByParams(
            String partName) {
        Specification<Character> specification = nameSpecification
                .getSpecification(partName);
        return characterRepository.findAll(specification).stream()
                .map(e -> characterMapper.toInternalResponseDto(e))
                .toList();
    }

    @Override
    public void saveAll() {
        List<Character> characters = client.getCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        characterRepository.saveAll(characters);
    }
}
