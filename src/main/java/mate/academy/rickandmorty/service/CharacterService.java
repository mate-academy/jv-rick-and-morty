package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    public CharacterWikiDto generateRandomCharacterWikiDto() {
        Character character = characterRepository.findRandom();

        if (character == null) {
            throw new DataProcessingException("No characters found in DB");
        }

        return characterMapper.toWikiDto(character);
    }
}
