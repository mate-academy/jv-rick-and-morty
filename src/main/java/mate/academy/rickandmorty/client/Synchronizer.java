package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Synchronizer {
    private static final String ALL_CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient;
    private final CharacterMapper mapper;
    private final MovieCharacterRepository movieCharacterRepository;

    @PostConstruct
    public void syncExternalCharacters() {
        ApiResponseDto apiResponseDto = httpClient.get(
                ALL_CHARACTER_URL, ApiResponseDto.class);
        saveDtosToDb(apiResponseDto);

        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(
                    apiResponseDto.getInfo().getNext(), ApiResponseDto.class);
            saveDtosToDb(apiResponseDto);
        }
    }

    private void saveDtosToDb(ApiResponseDto apiResponseDto) {
        Map<Long, ApiCharacterDto> externalDtos = apiResponseDto.getResults().stream()
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));

        Set<Long> externalIds = externalDtos.keySet();

        List<MovieCharacter> existingInDbMovieCharacters =
                movieCharacterRepository.findAllByExternalIdIn(externalIds);

        Map<Long, MovieCharacter> existingCharactersWithIds =
                existingInDbMovieCharacters.stream()
                        .collect(
                                Collectors.toMap(
                                        MovieCharacter::getExternalId, Function.identity()));

        Set<Long> existingIds = existingCharactersWithIds.keySet();

        externalIds.removeAll(existingIds);

        List<MovieCharacter> charactersToSave = externalIds.stream().map(
                        i -> mapper.toEntity(externalDtos.get(i)))
                .collect(Collectors.toList());

        movieCharacterRepository.saveAll(charactersToSave);
    }
}
