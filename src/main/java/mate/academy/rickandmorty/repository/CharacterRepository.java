package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterPerson, Long> {

    List<CharacterPerson> findByNameLikeIgnoreCase(String name);
}
