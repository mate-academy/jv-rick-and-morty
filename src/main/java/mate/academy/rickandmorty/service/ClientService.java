package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    List<CreateCharacterRequestDto> getAllCharacters();
}
