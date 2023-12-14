package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.service.ClientService;
import mate.academy.rickandmorty.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApiCaller implements ApplicationRunner {
    private ClientService clientService;

    private DateService dateService;

    @Autowired
    public ApiCaller(ClientService clientService, DateService dateService) {
        this.clientService = clientService;
        this.dateService = dateService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<CreateCharacterRequestDto> listCreateCharacterRequestDto
                = clientService.getAllCharacters();

        dateService.saveCharacter(listCreateCharacterRequestDto);
    }
}
