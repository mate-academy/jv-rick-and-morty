package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mappper.MapperToEntity;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final MapperToEntity<CharacterResponseDto, Character> mapperToEntity;
    private final CharacterRepository characterRepository;

    @Override
    public List<Character> saveAll(List<CharacterResponseDto> dtoList) {
        List<Character> characters = dtoList.stream()
                .map(mapperToEntity::toEntity)
                .toList();
        return characterRepository.saveAll(characters);
    }

    @Override
    public List<Character> findAllByName(String name) {
        return characterRepository.findAllByName(name);
    }
}
