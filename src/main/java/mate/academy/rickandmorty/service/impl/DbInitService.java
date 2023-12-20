package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.dtos.CharacterResponseDto;
import mate.academy.rickandmorty.dto.character.dtos.CharactersResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.RickAndMortyClientToDbService;
import mate.academy.rickandmorty.service.rick.and.morty.client.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbInitService implements RickAndMortyClientToDbService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final RickAndMortyClient rickAndMortyClient;

    @PostConstruct
    public void getAllCharacters() {
        int currentPage = 0;
        boolean isExit = true;
        while (isExit) {
            CharactersResponseDataDto charactersResponse = rickAndMortyClient
                    .getCharacters(currentPage);
            if (charactersResponse.getInfo().getNext() == null) {
                isExit = false;
            }
            List<CharacterResponseDto> results = charactersResponse.getResults();
            List<CharacterFromRickAndMorty> list = results.stream()
                    .map(characterMapper::toCharacterModel)
                    .toList();
            characterRepository.saveAll(list);
            currentPage++;
        }
    }
}
