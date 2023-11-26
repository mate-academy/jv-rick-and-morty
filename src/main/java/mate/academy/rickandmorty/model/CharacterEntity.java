package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;
    private String name;
    private String status;
    private String gender;

    public CharacterEntity() {

    }

    public CharacterEntity(String externalId, String name, String status, String gender) {
        this.externalId = externalId;
        this.name = name;
        this.status = status;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterEntity that = (CharacterEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(externalId, that.externalId)
                && Objects.equals(name, that.name)
                && Objects.equals(status, that.status)
                && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, name, status, gender);
    }

    @Override
    public String toString() {
        return "CharacterEntity{"
                + "id=" + id
                + ", externalId='" + externalId + '\''
                + ", name='" + name + '\''
                + ", status='" + status + '\''
                + ", gender='" + gender + '\'' + '}';
    }
}
