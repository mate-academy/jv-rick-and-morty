package mate.academy.rickandmorty.external.api.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.external.api.service.ExternalCharacterService;
import mate.academy.rickandmorty.mapping.CharacterMapper;
import mate.academy.rickandmorty.models.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalCharacterServiceImpl implements ExternalCharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveOrUpdate(List<CharacterResponseDto> responseDtoList) {
        List<Character> listToSave = new LinkedList<>();

        for (CharacterResponseDto responseDto : responseDtoList) {
            Optional<Character> characterOptional = characterRepository
                    .findFirstByExternalId(responseDto.getExternalId());

            if (characterOptional.isEmpty()) {
                listToSave.add(
                        characterMapper.convertFromResponseDto(responseDto));
            } else {
                Character character = characterOptional.get();
                if (isEqual(responseDto, character)) {
                    return;
                }

                Character updateCharacter = characterMapper.convertFromResponseDto(responseDto);
                updateCharacter.setId(character.getId());
                listToSave.add(updateCharacter);
            }
        }
        characterRepository.saveAll(listToSave);
    }

    private boolean isEqual(CharacterResponseDto characterResponseDto, Character character) {
        return characterResponseDto.getName().equals(character.getName())
                && characterResponseDto.getGender().equals(character.getGender())
                && characterResponseDto.getStatus().equals(character.getStatus());
    }
}
