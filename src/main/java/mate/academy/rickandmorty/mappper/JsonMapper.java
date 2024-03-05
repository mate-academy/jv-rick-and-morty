package mate.academy.rickandmorty.mappper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonMapper {
    private final ObjectMapper objectMapper;

    public <T> T mapJsonToDto(String jsonObject, Class<T> dto) {
        try {
            return objectMapper.readValue(jsonObject, dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
