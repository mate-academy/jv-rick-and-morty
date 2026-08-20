package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.RickAndMortyResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterImportService {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;

    public void fetchAndSaveAllCharacters() {
        List<ExternalCharacterDto> externalCharacters =
                fetchAllCharacters();

        List<Character> characters = externalCharacters.stream()
                .map(this::mapToCharacter)
                .toList();

        characterRepository.saveAll(characters);
    }

    private List<ExternalCharacterDto> fetchAllCharacters() {
        List<ExternalCharacterDto> characters = new ArrayList<>();

        int page = 1;
        int totalPages;

        do {
            RickAndMortyResponseDto response =
                    client.getCharacters(page);

            characters.addAll(response.getResults());

            totalPages = response.getInfo().getPages();
            page++;
        } while (page <= totalPages);

        return characters;
    }

    private Character mapToCharacter(
            ExternalCharacterDto externalCharacterDto) {
        Character character = new Character();

        character.setExternalId(
                String.valueOf(externalCharacterDto.getId())
        );
        character.setName(externalCharacterDto.getName());
        character.setStatus(externalCharacterDto.getStatus());
        character.setGender(externalCharacterDto.getGender());

        return character;
    }
}
