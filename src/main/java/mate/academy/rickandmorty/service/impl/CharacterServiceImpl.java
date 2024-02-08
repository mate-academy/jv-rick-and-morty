package mate.academy.rickandmorty.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private static Random random = new Random();

    @Override
    public CharacterDto getRandomCharacter() {
        long size = characterRepository.countAll();
        long randomNum = random.nextLong(size + 1);
        return characterMapper.toDto(
                characterRepository.findById(
                        randomNum
                ).orElseThrow(() -> new EntityNotFoundException("can't find Character by id " + randomNum))
        );
    }

    @Override
    public List<CharacterDto> getByName(String name) {
        return characterMapper.toDtos(
                characterRepository.findAllByNameContains(name)
        );
    }
}
