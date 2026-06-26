package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseCharacterDto {
    private Info info;
    private List<ExternalCharacterDto> results;

    @Getter
    @Setter
    @ToString
    public static class Info {
        private String next;
    }
}
