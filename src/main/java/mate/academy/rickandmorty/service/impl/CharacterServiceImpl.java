package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.dtos.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String RANDOM_ENTITY_NOT_FOUND_MSG = "Couldn't get a random entity";
    private final CharacterRepository characterRepository;
    private final Random random = new Random();
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> findAllByPartName(String namePart) {
        return characterRepository.findAllByNameIsLikeIgnoreCase(namePart).stream()
                .map(characterMapper::toDto)
                .toList();

    }

    @Override
    public CharacterDto getRandomCharacter() {
        CharacterFromRickAndMorty randomCharacter = characterRepository
                .findById(random.nextLong(characterRepository.count()))
                .orElseThrow(() -> new EntityNotFoundException(RANDOM_ENTITY_NOT_FOUND_MSG));
        return characterMapper.toDto(randomCharacter);
    }
}
