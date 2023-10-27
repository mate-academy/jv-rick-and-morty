package mate.academy.rickandmorty.api.util;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalResponseDto;
import mate.academy.rickandmorty.dto.external.Result;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    private final PersonageMapper personageMapper;
    private final RestTemplate restTemplate;
    @Value("${rick-and-morty.api.url}")
    private String baseCharacterUrl;

    public List<Personage> getAllPersonages() {
        List<Result> resultList = new ArrayList<>();
        ExternalResponseDto externalResponseDto =
                restTemplate.getForEntity(baseCharacterUrl, ExternalResponseDto.class).getBody();
        String nextUrl = externalResponseDto.info().next();
        while (nextUrl != null) {
            resultList.addAll(externalResponseDto.resultList());
            externalResponseDto = restTemplate
                    .getForEntity(nextUrl, ExternalResponseDto.class)
                    .getBody();
            nextUrl = externalResponseDto.info().next();
        }
        return resultList
                .stream()
                .map(personageMapper::toPersonage)
                .toList();
    }
}
