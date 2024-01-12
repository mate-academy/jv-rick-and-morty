package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Long START_ID = 1L;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final SpecificationProvider specificationProvider;

    @Override
    public CharacterDto getRandomCharacterFromDb() {
        long countEntities = characterRepository.count();
        if (countEntities < START_ID) {
            throw new EntityNotFoundException("Can't get Entity from DB. Size of DB is: "
                    + countEntities);
        }
        long randomId = ThreadLocalRandom.current().nextLong(START_ID, countEntities + 1);
        Character character = characterRepository.findById(randomId)
                .orElseThrow(() -> new EntityNotFoundException("Can't get Entity from DB by Id: "
                                                                    + randomId));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name, Pageable pageable) {
        Specification<Character> characterSpecification
                = specificationProvider.getSpecification(name);
        List<CharacterDto> characterDtoList = characterRepository
                .findAll(characterSpecification, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
        if (characterDtoList.isEmpty()) {
            throw new EntityNotFoundException("Can't find Character by name: " + name);
        }
        return characterDtoList;
    }
}
