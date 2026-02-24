package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto {
    private InfoDto info;
    private List<CharacterDto> results;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InfoDto {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}
