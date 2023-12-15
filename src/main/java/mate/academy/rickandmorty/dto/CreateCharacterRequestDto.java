package mate.academy.rickandmorty.dto;

import org.springframework.stereotype.Component;

@Component
public class CreateCharacterRequestDto {
    private Long id;

    private String name;

    private String status;

    private String gender;

    public CreateCharacterRequestDto() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long externalId) {
        this.id = externalId;
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
