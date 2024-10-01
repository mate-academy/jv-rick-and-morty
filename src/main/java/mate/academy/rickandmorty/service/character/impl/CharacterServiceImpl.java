package mate.academy.rickandmorty.service.character.impl;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;

    @Override
        public void addListCharacter(List<RickAndMortyApiCharacterDto> characters) {
        characterRepository.saveAll(characters.stream()
                .map(characterMapper::toModel)
                .toList()
        );
    }

    @Override
    public ResponseCharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        Random random = new Random();
        return findById(random.nextLong(count) + 1L);
    }

    @Override
    public List<ResponseCharacterDto> findAllByName(String name, Pageable pageable) {
        return characterRepository.findByName(name, pageable).stream()
                .map(characterMapper::toInternalDto)
                .toList();
    }

    @Override
    public ResponseCharacterDto findById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Character not found"));
        return characterMapper
                .toInternalDto(character);
    }
}
