package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterDao;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterDao characterDao;

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        Long randomId = random.nextLong(characterDao.count()) + 1;
        Character character = characterDao.findById(randomId).orElseThrow(
                () -> new NoSuchElementException("Can't find character by id " + randomId)
        );
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterDao.findAllByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
