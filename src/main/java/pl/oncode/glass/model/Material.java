package pl.oncode.glass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30, message = "Name can have maximum 30 characters")
    @NotEmpty(message = "Name cannot be null nor empty")
    private String name;

    @Size(max = 100, message = "Description can have maximum 100 characters")
    private String description;

    public Material() {
    }

    public Material(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
