package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient client;
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final Random random = new Random();

    @Override
    public void loadingAndSavingCharacters() {
        CharacterResponseDataDto characterResponseDataDto = client.getCharactersData();
        repository.saveAll(characterResponseDataDto.getResults().stream()
                .map(mapper::toModel)
                .toList());
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return mapper.toDto(repository.findById(random.nextLong(repository.count())).orElseThrow(
                () -> new EntityNotFoundException("Can't get random character.")
        ));
    }

    @Override
    public List<CharacterDto> getCharactersWhoseNameContains(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
    }
}
