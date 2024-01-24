package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    public Page<CharacterDto> getAllByName(String name, Pageable pageable) {
        Page<Character> characterPage = characterRepository
                .findCharactersByNameContainingIgnoreCase(name, pageable);

        return characterPage.map(characterMapper::toDto);
    }

    @Transactional
    public void parseAndSave() {
        CharacterResponseDataDto responseDataDtos = characterClient.getAllCharacters();
        List<Character> characters = responseDataDtos.getResults().stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
    }

    public CharacterDto getRandomCharacter() {
        long characterCount = characterRepository.count();
        if (characterCount > 0) {
            int randomIndex = new Random().nextInt((int) characterCount);
            Page<Character> randomCharacterPage = characterRepository.findAll(
                    PageRequest.of(randomIndex, 1));
            if (!randomCharacterPage.isEmpty()) {
                return characterMapper.toDto(randomCharacterPage.getContent().get(0));
            }
        }
        return null;
    }

}
