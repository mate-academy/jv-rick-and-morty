package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyWikiImpl implements RickAndMortyWiki {
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    private void init() {
        int pagesQuantity = rickAndMortyClient.getPage(1).getInfo().getPages();
        List<Character> characters = new ArrayList<>();
        for (int i = 1; i <= pagesQuantity; i++) {
            characters.addAll(
                    rickAndMortyClient.getPage(i).getResults().stream()
                            .map(characterMapper::toModel)
                            .toList()
            );
        }
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Random random = new Random();
        long randomCharacterId = random.nextLong(characterRepository.count()) + 1;
        Character character = characterRepository.findById(randomCharacterId).get();
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findAllByName(String name, Pageable pageable) {
        return characterRepository.findAllByNameContainingIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
