package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.model.CharacterEntity;

@Data
public class CharacterResponse {
    private Info info;
    private List<CharacterEntity> results;

    @Data
    static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;
    }
}
