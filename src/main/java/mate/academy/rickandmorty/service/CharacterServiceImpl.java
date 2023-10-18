package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repositiry.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static int countCharacters;
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public CharacterDto getRandomCharacter() {
        int id = RANDOM.nextInt(countCharacters) + 1;
        return mapper.toDto(characterRepository.findById((long) id).orElseThrow(() ->
                new RuntimeException("Character was not found with id " + id)));
    }

    @Override
    public void saveAll(List<CharacterResponseDto> listDto) {
        List<Character> characters = listDto.stream()
                .map(mapper::toModel)
                .toList();
        countCharacters = characters.size();
        characterRepository.saveAll(characters);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(mapper::toDto)
                .toList();
    }
}
