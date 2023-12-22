package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void loadDataFromExternalApi() {
        characterRepository.saveAll(characterClient
                .getCharacters()
                .stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList()));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandom());
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterRepository.findByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}

