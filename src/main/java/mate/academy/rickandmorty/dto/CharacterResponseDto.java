package mate.academy.rickandmorty.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
