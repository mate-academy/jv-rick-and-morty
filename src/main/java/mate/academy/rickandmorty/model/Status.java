package mate.academy.rickandmorty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "statuses")
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusEnum name;

    public Status() {
    }

    public Status(StatusEnum name) {
        this.name = name;
    }

    public enum StatusEnum {
        UNKNOWN("Unknown"),
        ALIVE("Alive"),
        DEAD("Dead");

        private String title;

        StatusEnum(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
