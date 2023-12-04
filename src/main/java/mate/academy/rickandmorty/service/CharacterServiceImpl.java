package mate.academy.rickandmorty.service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CustomCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final Random randomLong;

    @Override
    public CharacterDto getRandomCharacter() {
        long generatedRandomId = randomLong.nextLong(1, getMaxId() + 1);        
        return mapper.toDto(repository.getReferenceById(generatedRandomId));
    }

    @Override
    public List<CharacterDto> getByName(String name) {
        return repository.findByNameIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
    }
    
    private Long getMaxId() {
        return repository.findAll().stream()
                .map(CustomCharacter::getId)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .get();
    }
}
