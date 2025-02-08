package mate.academy.rickandmorty.repository.cartooncharacter;

import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter, Long>,
        JpaSpecificationExecutor<CartoonCharacter> {

}
