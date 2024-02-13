package mate.academy.rickandmorty.util;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDtoWithoutExternalId;
import mate.academy.rickandmorty.service.ApplicationRunnerService;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomApplicationRunner implements ApplicationRunner {
    private final ApplicationRunnerService applicationRunnerService;
    private final CharacterService characterService;

    @Override
    public void run(ApplicationArguments args) {
        List<CharacterDtoWithoutExternalId> characterDtoList
                = applicationRunnerService.getCharactersFromApi();
        characterService.saveAll(characterDtoList);
    }
}
