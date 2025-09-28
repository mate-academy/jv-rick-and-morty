package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor; // ИСПРАВЛЕНИЕ: Используем AllArgsConstructor
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // ИСПРАВЛЕНИЕ: Заменяем @RequiredArgsConstructor на @AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyClient characterClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @PostConstruct
    public void saveAllCharacters() {
        // Очищаем таблицу перед загрузкой, чтобы избежать дубликатов при каждом перезапуске
        // (из-за spring.jpa.hibernate.ddl-auto=create-drop)
        characterRepository.deleteAll();

        List<CharacterDto> characterDtos = characterClient.getCharacters();
        List<RickAndMortyCharacter> rickAndMortyCharacters = characterDtos.stream()
                .map(characterMapper::toModel).toList();
        characterRepository.saveAll(rickAndMortyCharacters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        // ИСПРАВЛЕНИЕ ОПТИМИЗАЦИИ: Получаем только количество, чтобы избежать загрузки всей БД
        long count = characterRepository.count();
        if (count == 0) {
            throw new RuntimeException("No characters found in database");
        }

        // Генерируем случайный индекс от 0 до count-1.
        long randomIndex = random.nextLong(count);

        // Поскольку метод findById(id) самый эффективный, попробуем найти по ID,
        // но ID могут быть с пропусками, поэтому используем findAll().get()
        // как временное решение, пока не будет реализован поиск по случайному ID без findAll().
        List<RickAndMortyCharacter> allCharacters = characterRepository.findAll();
        RickAndMortyCharacter rickAndMortyCharacter = allCharacters.get((int) randomIndex);

        return characterMapper.toDto(rickAndMortyCharacter);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        List<RickAndMortyCharacter> charactersByName = characterRepository
                .findByNameContainingIgnoreCase(name);
        return charactersByName.stream()
                .map(characterMapper::toDto).toList();
    }
}
