package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Random random = new Random();
    private static final int CHARACTER_COUNT = 800;

    private final RickAndMortyClientApi rickAndMortyClientApi;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public InternalCharacterDto getRandomCharacter() {
        Long randomNumber = random.nextLong(CHARACTER_COUNT);
        return characterRepository.findByExternalId(randomNumber)
                .map(characterMapper::fromEntityToInternal)
                .orElseGet(() -> {
                    ExternalCharacterDto response = rickAndMortyClientApi
                            .getRandomCharacter(randomNumber);
                    Character character = characterMapper
                            .fromExternalToEntity(response);

                    return characterMapper.fromEntityToInternal(
                            characterRepository.save(character)
                    );
                });
    }

    @Override
    public List<InternalCharacterDto> getCharactersByName(String name) {
        List<Character> characterList = characterRepository.findAllByName(name);

        List<InternalCharacterDto> characterDtos = new ArrayList<>(
                characterList.stream()
                .map(characterMapper::fromEntityToInternal)
                .toList()
        );

        if (characterList.isEmpty()) {
            List<ExternalCharacterDto> response = rickAndMortyClientApi.getCharactersByName(name);
            List<Character> newCharacters = response.stream()
                    .map(characterMapper::fromExternalToEntity)
                    .toList();
            characterRepository.saveAll(newCharacters);
            characterDtos.addAll(newCharacters.stream()
                    .map(characterMapper::fromEntityToInternalDto).toList());
        }
        return characterDtos;
    }
}

