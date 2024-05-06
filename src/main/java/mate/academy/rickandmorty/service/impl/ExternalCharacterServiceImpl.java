package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.ExternalCharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalCharacterServiceImpl implements ExternalCharacterService {
    private final CharacterRepository repository;

    @Override
    public List<Character> getAll() {
        return List.of();
    }

    @Override
    public void saveAll(List<Character> characters) {
        repository.saveAll(characters);
    }
}
