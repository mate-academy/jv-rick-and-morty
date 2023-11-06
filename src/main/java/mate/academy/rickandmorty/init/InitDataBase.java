package mate.academy.rickandmorty.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.exception.DataProcessException;
import mate.academy.rickandmorty.mapper.DtoMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataBase implements CommandLineRunner {
    private static final String URL = "https://rickandmortyapi.com/api/character/";
    private static final int NUMBER_OF_CHARACTERS = 20;
    private final DtoMapper mapper;
    private final ObjectMapper jasonMapper;
    private final CharacterRepository characterRepository;

    @Override
    public void run(String... args) {
        HttpClient client = HttpClient.newHttpClient();
        List<Character> characters = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_CHARACTERS; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(URL + i))
                    .build();
            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                CharacterResponseDto responseDto = jasonMapper.readValue(response.body(),
                        CharacterResponseDto.class);

                characters.add(mapper.responseDtoToCharacter(responseDto));
            } catch (IOException | InterruptedException e) {
                throw new DataProcessException("Cannot get data from API", e);
            }
        }
        try {
            characterRepository.saveAllAndFlush(characters);
        } catch (Exception e) {
            throw new DataProcessException("Cannot save characters to DB", e);
        }
    }
}
