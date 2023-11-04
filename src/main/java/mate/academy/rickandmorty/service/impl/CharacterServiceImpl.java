package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.client.RickAndMortyClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final int TOTAL_CHARACTERS_AMOUNT = 826;
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;

    public void saveAllToDB() {
        characterRepository.saveAll(rickAndMortyClient.getAllCharacters().stream()
                .map(characterMapper::toModel)
                .toList());
    }

    @Override
    public CharacterDto getRandom() {
        Long id = new Random().nextLong(TOTAL_CHARACTERS_AMOUNT);
        return characterMapper.toInternalDto(characterRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find character by id: " + id)));
    }

    @Override
    public List<CharacterDto> searchCharacters(String name, Pageable pageable) {
        return characterRepository.findAllByNameContainsIgnoreCase(name, pageable).stream()
                .map(characterMapper::toInternalDto)
                .toList();
    }
}
