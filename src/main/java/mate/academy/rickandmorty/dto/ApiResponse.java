package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Info info;
    private List<CharacterDto> results;

    @Getter
    @Setter
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}
