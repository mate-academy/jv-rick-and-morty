package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.RnMCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CharacterRepository
        extends JpaRepository<RnMCharacter, Long>,
        JpaSpecificationExecutor<RnMCharacter> {

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select * from characters order by rand() limit 1")
    RnMCharacter findFirst();

}
