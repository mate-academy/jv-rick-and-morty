package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genders")
@Getter
@Setter
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private GenderEnum name;

    public Gender() {
    }

    public Gender(GenderEnum name) {
        this.name = name;
    }

    public enum GenderEnum {
        UNKNOWN("unknown"),
        FEMALE("Female"),
        MALE("Male"),
        GENDERLESS("Genderless");

        private String title;

        GenderEnum(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
