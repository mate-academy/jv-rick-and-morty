package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.HeroInternalDto;

public interface HeroService {

    HeroInternalDto getRandomHero();

    HeroInternalDto getHeroByName(String heroName);
}
