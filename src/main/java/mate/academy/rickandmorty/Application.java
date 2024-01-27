package mate.academy.rickandmorty;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.server.CharacterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private static CharacterServer characterServer;

    @Autowired
    public void setCharacterServer(CharacterServer characterServer1) {
        characterServer = characterServer1;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Application.class, args);
        characterServer.saveAllFromExternalApi();
    }
}
