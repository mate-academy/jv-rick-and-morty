package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
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
    private final Random random = new Random();
    private int size;

    @Override
    public void saveAll(List<CreateCharacterDto> characterDtoList) {
        List<Character> characters = characterDtoList.stream()
                        .map(characterMapper::toModel)
                                .toList();
        size = characters.size();
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long countOfRecords = characterRepository.getCountOfRecords();
        Long id = random.nextLong(1L, countOfRecords);
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isPresent()) {
            return characterMapper.toDto(optionalCharacter.get());
        }
        throw new RuntimeException("Can't find character by id: " + id);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
