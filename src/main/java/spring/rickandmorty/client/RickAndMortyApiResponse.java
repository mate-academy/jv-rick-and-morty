package spring.rickandmorty.client;

import java.util.List;
import lombok.Data;
import spring.rickandmorty.dto.ExternalCharacterDto;

@Data
public class RickAndMortyApiResponse {
    private List<ExternalCharacterDto> results;
}
