package mate.academy.rickandmorty.service.character;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParams;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
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
    private final CharacterRepository characterRepository;
    private final CharacterSpecificationBuilder specificationBuilder;
    private final CharacterMapper characterMapper;
    private final Random random;
    private final CharacterClient characterClient;

    @Override
    public List<CharacterDto> saveAll() {
        List<CreateCharacterRequestDto> requestDtos = characterClient.getRequestDtos();
        return characterRepository.saveAll(requestDtos
                .stream()
                .map(characterMapper::toModel)
                .toList())
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public List<CharacterDto> search(CharacterSearchParams searchParams) {
        characterRepository.count();
        Specification<Character> characterSpecification = specificationBuilder.build(searchParams);
        return characterRepository.findAll(characterSpecification)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        characterRepository.count();
        return characterMapper.toDto(characterRepository
                .findById(random.nextLong(ORIGIN, 500))
                .orElseThrow());
    }
}
