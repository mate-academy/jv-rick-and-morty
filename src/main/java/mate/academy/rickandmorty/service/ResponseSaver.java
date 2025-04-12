package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterModelDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseSaver {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final RickAndMortyApiClient rickAndMortyApiClient;
    private final CharacterMapper characterMapper;

    public void saveResponse() {
        String currentPage = "https://rickandmortyapi.com/api/character";
        do {
            RickAndMortyResponseDto responseDto = rickAndMortyApiClient.getResponse(currentPage);
            List<CharacterModelDto> characterInfo = responseDto.getCharactersInfo();
            characterInfo.stream()
                    .map(characterMapper::toModel)
                    .forEach(rickAndMortyRepository::save);
            currentPage = responseDto.getInfo().getNext();
        } while (currentPage != null);
        System.out.println("DB is saved.");
    }
}
