package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.RandomCharacterException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.CharacterRepositoryMetadata;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String CAN_T_GET_RANDOM_CHARACTER = "Can't get random Character";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterRepositoryMetadata repositoryMetadata;

    @Override
    public CharacterDto getRandomCharacter() {
        long numberOfCharacters = repositoryMetadata.getNumberOfCharacters();
        if (numberOfCharacters < 1) {
            throw new RandomCharacterException(CAN_T_GET_RANDOM_CHARACTER);
        }
        Random random = new Random();
        long randomCharacterId = Math.abs(random.nextLong() % numberOfCharacters + 1);
        Character randomCharacter = characterRepository.findById(randomCharacterId)
                .orElseThrow(() -> new RandomCharacterException(CAN_T_GET_RANDOM_CHARACTER));
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterDto> findCharacterByName(String name, Pageable pageable) {
        return characterRepository
                .findAllByNameContainingIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
