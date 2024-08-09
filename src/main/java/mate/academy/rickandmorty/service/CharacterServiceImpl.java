package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.repository.character.CharacterSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final Random random = new Random();

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    private final CharacterSpecificationBuilder characterSpecificationBuilder;

    @Override
    public List<CharacterDto> getAll() {
        return characterMapper.toListDto(characterRepository.findAll());
    }

    @Override
    public CharacterDto findById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find character by id: " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<CharacterDto> characters = getAll();
        int countALlCharacters = characters.size();
        Long randomId = (long) random.nextInt(countALlCharacters + 1);
        return findById(randomId);
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParameters characterSearchParameters) {
        Specification<Character> characterSpecification =
                characterSpecificationBuilder.build(characterSearchParameters);
        return characterRepository.findAll(characterSpecification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
