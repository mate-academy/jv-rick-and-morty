package mate.academy.rickandmorty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rick_and_morty_characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        @JsonProperty("Male")
        MALE("Male"),

        @JsonProperty("Female")
        FEMALE("Female"),

        @JsonProperty("Genderless")
        GENDERLESS("Genderless"),

        @JsonProperty("unknown")
        UNKNOWN("unknown");

        private String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Status {
        @JsonProperty("Alive")
        ALIVE("Alive"),

        @JsonProperty("Dead")
        DEAD("Dead"),

        @JsonProperty("unknown")
        UNKNOWN("unknown");

        private String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
