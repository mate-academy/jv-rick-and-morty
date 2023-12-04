package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final Long maxId = 257L;

    @Override
    public CharacterDto getRandomCharacter() {
        long generatedRandomId = new Random().nextLong(1, maxId);
        return mapper.toDto(repository.findById(generatedRandomId).orElseThrow(
                () -> new RuntimeException("Can't find character with id: " + generatedRandomId)));
    }

    @Override
    public List<CharacterDto> getByName(String name) {
        return repository.findByNameContainsIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<CharacterDataDto> data) {
        repository.saveAll(
                data.stream()
                .map(mapper::toModel)
                .toList());
    }
}
