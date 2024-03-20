package mate.academy.rickandmorty.service;

import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterClient characterClient;
    private CharacterMapper mapper;
    private CharacterRepository characterRepository;

    @Override
    public CharacterDto getRandomCharacterDto() {
        CharacterResponseDto characterResponseDto = characterClient.getRandomCharacterResponseDto();
        Character character = mapper.toModel(characterResponseDto);
        character = characterRepository.save(character);
        return mapper.toDto(character);
    }
}
