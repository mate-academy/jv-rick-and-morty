package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final int PAGE_SIZE = 1;
    private static final int FIRST_INDEX = 0;
    private final CharacterRepository characterRepository;
    private final CharactersClient charactersClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void initCharacters() {
        List<Character> list = charactersClient.loadCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        characterRepository.saveAll(list);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long numberOfCharacters = characterRepository.count();
        int pageNumber = (int)(Math.random() * numberOfCharacters);
        Page<Character> page = characterRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE));
        return Optional.ofNullable(page.getContent().get(FIRST_INDEX))
                .map(characterMapper::toDto)
                .orElseThrow(() -> new CharacterNotFoundException(
                        "Can't generate random character")
                );
    }

    @Override
    public List<CharacterDto> searchCharacters(
            String name, @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return characterRepository.findAllByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
