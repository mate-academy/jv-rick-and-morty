package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class ExternalCharacterDto {
    private ExternalCharacterDtoInfo info;
    private List<ExternalCharacterDtoResult> results;
}
