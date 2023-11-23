package mate.academy.rickandmorty.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @Override
    public Character getById(Long id) {
        return characterRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Can't find character by id " + id));
    }

    @Override
    public int getSizeOfDb() {
        return characterRepository.findAll().size();
    }

    @Override
    public List<CharacterDto> getDtoByParam(String string) {
        return characterRepository.findCharactersByNameLike(string)
                .stream().map(characterMapper::toDto).map(c -> setId(c)).toList();
    }

    @Override
    public List<Character> getByParam(String string) {
        return characterRepository.findCharactersByNameLike(string);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return setId(characterMapper.toDto(characterClient.getRandomCharacterFromDb()));
    }

    private CharacterDto setId(CharacterDto characterDto) {
        Character character = characterRepository.getCharacterByName(characterDto.getName());
        characterDto.setId(character.getId());
        return characterDto;
    }
}
