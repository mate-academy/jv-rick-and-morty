package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient client;
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @Override
    public CharacterDto getRandom() {
        return mapper.toDto(repository.getRandom());
    }

    @Override
    public List<CharacterDto> getAllByName(String name) {
        return mapper.toCharacterDtoList(repository.findCharactersByNameContainsIgnoreCase(name));
    }

    @PostConstruct
    public void getDataFromApi() {
        repository.saveAll(mapper.toModelList(client.getAllCharacters()));
    }
}
