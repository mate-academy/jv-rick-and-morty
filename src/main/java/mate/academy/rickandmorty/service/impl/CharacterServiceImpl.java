package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharactersClient charactersClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public void saveAll() {
        characterRepository.saveAll(characterMapper.toListModel(charactersClient.findAll()));
    }

    @Override
    public CharacterEntity findRandom() {
        return characterRepository.findById(getRandomId())
                .orElseThrow(() -> new RuntimeException("No character found"));
    }

    @Override
    public List<CharacterEntity> findByName(String name) {
        return characterRepository.findByName(name);
    }

    private long getRandomId() {
        return random.nextLong(characterRepository.count());
    }
}
