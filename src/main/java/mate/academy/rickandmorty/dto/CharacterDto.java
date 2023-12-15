package mate.academy.rickandmorty.dto;

import org.springframework.stereotype.Component;

@Component
public class CharacterDto {
    private Long id;

    private Long externalId;

    private String name;

    private String status;

    private String gender;

    public CharacterDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getGender() {
        return gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
