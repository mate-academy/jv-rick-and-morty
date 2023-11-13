package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterRateDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyClient client;
    private final RickAndMortyRepository rickAndMortyRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public void saveAll(List<CharacterRateDto> data) {
        List<Character> allCharacters = data.stream().map(characterMapper::toModel).toList();
        rickAndMortyRepository.saveAll(allCharacters);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        List<Character> characters = rickAndMortyRepository.findByNameContaining(name);
        return characters.stream()
                .map(characterMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long count = rickAndMortyRepository.count();
        Character randomCharacter = rickAndMortyRepository.findById(random.nextLong(count))
                .orElseThrow(() -> new RuntimeException("Can't find random character"));
        return characterMapper.toDto(randomCharacter);
    }

    @PostConstruct
    public void unit() {
        saveAll(client.getAllCharacter());
    }
}
