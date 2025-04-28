package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
@Tag(name = "Database initialization point", description = "This autostarted endpoint is used to"
        + " automatically fill the database by data from other source. Further if there is any "
        + "need you can manually restart this point to renew data in database.")
public class InitDbController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Just calling needed method to fill the database", description = "By "
            + "using empty controller you will start database renew.")
    public String initDb() {
        return characterService.initDb();
    }
}
