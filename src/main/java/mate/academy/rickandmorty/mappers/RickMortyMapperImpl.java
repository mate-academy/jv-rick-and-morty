package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.dto.RickMortyDtoResponse;
import mate.academy.rickandmorty.models.RickMorty;
import org.springframework.stereotype.Component;

@Component
public class RickMortyMapperImpl implements RickMortyMapper {
    public RickMortyMapperImpl() {
    }

    public RickMorty toModel(RickMortyDtoResponse rickMortyDtoRequest) {
        if (rickMortyDtoRequest == null) {
            return null;
        } else {
            RickMorty rickMorty = new RickMorty();
            if (rickMortyDtoRequest.getExternalId() != null) {
                rickMorty.setExternalId(rickMortyDtoRequest.getExternalId());
            }

            if (rickMortyDtoRequest.getName() != null) {
                rickMorty.setName(rickMortyDtoRequest.getName());
            }

            if (rickMortyDtoRequest.getStatus() != null) {
                rickMorty.setStatus(rickMortyDtoRequest.getStatus());
            }

            if (rickMortyDtoRequest.getGender() != null) {
                rickMorty.setGender(rickMortyDtoRequest.getGender());
            }

            return rickMorty;
        }
    }
}

