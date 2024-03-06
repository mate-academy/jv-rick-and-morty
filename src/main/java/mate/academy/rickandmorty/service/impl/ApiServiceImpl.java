package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mappper.JsonMapper;
import mate.academy.rickandmorty.service.ApiService;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.util.HttpClientUtilBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiServiceImpl implements ApiService {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final JsonMapper jsonMapper;
    private final CharacterService characterService;

    @Override
    public void saveDataToBd() {
        characterService.saveAll(getCharactersFromApi());
    }

    private List<CharacterResponseDto> getCharactersFromApi() {
        List<CharacterResponseDto> characterDtoList = new ArrayList<>();
        ApiResponseDataDto apiResponseDataDto = null;

        do {
            String url = API_URL;
            if (apiResponseDataDto != null) {
                url = apiResponseDataDto.getInfo().next();
            }

            apiResponseDataDto = fetchDataFromApi(url);
            characterDtoList.addAll(apiResponseDataDto.getResults());
        } while (apiResponseDataDto.getInfo().next() != null);

        return characterDtoList;
    }

    private ApiResponseDataDto fetchDataFromApi(String url) {
        String responseBody = new HttpClientUtilBuilder().setUrl(url)
                .httpGetRequest()
                .getResponseBody();
        return jsonMapper.mapJsonToDto(responseBody, ApiResponseDataDto.class);
    }
}
