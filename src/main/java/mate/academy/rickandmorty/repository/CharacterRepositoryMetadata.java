package mate.academy.rickandmorty.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CharacterRepositoryMetadata {
    private int numberOfCharacters;
}
