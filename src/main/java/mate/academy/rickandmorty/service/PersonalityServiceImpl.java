package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.PersonalityDto;
import mate.academy.rickandmorty.dto.internal.PersonalitySearchParametersDto;
import mate.academy.rickandmorty.dto.mapper.PersonMapper;
import mate.academy.rickandmorty.model.Personality;
import mate.academy.rickandmorty.repository.personality.PersonalityRepository;
import mate.academy.rickandmorty.repository.personality.PersonalitySpecificationBuilderImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalityServiceImpl implements PersonalityService {
    private final PersonalityRepository personalityRepository;
    private final RickAndMortyHttpClient rickAndMortyHttpClient;
    private final PersonMapper personMapper;
    private final PersonalitySpecificationBuilderImpl personalitySpecificationBuilder;

    @PostConstruct
    public void init() {
        final List<CharacterDto> allCharactersFromRickAndMortyApi =
                rickAndMortyHttpClient.getAllCharactersFromPages();
        this.saveAllPersonalitiesFromThirdPartAri(allCharactersFromRickAndMortyApi);
    }

    @Override
    public void saveAllPersonalitiesFromThirdPartAri(List<CharacterDto> characterDtos) {
        final List<Personality> personalities = characterDtos.stream()
                .map(personMapper::characterDtoToPersonality)
                .toList();
        personalityRepository.saveAll(personalities);
    }

    @Override
    public PersonalityDto getRandomPersonality() {
        return personMapper.toPersonalityDtoFromEntity(
                personalityRepository.findRandomPersonality());
    }

    @Override
    public List<PersonalityDto> getAll(Pageable pageable) {
        return personalityRepository.findAll(pageable).stream()
                .map(personMapper::toPersonalityDtoFromEntity)
                .toList();
    }

    @Override
    public List<PersonalityDto> search(
            PersonalitySearchParametersDto params,
            Pageable pageable
    ) {
        final Specification<Personality> personalitySpecification
                = personalitySpecificationBuilder.build(params);
        return personalityRepository.findAll(personalitySpecification,pageable)
                .stream()
                .map(personMapper::toDto)
                .toList();
    }
}
