package mate.academy.rickandmorty.dto;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterRM;

public record CharacterResponse(Info info, List<CharacterRM> results) {
    public record Info(int count, int pages, String next, String prev) {}
}
