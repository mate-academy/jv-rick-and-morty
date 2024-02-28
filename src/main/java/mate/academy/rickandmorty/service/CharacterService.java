package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final CharacterClient client;

    public List<CharacterDto> findCharacterByName(String name) {
        return client.getCharacterByName(name);
    }

    public CharacterDto getRandomCharacter() {
        return client.getRandomCharacter();
    }
}
