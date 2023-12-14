package mate.academy.rickandmorty.service.impl;

import java.util.List;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.reposetory.CharacterRepository;
import mate.academy.rickandmorty.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {
    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    @Autowired
    public DateServiceImpl(CharacterRepository characterRepository,
                           CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public void saveCharacter(List<CreateCharacterRequestDto> createCharacterRequestDto) {
        createCharacterRequestDto.stream()
                .map(characterMapper::toModel)
                .forEach(characterRepository::save);
    }
}
