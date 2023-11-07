package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private InfoDto info;
    private List<CharacterExternalDto> results;

    public CharacterResponseDto() {
    }

    public CharacterResponseDto(String json) throws IOException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterResponseDto characterResponseDto =
                objectMapper.readValue(json, CharacterResponseDto.class);
        this.info = characterResponseDto.getInfo();
        this.results = characterResponseDto.getResults();
    }
}
