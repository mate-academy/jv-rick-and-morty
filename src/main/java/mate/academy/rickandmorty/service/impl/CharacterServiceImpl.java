package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.Fetcher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random random = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Fetcher fetcher;

    @Override
    public CharacterDto save(CharacterResponseDto characterResponseDto) {
        Character character = characterMapper.toModel(characterResponseDto);
        return characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomId = random.nextLong(characterRepository.count());
        return characterMapper.toDto(characterRepository.getReferenceById(randomId));
    }

    @Override
    public List<CharacterDto> findAllByNamePart(String namePart) {
        return characterMapper.toDtoList(
                characterRepository.findByNameContainingIgnoreCase(namePart)
        );
    }

    @PostConstruct
    public void init() {
        List<Character> characters =
                characterMapper.toModelList(fetcher.getDataFromApi());
        characterRepository.saveAll(characters);
    }
}
