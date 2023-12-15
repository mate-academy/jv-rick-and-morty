package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterDto getWikiAboutRandomCharacter() {
        long numberOfCharacters = getNumberOfCharacters();
        long randomCharacterId = random.nextLong(numberOfCharacters);

        CharacterFromRickAndMorty randomCharacterFromRickAndMorty = characterRepository.findById(
                randomCharacterId).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't get character id" + randomCharacterId)
        );

        return characterMapper.toCharacterDto(randomCharacterFromRickAndMorty);
    }

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        return characterRepository.findCharactersByNameIsContaining(name).stream()
                .map(characterMapper::toCharacterDto)
                .toList();
    }

    @CachePut("numberOfCharactersCache")
    public long getNumberOfCharacters() {
        return characterRepository.count();
    }
}
