package mate.academy.rickandmorty.dto.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class InfoResponseDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
