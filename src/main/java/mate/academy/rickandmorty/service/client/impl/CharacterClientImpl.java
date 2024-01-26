package mate.academy.rickandmorty.service.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.character.CharacterDto;
import mate.academy.rickandmorty.dto.external.character.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.external.character.CharactersDto;
import mate.academy.rickandmorty.service.client.CharacterClient;
import mate.academy.rickandmorty.service.http.impl.HttpResponseStringService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final HttpResponseStringService httpResponseStringService;

    @Override
    public CharactersDto getCharacters() throws IOException, InterruptedException {
        return objectMapper.readValue(httpResponseStringService.getHttpResponse(BASE_URL).body(),
                CharactersDto.class);
    }

    @Override
    public List<CharacterDto> getCharactersByIds(Integer... ids)
            throws IOException, InterruptedException {
        String url = BASE_URL + "/" + Arrays.toString(ids)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
        return ids.length == 1 ? List.of(objectMapper.readValue(httpResponseStringService
                .getHttpResponse(url).body(), CharacterDto.class))
                : objectMapper.readValue(httpResponseStringService.getHttpResponse(url).body(),
                    new TypeReference<>() {
                    });
    }

    @Override
    public CharactersDto getCharactersByParams(CharacterSearchParameters characterSearchParameters)
            throws IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL + "/?");
        if (characterSearchParameters.getName() != null) {
            urlBuilder.append("name=").append(characterSearchParameters.getName()
                    .replace(" ", "+")).append("&");
        }
        if (characterSearchParameters.getStatus() != null) {
            urlBuilder.append("status=").append(characterSearchParameters.getStatus()
                    .replace(" ", "+")).append("&");
        }
        if (characterSearchParameters.getSpecies() != null) {
            urlBuilder.append("species=").append(characterSearchParameters.getSpecies()
                    .replace(" ", "+")).append("&");
        }
        if (characterSearchParameters.getType() != null) {
            urlBuilder.append("type=").append(characterSearchParameters.getType()
                    .replace(" ", "+")).append("&");
        }
        if (characterSearchParameters.getGender() != null) {
            urlBuilder.append("gender=").append(characterSearchParameters.getGender()
                    .replace(" ", "+")).append("&");
        }
        return objectMapper.readValue(httpResponseStringService
                .getHttpResponse(urlBuilder.toString()).body(), CharactersDto.class);
    }
}
