package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Component
public class ExternalCharacterResponseDto {
    private InfoResponseDto info;
    private List<CharacterResponseDto> results;

}
