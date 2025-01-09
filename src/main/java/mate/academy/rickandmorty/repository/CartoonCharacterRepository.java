package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoonCharacterRepository extends
        JpaRepository<CartoonCharacter, Long>,
        JpaSpecificationExecutor<CartoonCharacter> {
    @Query("FROM CartoonCharacter character "
            + "left join fetch character.origin "
            + "left join fetch character.location "
            + "left join fetch character.episodes "
            + " WHERE character.externalId = :id")
    Optional<CartoonCharacter> findById(Long id);

    @Query("FROM CartoonCharacter character "
            + "left join fetch character.origin "
            + "left join fetch character.location "
            + "left join fetch character.episodes "
            + " WHERE character.name = :name")
    Optional<CartoonCharacter> findByName(String name);

    @Query("FROM CartoonCharacter character "
            + "left join fetch character.origin "
            + "left join fetch character.location "
            + "left join fetch character.episodes "
            + " WHERE character.externalId = :externalId")
    Optional<CartoonCharacter> findByExternalId(Long externalId);

    @Query("FROM CartoonCharacter character "
            + "left join fetch character.origin "
            + "left join fetch character.location "
            + "left join fetch character.episodes "
            + " WHERE character.name LIKE :string")
    List<CartoonCharacter> findNamesLike(String string);
}
