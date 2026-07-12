package mate.academy.rickandmorty.model.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RickAndMortyResponse {
    private Info info;
    private List<Result> results;

    @Getter
    @Setter
    public static class Info {
        private int pages;
    }

    @Getter
    @Setter
    public static class Result {
        private String id;
        private String name;
        private String status;
        private String gender;
    }
}
