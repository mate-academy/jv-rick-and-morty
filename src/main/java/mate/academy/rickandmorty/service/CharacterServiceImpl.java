package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.RickAndMortyPageDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<CharacterEntity> findAllByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public CharacterEntity getRandom() {
        List<CharacterEntity> repositoryAll = characterRepository.findAll();
        return repositoryAll.get(new Random().nextInt(repositoryAll.size()));
    }

    public boolean isDatabaseEmpty() {
        return characterRepository.count() == 0;
    }

    public void importAllCharacters() {
        String currentUrl = URL;
        HttpClient client = HttpClient.newHttpClient();

        while (currentUrl != null) {
            RickAndMortyPageDto pageDto = fetchPage(client, currentUrl);

            saveToDatabase(pageDto.getResults());

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentUrl = pageDto.getInfo().getNext();
        }
    }

    private void saveToDatabase(List<ExternalCharacterDto> pageDtoResults) {
        for (ExternalCharacterDto ecd : pageDtoResults) {
            characterRepository.save(characterMapper.toModel(ecd));
        }
    }

    private RickAndMortyPageDto fetchPage(HttpClient httpClient, String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse response = null;
        try {
            response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException("API вернул статус " + response.statusCode()
                    + " для URL: " + url);
        }
        try {
            return objectMapper.readValue((String) response.body(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
