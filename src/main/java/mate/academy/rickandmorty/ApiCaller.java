package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApiCaller implements ApplicationRunner {
    private ClientService clientService;

    private CharacterService characterService;

    @Autowired
    public ApiCaller(ClientService clientService, CharacterService characterService) {
        this.clientService = clientService;
        this.characterService = characterService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<CreateCharacterRequestDto> listCreateCharacterRequestDto
                = clientService.getAllCharacters();

        characterService.saveCharacter(listCreateCharacterRequestDto);
    }
}
