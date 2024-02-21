package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = random.nextLong(count);
        Character randomCharacter = characterRepository.findById(randomId)
                .orElseThrow(RuntimeException::new);
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterDto> findByNamePart(String namePart) {
        return characterRepository.findAllByNameLikeIgnoreCase(namePart)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<Character> characters) {
        characters.stream()
                .map(characterRepository::save)
                .toList();
    }
}
