package mate.academy.rickandmorty.initialization;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import mate.academy.rickandmorty.service.CartoonCharacterClient;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThirdPartyApiDataLoader {
    private final CartoonCharacterClient characterClient;
    private final CartoonCharacterService characterService;

    @PostConstruct
    private void loadData() {
        List<CreateCartoonCharacterRequestDto> characters = characterClient.getAllCharacters();
        characterService.saveAll(characters);
    }
}
