package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "externalId",unique = true)
    @NotBlank(message = "External ID must not be blank")
    private String externalId;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Status must not be blank")
    private String status;
    @NotBlank(message = "Gender must not be blank")
    private String gender;

}
