package mate.academy.rickandmorty.service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CustomCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static Long minId = 1L;
    private static Long maxId = 1L;
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final Random random = new Random();

    @Override
    public CharacterDto getRandomCharacter() {
        long generatedRandomId = random.nextLong(minId, maxId);
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
    public void saveAllWithBounds(List<CharacterDataDto> data) {
        List<CustomCharacter> savedCharacters = repository.saveAll(
                data.stream()
                .map(mapper::toModel)
                .toList());
        setRanomGeneratorLongBounds(savedCharacters);
    }
    
    private void setRanomGeneratorLongBounds(List<CustomCharacter> savedCharacters) {
        minId = savedCharacters.stream()
                .map(ch -> ch.getId())
                .min(Comparator.naturalOrder())
                .get();
        maxId = savedCharacters.stream()
                .map(ch -> ch.getId())
                .max(Comparator.naturalOrder())
                .get() + 1;
    }
}
