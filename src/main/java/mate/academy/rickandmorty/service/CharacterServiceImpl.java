package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.CharacterSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final int COUNT_OF_CHARACTERS = 826;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterSpecificationBuilder characterSpecificationBuilder;

    @Override
    public CharacterDto save(CreateCharacterRequestDto requestDto) {
        Character character = characterMapper.toModel(requestDto);
        return characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long id = getRandomId(COUNT_OF_CHARACTERS);
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find book with id: " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParameters params) {
        Specification<Character> characterSpecification =
                characterSpecificationBuilder.build(params);
        return characterRepository.findAll(characterSpecification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    private Long getRandomId(int size) {
        return (long) ThreadLocalRandom.current().nextInt(size + 1);
    }
}
