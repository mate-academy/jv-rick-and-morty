package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
        for (int i = 1; i <= pagesQuantity; i++) {
            rickAndMortyClient.getPage(i).getResults().stream()
                    .map(characterMapper::toModel)
                    .map(characterRepository::save)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        int numberOfCharacters = rickAndMortyClient.getPage(1).getInfo().getCount();
        Random random = new Random();
        int randomCharacterId = random.nextInt(numberOfCharacters) + 1;
        Character character = characterRepository.findById((long) randomCharacterId).get();
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findAllByName(String name, Pageable pageable) {
        return characterRepository.findAllByNameContainingIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
