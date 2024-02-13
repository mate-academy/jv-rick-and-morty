package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterDtoWithoutExternalId;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public CharacterDto getRandom() {
        return characterMapper.toDto(characterRepository.findRandom().orElseThrow(
                () -> new EntityNotFoundException("Can't find random character")
        ));
    }

    @Override
    public List<CharacterDto> getAllByNameContains(String name, Pageable pageable) {
        return characterRepository.findAllByNameContainsIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<CharacterDtoWithoutExternalId> characterDtoList) {
        characterRepository.saveAll(
                characterDtoList.stream()
                        .map(characterMapper::toEntity)
                        .toList()
        );
    }
}
