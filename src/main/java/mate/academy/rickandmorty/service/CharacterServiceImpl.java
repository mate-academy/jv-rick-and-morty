package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;

    @Override
    public InternalCharacterDto getRandomCharacter() {
        Long id = RANDOM.nextLong(characterRepository.count()) + 1;
        return mapper.toDto(characterRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t get random character id" + id)));
    }

    @Override
    public void saveAll(List<ExternalCharacterDto> listDto) {
        List<Character> characters = listDto.stream()
                .map(mapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public List<InternalCharacterDto> findByName(String name) {
        return characterRepository.findByNameContaining(name).stream()
                .map(mapper::toDto)
                .toList();
    }
}
