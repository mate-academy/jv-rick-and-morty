package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiDataServiceImpl implements ApiDataService {
    private static final String GET_ALL_CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;

    @Override
    public void getDataFromApi() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_ALL_CHARACTERS_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            CharactersDto charactersDto = objectMapper.readValue(response.body(),
                    CharactersDto.class);

            List<Character> characters = charactersDto.results()
                     .stream()
                     .map(responseDto -> {
                         Character character = new Character();
                         character.setExternalId(responseDto.getExternalId());
                         character.setGender(responseDto.getGender());
                         character.setName(responseDto.getName());
                         character.setStatus(responseDto.getStatus());
                         return character;
                     })
                     .toList();
            characterRepository.saveAll(characters);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
