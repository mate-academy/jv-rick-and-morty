package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private int listSize;
    private final RandomUtil random;
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public CharacterDto getRandomCharacter() {
        int id = random.util().nextInt(listSize) + 1;
        return mapper.toDto(characterRepository.findById((long) id).orElseThrow(
                () -> new RuntimeException("Characters not found by id: " + id))
        );
    }

    @Override
    public void saveAll(List<CharacterResponseDto> listDto) {
        List<Character> characters = listDto.stream()
                .map(mapper::toModel)
                .toList();
        listSize = characters.size();
        characterRepository.saveAll(characters);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(mapper::toDto)
                .toList();
    }
}
