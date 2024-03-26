package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterPerson;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.RickAndMortyInternalService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyInternalServiceImpl implements RickAndMortyInternalService {
    private static long UPPER_CHARACTERS_BOUND;
    private final RickAndMortyClientServiceImpl rickAndMortyClientServiceImpl;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final SecureRandom random;

    public List<CharacterDto> getAllCharactersNameLike(String name) {
        name = "%" + name + "%";
        List<CharacterPerson> characters = characterRepository.findByNameLikeIgnoreCase(name);
        return characterMapper.toListDtos(characters);
    }

    public CharacterDto getRandomCharacter() {
        Optional<CharacterPerson> charById = characterRepository.findById(
                random.nextLong(UPPER_CHARACTERS_BOUND));
        return characterMapper.toDto(charById.orElseThrow(
                () -> new RuntimeException("Cannot find character with random number")));
    }

    @PostConstruct
    public void saveCharacters() {
        List<CharacterPerson> result = characterMapper.toListModels(
                rickAndMortyClientServiceImpl.getCharacters());
        UPPER_CHARACTERS_BOUND = result.size() + 1;
        characterRepository.saveAll(result);
    }
}
