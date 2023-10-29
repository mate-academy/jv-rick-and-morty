package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Table(name = "personalities")
@SQLDelete(sql = "UPDATE persons SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@NoArgsConstructor
public class Personality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long externalId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Gender gender;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public enum Status {
        ALIVE,
        DEAD,
        UNKNOWN;
    }

    public enum Gender {
        FEMALE,
        MALE,
        GENDERLESS,
        UNKNOWN
    }
}
