package mate.academy.rickandmorty.service.loader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.ApiClient;
import mate.academy.rickandmorty.dto.page.PageResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterImportServiceImpl implements ImportService {
    private static final String CHARACTERS_URL =
            "https://rickandmortyapi.com/api/character";
    private static final String JSON_PART = "{";
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public void importCharacters() {
        String nextUrl = CHARACTERS_URL;

        try {
            while (nextUrl != null) {
                String pageJson = apiClient.getPage(nextUrl);

                if (!isValidJson(pageJson)) {
                    nextUrl = null;
                    break;
                }

                PageResponseDto pageResponseDto =
                        objectMapper.readValue(pageJson, PageResponseDto.class);

                List<Character> characters = pageResponseDto.results().stream()
                        .map(characterMapper::toModel)
                        .toList();

                List<Character> toSave = characters.stream()
                        .filter(c -> !characterRepository.existsByExternalId(c.getExternalId()))
                        .toList();

                characterRepository.saveAll(toSave);

                nextUrl = pageResponseDto.info().next();
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidJson(String pageJson) {
        return pageJson != null && pageJson.trim().startsWith(JSON_PART);
    }
}
