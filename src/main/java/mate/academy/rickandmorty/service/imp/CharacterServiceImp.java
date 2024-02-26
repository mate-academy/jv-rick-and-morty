package mate.academy.rickandmorty.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImp implements CharacterService {
    private static final String CANT_GET_RANDOM_MSG = "Can't get random character with id ";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = random.nextLong(count);
        Optional<Character> characterById = characterRepository.findById(randomId);
        if (characterById.isPresent()) {
            return characterMapper.toDto(characterById.get());
        }
        throw new RuntimeException(CANT_GET_RANDOM_MSG + randomId);
    }

    @Override
    public List<CharacterResponseDto> findByNamePart(Pageable pageable, String namePart) {
        List<Character> characters = characterRepository
                .findAllByNameContainsIgnoreCase(pageable, namePart);
        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
