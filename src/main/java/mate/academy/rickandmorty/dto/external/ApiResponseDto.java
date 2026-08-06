package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponseDto {
    private PageInfo info;
    private List<ExternalCharacterDto> results;

    @Data
    public static class PageInfo {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}

