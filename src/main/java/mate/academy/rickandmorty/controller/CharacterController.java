package mate.academy.rickandmorty.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.character.CharacterDto;
import mate.academy.rickandmorty.dto.external.character.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.external.character.CharactersDto;
import mate.academy.rickandmorty.service.client.CharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final CharacterClient characterClient;

    @GetMapping
    public CharactersDto getCharacters() throws IOException, InterruptedException {
        return characterClient.getCharacters();
    }

    @GetMapping("/{ids}")
    public List<CharacterDto> getCharacterByIds(@PathVariable Integer[] ids)
            throws IOException, InterruptedException {
        return characterClient.getCharactersByIds(ids);
    }

    @GetMapping("/search")
    public CharactersDto getCharactersByParams(CharacterSearchParameters characterSearchParameters)
            throws IOException, InterruptedException {
        return characterClient.getCharactersByParams(characterSearchParameters);
    }
}
