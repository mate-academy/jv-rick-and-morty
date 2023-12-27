package mate.academy.rickandmorty.dto.character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharactersInfo {
    private int count;
    private int pages;
    private String next;
}
