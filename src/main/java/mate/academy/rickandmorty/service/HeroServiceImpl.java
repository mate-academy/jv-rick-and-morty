package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.HeroInternalDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.HeroMapper;
import mate.academy.rickandmorty.model.Hero;
import mate.academy.rickandmorty.repository.HeroRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    @Override
    public HeroInternalDto getRandomHero() {
        Hero randomHero = heroRepository.getRandomHero();
        return heroMapper.toInternalDto(randomHero);
    }

    @Override
    public HeroInternalDto getHeroByName(String name) {
        return heroRepository.findHeroByName(name)
                .map(heroMapper::toInternalDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot find character with name " + name));
    }
}
