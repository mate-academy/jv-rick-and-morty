package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repo.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final static Random random = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterDto getRandomCharacter() {
        List<Long> characterIds = characterRepository
                .findAll()
                .stream()
                .map(CharacterEntity::getId)
                .toList();

        Long randomCharacterId = characterIds.get(random.nextInt(characterIds.size()));
        CharacterEntity characterEntity = characterRepository
                .findById(randomCharacterId)
                .orElseThrow();
        return characterMapper.toDto(characterEntity);
    }

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        return characterMapper.toDtoList(characterRepository.findByName(name));
    }

    @Override
    public List<CharacterEntity> saveAll(List<CharacterDto> characters) {
        return characterRepository.saveAll(characterMapper.toEntityList(characters));
    }

}
