package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.HeroDto;
import mate.academy.rickandmorty.mapper.HeroMapper;
import mate.academy.rickandmorty.model.Hero;
import mate.academy.rickandmorty.repository.HeroRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HeroDataInitializerImpl {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;
    private final HeroClient client;

    @PostConstruct
    public void initializeCharacters() {
        List<HeroDto> heroes = client.getHeroes();
        List<Hero> apiHeroes = heroes.stream()
                .map(heroMapper::toModel)
                .toList();
        heroRepository.saveAll(apiHeroes);
    }
}
