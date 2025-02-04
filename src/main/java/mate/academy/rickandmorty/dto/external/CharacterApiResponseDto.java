package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterApiResponseDto {
    private InfoDto info;
    private List<CharacterExternalResponseDto> results;

    @Data
    public static class InfoDto {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}
