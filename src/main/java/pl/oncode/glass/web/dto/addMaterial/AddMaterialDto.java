package pl.oncode.glass.web.dto.addMaterial;

import pl.oncode.glass.model.Material;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddMaterialDto {

    @Size(max = 30, message = "Name can have maximum 30 characters")
    @NotEmpty(message = "Name cannot be null nor empty")
    private String name;

    @Size(max = 100, message = "Description can have maximum 100 characters")
    private String description;

    public AddMaterialDto() {
    }

    public AddMaterialDto(String name, String description) {
        this.name = name;
        this.description = description;
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

    public static class AddMaterialDtoBuilder {
        private String name;
        private String description;

        public AddMaterialDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AddMaterialDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AddMaterialDto createAddMaterialDto() {
            return new AddMaterialDto(name, description);
        }
    }

    public Material createMaterial() {
        return new Material(null, this.name, this.description);
    }

}
