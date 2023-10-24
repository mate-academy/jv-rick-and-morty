package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseMetaDto;
import mate.academy.rickandmorty.model.AnimationCharacter;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;
import mate.academy.rickandmorty.repository.AnimationCharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApiClient {
    private final AnimationCharacterRepository repository;
    private final GenderService genderService;
    private final StatusService statusService;
    private final ObjectMapper objectMapper; 
    @Value("${mate.academy.rickandmorty.baseUrl}")
    private String baseUrl;
    
    public void getListAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseMetaDto metaMap = objectMapper.readValue(
                    response.body(), CharacterResponseMetaDto.class);
            int countOfPages = Integer.parseInt(metaMap.info().get("pages"));
            for (int i = 1; i <= 1; i++) {
                httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(baseUrl + "/?page=" + i))
                        .build();
                response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dtos = objectMapper.readValue(
                        response.body(), CharacterResponseDataDto.class);
                for (CharacterResponseDto dto: dtos.getResults()) {
                    saveCharacter(dto);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveCharacter(CharacterResponseDto dto) {
        Gender gender = genderService.getByName(dto.gender());
        Status status = statusService.getByName(dto.status());
        AnimationCharacter character = new AnimationCharacter();
        character.setName(dto.name());
        character.setExternalId(dto.id());
        character.setStatus(status);
        character.setGender(gender);
        repository.save(character);
    }
}
