package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.mapper.RickAndMortyCharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private final CharacterRepository characterRepository;

    private final RickAndMortyCharacterMapper characterMapper;

    @Override
    public List<RickAndMortyCharacter> saveAll(List<CharacterResultDto> resultDtoList) {
        List<RickAndMortyCharacter> rickAndMortyCharacterList = new ArrayList<>();
        resultDtoList.forEach(rs -> rickAndMortyCharacterList
                .add(characterMapper.toModel(rs)));
        return characterRepository.saveAll(rickAndMortyCharacterList);
    }

    @Override
    public CharacterWikiDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }
}
