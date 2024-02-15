package mate.academy.rickandmorty.service.character;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.repository.character.CharacterSpecificationBuilder;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    public static final int ORIGIN = 1;
    private final static Random random = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterSpecificationBuilder specificationBuilder;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public List<CharacterDto> search(CharacterSearchParameters searchParams) {
        Specification<Character> characterSpecification = specificationBuilder.build(searchParams);
        return characterRepository.findAll(characterSpecification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository
                .findById(random.nextLong(ORIGIN, characterRepository.count()))
                .orElseThrow());
    }

    @Override
    public void fillDataBase() {
        if (characterRepository.count() == 0) {
            characterRepository.saveAll(characterClient.getRequestDtos()
                    .stream()
                    .map(characterMapper::toModel)
                    .toList());
        }
    }
}
