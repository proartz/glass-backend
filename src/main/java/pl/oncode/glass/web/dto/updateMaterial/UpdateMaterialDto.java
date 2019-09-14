package pl.oncode.glass.web.dto.updateMaterial;

import pl.oncode.glass.model.Material;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateMaterialDto {

    @NotNull(message = "Id cannot be null")
    private Integer id;

    @Size(max = 30, message = "Name can have maximum 30 characters")
    @NotEmpty(message = "Name cannot be null nor empty")
    private String name;

    @Size(max = 100, message = "Description can have maximum 100 characters")
    private String description;

    public UpdateMaterialDto() {
    }

    public UpdateMaterialDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Material createMaterial() {
        return new Material(this.id, this.name, this.description);
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

    public static class UpdateMaterialDtoBuilder {
        private Integer id;
        private String name;
        private String description;

        public UpdateMaterialDtoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UpdateMaterialDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UpdateMaterialDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public UpdateMaterialDto createUpdateMaterialDto() {
            return new UpdateMaterialDto(id, name, description);
        }
    }

    @Override
    public String toString() {
        return "UpdateMaterialDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
