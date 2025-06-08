package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.HeroInternalDto;
import mate.academy.rickandmorty.service.HeroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Heroes management", description = "Endpoints for managing heroes")
@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping
    @Operation(summary = "Get hero by name", description = "Search Hero by name")
    public HeroInternalDto getHeroByName(@RequestParam String name) {
        return heroService.getHeroByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random hero", description = "Get random hero")
    public HeroInternalDto getRandomHero() {
        return heroService.getRandomHero();
    }
}
